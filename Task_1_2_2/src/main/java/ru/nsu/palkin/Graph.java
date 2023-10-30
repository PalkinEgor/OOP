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
    protected static final int INF = Integer.MAX_VALUE;

    /**
     * Add vertex to the graph.
     *
     * @param vertex - vertex
     */
    public abstract int addVertex(Vertex<T> vertex);

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
            if (this.edgeList.get(i).getSrc().equals(vertex)) {
                result.add(this.edgeList.get(i));
            }
        }
        return result;
    }

    /**
     * Get the shortest paths from the vertex.
     *
     * @param vertex - vertex
     * @return list of vertexes
     */
    public ArrayList<Vertex<T>> shortestPath(Vertex<T> vertex) {
        int vertexLen = this.vertexList.size();
        int[] distance = new int[vertexLen];
        Arrays.fill(distance, INF);
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
            if (distance[shortest] == INF) {
                break;
            }
            mark[shortest] = true;

            ArrayList<Edge<T>> adjacentEdge = getEdge(this.vertexList.get(shortest));
            int edgeLen = adjacentEdge.size();
            for (int j = 0; j < edgeLen; j++) {
                Edge<T> cur = adjacentEdge.get(j);
                if (distance[shortest] + cur.getWeight()
                        < distance[this.vertexList.indexOf(cur.getDest())]) {
                    distance[this.vertexList.indexOf(cur.getDest())]
                            = distance[shortest] + cur.getWeight();
                }
            }
        }

        ArrayList<VertexDistance<T>> sortArray = new ArrayList<>();
        for (int i = 0; i < vertexLen; i++) {
            sortArray.add(new VertexDistance<>(this.vertexList.get(i), distance[i]));
        }
        Collections.sort(sortArray);
        ArrayList<Vertex<T>> result = new ArrayList<>();
        int len = sortArray.size();
        for (int i = 0; i < len; i++) {
            result.add(sortArray.get(i).vertex);
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