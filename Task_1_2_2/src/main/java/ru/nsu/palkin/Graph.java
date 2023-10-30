package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Graph class.
 *
 * @param <T> - type of class
 */
public abstract class Graph<T> {
    protected ArrayList<Vertex<T>> vertexList;
    protected ArrayList<Edge<T>> edgeList;
    protected static final int inf = Integer.MAX_VALUE;

    /**
     * Add vertex to the graph.
     *
     * @param vertex - vertex
     */
    public abstract void addVertex(Vertex<T> vertex);

    /**
     * Remove vertex from the graph.
     *
     * @param vertex - vertex
     */
    public abstract void removeVertex(Vertex<T> vertex);

    /**
     * Change vertex in the graph.
     *
     * @param oldVertex - old vertex
     * @param newVertex - new vertex
     */
    public abstract void changeVertex(Vertex<T> oldVertex, Vertex<T> newVertex);

    /**
     * Add edge to the graph.
     *
     * @param edge - edge
     */
    public abstract void addEdge(Edge<T> edge);

    /**
     * Remove edge from the graph.
     *
     * @param edge - edge
     */
    public abstract void removeEdge(Edge<T> edge);

    /**
     * Change edge in the graph.
     *
     * @param oldEdge - old edge
     * @param newEdge - new edge
     */
    public abstract void changeEdge(Edge<T> oldEdge, Edge<T> newEdge);

    /**
     * Get adjacent edges.
     *
     * @param vertex - vertex
     * @return list of adjacent edges
     */
    public ArrayList<Edge<T>> getEdge(Vertex<T> vertex) {
        ArrayList<Edge<T>> result = new ArrayList<>();
        int len = this.edgeList.size();
        for (int i = 0; i < len; i++) {
            if (this.edgeList.get(i).src.equals(vertex)) {
                result.add(this.edgeList.get(i));
            }
        }
        return result;
    }

    /**
     * Get the shortest paths from the vertex.
     *
     * @param vertex - vertex
     * @return string with vertexes and path length for each vertex
     */
    public StringBuilder shortestPath(Vertex<T> vertex) {
        int vertexLen = this.vertexList.size();
        int[] distance = new int[vertexLen];
        Arrays.fill(distance, inf);
        distance[this.vertexList.indexOf(vertex)] = 0;
        boolean[] mark = new boolean[vertexLen];
        Arrays.fill(mark, false);

        for (int i = 0; i < vertexLen; i++) {
            int shortest = -1;
            for (int j = 0; j < vertexLen; j++) {
                if (!mark[j] && (shortest == -1 || distance[shortest] > distance[j])) {
                    shortest = j;
                }
            }
            if (distance[shortest] == inf) {
                break;
            }
            mark[shortest] = true;

            ArrayList<Edge<T>> adjacentEdge = getEdge(this.vertexList.get(shortest));
            int edgeLen = adjacentEdge.size();
            for (int j = 0; j < edgeLen; j++) {
                Edge<T> cur = adjacentEdge.get(j);
                if (distance[shortest] + cur.weight < distance[this.vertexList.indexOf(cur.dest)]) {
                    distance[this.vertexList.indexOf(cur.dest)] = distance[shortest] + cur.weight;
                }
            }
        }

        ArrayList<VertexDistance<T>> map = new ArrayList<>();
        for (int i = 0; i < vertexLen; i++) {
            map.add(new VertexDistance<>(this.vertexList.get(i), distance[i]));
        }
        Collections.sort(map);
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < vertexLen; i++) {
            if (i != vertexLen - 1) {
                if (map.get(i).distance != inf) {
                    result.append(map.get(i).vertex.object).append("(")
                            .append(map.get(i).distance).append("), ");
                } else {
                    result.append(map.get(i).vertex.object).append("(")
                            .append("infinity").append("), ");
                }
            } else {
                if (map.get(i).distance != inf) {
                    result.append(map.get(i).vertex.object).append("(")
                            .append(map.get(i).distance).append(")]");
                } else {
                    result.append(map.get(i).vertex.object).append("(")
                            .append("infinity").append(")]");
                }
            }
        }
        return result;
    }

    /**
     * Vertex distance class.
     *
     * @param <T> - type of class
     */
    protected class VertexDistance<T> implements Comparable<VertexDistance> {
        protected Vertex<T> vertex;
        protected int distance;

        /**
         * Class constructor.
         *
         * @param vertex   - vertex
         * @param distance - distance
         */
        VertexDistance(Vertex<T> vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * Override compareTo method.
         *
         * @param vertexDistance - vertexDistance object
         * @return positive, negative number or zero
         */
        @Override
        public int compareTo(VertexDistance vertexDistance) {
            return this.distance - vertexDistance.distance;
        }
    }
}