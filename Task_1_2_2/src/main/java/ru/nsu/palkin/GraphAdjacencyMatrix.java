package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class GraphAdjacencyMatrix.
 *
 * @param <T> - objects in vertices
 */
public class GraphAdjacencyMatrix<T> implements Graphable<T> {
    private ArrayList<ArrayList<Integer>> graph;
    private ArrayList<T> verticesList;
    private static final int inf = Integer.MAX_VALUE;

    /**
     * Class constructor.
     *
     * @param vertices - list of vertices
     * @param edges    - list of edges
     */
    GraphAdjacencyMatrix(ArrayList<T> vertices, ArrayList<Edge<T>> edges) {
        this.graph = new ArrayList<>();
        this.verticesList = vertices;
        int len = vertices.size();
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> t = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                t.add(-1);
            }
            this.graph.add(t);
        }
        len = edges.size();
        for (int i = 0; i < len; i++) {
            int row = this.verticesList.indexOf(edges.get(i).src);
            int col = this.verticesList.indexOf(edges.get(i).dest);
            this.graph.get(row).set(col, edges.get(i).weight);
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
            ArrayList<Integer> newRow = new ArrayList<>();
            int len = this.verticesList.size();
            for (int i = 0; i < len; i++) {
                graph.get(i).add(-1);
                newRow.add(-1);
            }
            newRow.add(-1);
            this.graph.add(newRow);
            this.verticesList.add(vert);
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
            int len = this.verticesList.size();
            for (int i = 0; i < len; i++) {
                this.graph.get(i).remove(index);
            }
            this.graph.remove(index);
            this.verticesList.remove(index);
        }
    }

    /**
     * Rename vertice in the graph.
     *
     * @param oldVert - old name
     * @param newVert - new name
     */
    @Override
    public void changeVertice(T oldVert, T newVert) {
        if (this.verticesList.contains(oldVert)) {
            int index = this.verticesList.indexOf(oldVert);
            this.verticesList.set(index, newVert);
        }
    }

    /**
     * Add an edge to the graph.
     *
     * @param edge - edge
     */
    @Override
    public void addEdge(Edge<T> edge) {
        if (this.verticesList.contains(edge.src) && this.verticesList.contains(edge.dest)) {
            int row = this.verticesList.indexOf(edge.src);
            int col = this.verticesList.indexOf(edge.dest);
            this.graph.get(row).set(col, edge.weight);
        }
    }

    /**
     * Remove an edge from the graph.
     *
     * @param edge - edge
     */
    @Override
    public void removeEdge(Edge<T> edge) {
        if (this.verticesList.contains(edge.src) && this.verticesList.contains(edge.dest)) {
            int row = this.verticesList.indexOf(edge.src);
            int col = this.verticesList.indexOf(edge.dest);
            this.graph.get(row).set(col, -1);
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
        if (this.verticesList.contains(oldEdge.src) && this.verticesList.contains(oldEdge.dest)) {
            int row = this.verticesList.indexOf(oldEdge.src);
            int col = this.verticesList.indexOf(oldEdge.dest);
            this.graph.get(row).set(col, newEdge.weight);
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
        int[] distance = new int[len];
        boolean[] marks = new boolean[len];
        Arrays.fill(marks, false);
        Arrays.fill(distance, inf);
        int index = this.verticesList.indexOf(vert);
        distance[index] = 0;
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
            for (int j = 0; j < len; j++) {
                if (this.graph.get(shortest).get(j) != -1) {
                    if (distance[shortest] + this.graph.get(shortest).get(j) < distance[j]) {
                        distance[j] = distance[shortest] + this.graph.get(shortest).get(j);
                    }
                }
            }
        }

        ArrayList<VerticeDistance<T>> map = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            map.add(new VerticeDistance<>(this.verticesList.get(i), distance[i]));
        }
        Collections.sort(map);
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < len; i++) {
            if (i != len - 1) {
                if (map.get(i).distance != inf) {
                    result.append(map.get(i).vertice).append("(")
                            .append(map.get(i).distance).append("), ");
                } else {
                    result.append(map.get(i).vertice).append("(")
                            .append("infinity").append("), ");
                }
            } else {
                if (map.get(i).distance != inf) {
                    result.append(map.get(i).vertice).append("(")
                            .append(map.get(i).distance).append(")]");
                } else {
                    result.append(map.get(i).vertice).append("(")
                            .append("infinity").append(")]");
                }
            }
        }
        return result;
    }

    /**
     * Class with vertice and distance.
     *
     * @param <T> - objects in vertices
     */
    private class VerticeDistance<T> implements Comparable<VerticeDistance> {
        private T vertice;
        private int distance;

        /**
         * Class constructor.
         *
         * @param vertice  - vertice
         * @param distance - distance
         */
        VerticeDistance(T vertice, int distance) {
            this.vertice = vertice;
            this.distance = distance;
        }

        /**
         * Comparator.
         *
         * @param verticeDistance - object verticeDistance
         * @return distance difference
         */
        @Override
        public int compareTo(VerticeDistance verticeDistance) {
            return this.distance - verticeDistance.distance;
        }
    }
}