package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class GraphIncidenceMatrix.
 *
 * @param <T> - objects in vertices
 */
public class GraphIncidenceMatrix<T> implements Graphable<T> {
    private ArrayList<ArrayList<EdgeStatus<T>>> graph;
    private ArrayList<T> verticesList;
    private ArrayList<Edge<T>> edgesList;
    private static final int inf = Integer.MAX_VALUE;

    /**
     * Class constructor.
     *
     * @param vertices - list of vertices
     * @param edges    - list of edges
     */
    GraphIncidenceMatrix(ArrayList<T> vertices, ArrayList<Edge<T>> edges) {
        this.verticesList = vertices;
        this.edgesList = edges;
        this.graph = new ArrayList<>();
        int len1 = vertices.size();
        int len2 = edges.size();
        for (int i = 0; i < len1; i++) {
            ArrayList<EdgeStatus<T>> cur = new ArrayList<>();
            for (int j = 0; j < len2; j++) {
                T src = edges.get(j).src;
                T dest = edges.get(j).dest;
                T vert = vertices.get(i);
                if (vert == src && vert == dest) {
                    cur.add(new EdgeStatus<>(2, edges.get(j)));
                } else if (vert == src) {
                    cur.add(new EdgeStatus<>(1, edges.get(j)));
                } else if (vert == dest) {
                    cur.add(new EdgeStatus<>(-1, edges.get(j)));
                } else {
                    cur.add(new EdgeStatus<>(0, null));
                }
            }
            this.graph.add(cur);
        }
    }

    /**
     * Add a vertice to the graph.
     *
     * @param vert - vertice
     */
    @Override
    public void addVertice(T vert) {
        if (!this.verticesList.contains(vert)) {
            this.verticesList.add(vert);
            ArrayList<EdgeStatus<T>> cur = new ArrayList<>();
            int len = edgesList.size();
            for (int i = 0; i < len; i++) {
                cur.add(new EdgeStatus<>(0, null));
            }
            this.graph.add(cur);
        }
    }

    /**
     * Remove a vertice from the graph.
     *
     * @param vert - vertice
     */
    @Override
    public void removeVertice(T vert) {
        if (this.verticesList.contains(vert)) {
            int index = this.verticesList.indexOf(vert);
            int len = this.edgesList.size();
            for (int i = 0; i < len; i++) {
                EdgeStatus<T> cur = new EdgeStatus<>(0, null);
                this.graph.get(index).set(i, cur);
            }
            int len1 = this.verticesList.size();
            for (int i = 0; i < this.edgesList.size(); i++) {
                int count = 0;
                for (int j = 0; j < len1; j++) {
                    if (this.graph.get(j).get(i).status == 1 ||
                            this.graph.get(j).get(i).status == -1) {
                        count++;
                    }
                    if (this.graph.get(j).get(i).status == 2) {
                        count = count + 2;
                    }
                }
                if (count < 2) {
                    removeEdge(this.edgesList.get(i));
                    i--;
                }
            }
            this.verticesList.remove(vert);
            this.graph.remove(index);
        }
    }

    /**
     * Rename a vertice from the graph.
     *
     * @param oldVert - old name
     * @param newVert - new name
     */
    @Override
    public void changeVertice(T oldVert, T newVert) {
        if (this.verticesList.contains(oldVert)) {
            int index = this.verticesList.indexOf(oldVert);
            int len = this.edgesList.size();
            for (int i = 0; i < len; i++) {
                if (this.edgesList.get(i).src == oldVert) {
                    this.edgesList.get(i).src = newVert;
                }
                if (this.edgesList.get(i).dest == oldVert) {
                    this.edgesList.get(i).dest = newVert;
                }
                if (this.graph.get(index).get(i).status != 0) {
                    if (this.graph.get(index).get(i).edge.src == oldVert) {
                        this.graph.get(index).get(i).edge.src = oldVert;
                    }
                    if (this.graph.get(index).get(i).edge.dest == oldVert) {
                        this.graph.get(index).get(i).edge.dest = oldVert;
                    }
                }
            }
            this.verticesList.set(this.verticesList.indexOf(oldVert), newVert);
        }
    }

    /**
     * Add an edge to the graph.
     *
     * @param edge - edge
     */
    @Override
    public void addEdge(Edge<T> edge) {
        if (this.verticesList.contains(edge.src) && this.verticesList.contains(edge.dest)
                && !this.edgesList.contains(edge)) {
            int len = this.verticesList.size();
            for (int i = 0; i < len; i++) {
                T src = edge.src;
                T dest = edge.dest;
                if (this.verticesList.get(i) == src && src == dest) {
                    EdgeStatus<T> cur = new EdgeStatus<>(2, edge);
                    this.graph.get(i).add(cur);
                } else if (this.verticesList.get(i) == src) {
                    EdgeStatus<T> cur = new EdgeStatus<>(1, edge);
                    this.graph.get(i).add(cur);
                } else if (this.verticesList.get(i) == dest) {
                    EdgeStatus<T> cur = new EdgeStatus<>(-1, edge);
                    this.graph.get(i).add(cur);
                } else {
                    EdgeStatus<T> cur = new EdgeStatus<>(0, null);
                    this.graph.get(i).add(cur);
                }
            }
            this.edgesList.add(edge);
        }
    }

