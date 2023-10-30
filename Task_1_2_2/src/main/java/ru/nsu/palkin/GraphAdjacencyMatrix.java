package ru.nsu.palkin;

import java.util.ArrayList;

/**
 * Class GraphAdjacencyMatrix.
 *
 * @param <T> - type of class
 */
public class GraphAdjacencyMatrix<T> extends Graph<T> {
    private ArrayList<ArrayList<Integer>> graph;

    /**
     * Class constructor.
     *
     * @param vertexList - list of vertexes
     * @param edgeList   - list of edges
     */
    GraphAdjacencyMatrix(ArrayList<Vertex<T>> vertexList, ArrayList<Edge<T>> edgeList) {
        this.graph = new ArrayList<>();
        this.vertexList = vertexList;
        this.edgeList = edgeList;
        int len = vertexList.size();
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                cur.add(0);
            }
            graph.add(cur);
        }
        len = edgeList.size();
        for (int i = 0; i < len; i++) {
            int row = vertexList.indexOf(edgeList.get(i).src);
            int col = vertexList.indexOf(edgeList.get(i).dest);
            this.graph.get(row).set(col, edgeList.get(i).weight);
        }
    }

    /**
     * Add vertex to the graph.
     *
     * @param vertex - vertex
     */
    public void addVertex(Vertex<T> vertex) {
        if (!this.vertexList.contains(vertex)) {
            int len = this.vertexList.size();
            ArrayList<Integer> newRow = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                this.graph.get(i).add(0);
                newRow.add(0);
            }
            newRow.add(0);
            this.graph.add(newRow);
            this.vertexList.add(vertex);
        }
    }

    /**
     * Remove vertex from the graph.
     *
     * @param vertex - vertex
     */
    public void removeVertex(Vertex<T> vertex) {
        if (this.vertexList.contains(vertex)) {
            int index = this.vertexList.indexOf(vertex);
            int len = this.vertexList.size();
            for (int i = 0; i < len; i++) {
                this.graph.get(i).remove(index);
            }
            this.graph.remove(index);
            this.vertexList.remove(index);
            for (int i = 0; i < this.edgeList.size(); i++) {
                Edge<T> cur = this.edgeList.get(i);
                if (cur.src.equals(vertex) || cur.dest.equals(vertex)) {
                    this.edgeList.remove(i);
                    i = i - 1;
                }
            }
        }
    }

    /**
     * Change vertex in the graph.
     *
     * @param oldVertex - old vertex
     * @param newVertex - new vertex
     */
    public void changeVertex(Vertex<T> oldVertex, Vertex<T> newVertex) {
        if (this.vertexList.contains(oldVertex) && !this.vertexList.contains(newVertex)) {
            int index = this.vertexList.indexOf(oldVertex);
            this.vertexList.set(index, newVertex);
            int len = this.edgeList.size();
            for (int i = 0; i < len; i++) {
                if (this.edgeList.get(i).src.equals(oldVertex)) {
                    this.edgeList.get(i).src = newVertex;
                }
                if (this.edgeList.get(i).dest.equals(oldVertex)) {
                    this.edgeList.get(i).dest = newVertex;
                }
            }
        }
    }

    /**
     * Add edge to the graph.
     *
     * @param edge - edge
     */
    public void addEdge(Edge<T> edge) {
        if (this.vertexList.contains(edge.src) && this.vertexList.contains(edge.dest)) {
            int row = this.vertexList.indexOf(edge.src);
            int col = this.vertexList.indexOf(edge.dest);
            this.graph.get(row).set(col, edge.weight);
            this.edgeList.add(edge);
        }
    }

    /**
     * Remove edge from the graph.
     *
     * @param edge - edge
     */
    public void removeEdge(Edge<T> edge) {
        if (this.edgeList.contains(edge)) {
            int row = this.vertexList.indexOf(edge.src);
            int col = this.vertexList.indexOf(edge.dest);
            this.graph.get(row).set(col, 0);
            this.edgeList.remove(edge);
        }
    }

    /**
     * Change edge in the graph.
     *
     * @param oldEdge - old edge
     * @param newEdge - new edge
     */
    public void changeEdge(Edge<T> oldEdge, Edge<T> newEdge) {
        if (oldEdge.src.equals(newEdge.src) && oldEdge.dest.equals(newEdge.dest)) {
            if (this.edgeList.contains(oldEdge)) {
                int row = this.vertexList.indexOf(oldEdge.src);
                int col = this.vertexList.indexOf(oldEdge.dest);
                this.graph.get(row).set(col, newEdge.weight);
                int index = this.edgeList.indexOf(oldEdge);
                this.edgeList.set(index, newEdge);
            }
        }
    }
}