    /**
     * Remove an edge from the graph.
     *
     * @param edge - edge
     */
    @Override
    public void removeEdge(Edge<T> edge) {
        if (this.edgesList.contains(edge)) {
            int len = this.verticesList.size();
            int index = this.edgesList.indexOf(edge);
            for (int i = 0; i < len; i++) {
                this.graph.get(i).remove(index);
            }
            this.edgesList.remove(index);
        }
    }

    /**
     * Change weight of the edge.
     *
     * @param oldEdge - old weight
     * @param newEdge - new weight
     */
    @Override
    public void changeEdge(Edge<T> oldEdge, Edge<T> newEdge) {
        if (this.edgesList.contains(oldEdge)) {
            int index = this.edgesList.indexOf(oldEdge);
            this.edgesList.set(index, newEdge);
            int len = this.verticesList.size();
            for (int i = 0; i < len; i++) {
                if (this.graph.get(i).get(index).status == 1) {
                    this.graph.get(i).get(index).edge = newEdge;
                }
                if (this.graph.get(i).get(index).status == -1) {
                    this.graph.get(i).get(index).edge = newEdge;
                }
                if (this.graph.get(i).get(index).status == 2) {
                    this.graph.get(i).get(index).edge = newEdge;
                }
            }
        }
    }

    /**
     * Find the shortest paths from a vertice.
     *
     * @param vert - vertice
     * @return string with distances
     */
    @Override
    public StringBuilder shortestPaths(T vert) {
        int len = this.verticesList.size();
        int len2 = this.edgesList.size();
        int[] distance = new int[len];
        boolean[] marks = new boolean[len];
        Arrays.fill(marks, false);
        Arrays.fill(distance, inf);
        distance[this.verticesList.indexOf(vert)] = 0;
        for (int i = 0; i < len; i++) {
            int shortest = -1;
            for (int j = 0; j < len; j++) {
                if (!marks[j] && (shortest == -1 || distance[shortest] > distance[j])) {
                    shortest = j;
                }
            }
            if (distance[shortest] == inf) {
                break;
            }
            marks[shortest] = true;
            for (int j = 0; j < len2; j++) {
                if (this.graph.get(shortest).get(j).status == 1) {
                    Edge<T> cur = this.graph.get(shortest).get(j).edge;
                    int index = this.verticesList.indexOf(cur.dest);
                    if (distance[shortest] + cur.weight < distance[index]) {
                        distance[index] = distance[shortest] + cur.weight;
                    }
                }
            }
        }

        ArrayList<VerticesDistance<T>> map = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            map.add(new VerticesDistance<>(this.verticesList.get(i), distance[i]));
        }
        Collections.sort(map);
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < len; i++) {
            if (i != len - 1) {
                if (map.get(i).distance != inf) {
                    result.append(map.get(i).vertice).append("(").
                            append(map.get(i).distance).append("), ");
                } else {
                    result.append(map.get(i).vertice).append("(").
                            append("infinity").append("), ");
                }
            } else {
                if (map.get(i).distance != inf) {
                    result.append(map.get(i).vertice).append("(").
                            append(map.get(i).distance).append(")]");
                } else {
                    result.append(map.get(i).vertice).append("(").
                            append("infinity").append(")]");
                }
            }
        }
        return result;
    }

    /**
     * Class with edge and status(-1, 1, 0, 2).
     *
     * @param <T> - objects in edges
     */
    private class EdgeStatus<T> {
        private int status;
        private Edge<T> edge;

        /**
         * Class constructor.
         *
         * @param status - status
         * @param edge   - edge
         */
        EdgeStatus(int status, Edge<T> edge) {
            this.status = status;
            this.edge = edge;
        }
    }

    /**
     * Class with vertice and distance.
     *
     * @param <T> - objects in vertices
     */
    private class VerticesDistance<T> implements Comparable<VerticesDistance> {
        private T vertice;
        private int distance;

        /**
         * Class constructor.
         *
         * @param vertice  - vertice
         * @param distance - distance
         */
        VerticesDistance(T vertice, int distance) {
            this.vertice = vertice;
            this.distance = distance;
        }

        /**
         * Comparator.
         *
         * @param verticesDistance - object verticeDistance
         * @return distance difference
         */
        @Override
        public int compareTo(VerticesDistance verticesDistance) {
            return this.distance - verticesDistance.distance;
        }
    }
}