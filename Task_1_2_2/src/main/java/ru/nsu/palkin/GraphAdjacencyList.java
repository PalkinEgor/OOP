package ru.nsu.palkin;

import java.util.ArrayList;

/**
 * Class GraphAdjacencyList.
 *
 * @param <T> - type of class
 */
public class GraphAdjacencyList<T> extends Graph<T> {
    private ArrayList<ArrayList<VertexDistance<T>>> graph;

    /**
     * Class constructor.
     *
     * @param vertexList - list of vertexes
     * @param edgeList   - list of edges
     */
    GraphAdjacencyList(ArrayList<Vertex<T>> vertexList, ArrayList<Edge<T>> edgeList) {
        this.graph = new ArrayList<>();
        this.vertexList = vertexList;
        this.edgeList = edgeList;
        int len = vertexList.size();
        for (int i = 0; i < len; i++) {
            ArrayList<VertexDistance<T>> cur = new ArrayList<>();
            graph.add(cur);
        }
        len = edgeList.size();
        for (int i = 0; i < len; i++) {
            Vertex<T> src = edgeList.get(i).src;
            Vertex<T> dest = edgeList.get(i).dest;
            int weight = edgeList.get(i).weight;
            int srcIndex = vertexList.indexOf(src);
            VertexDistance<T> cur = new VertexDistance<T>(dest, weight);
            this.graph.get(srcIndex).add(cur);
        }
    }

    /**
     * Add vertex to the graph.
     *
     * @param vertex - vertex
     */
    public void addVertex(Vertex<T> vertex) {
        if (!this.vertexList.contains(vertex)) {
            this.vertexList.add(vertex);
            ArrayList<VertexDistance<T>> cur = new ArrayList<>();
            this.graph.add(cur);
        }
    }

    /**
     * Remove vertex from the graph.
     *
     * @param vertex - vertex
     */
    public void removeVertex(Vertex<T> vertex) {
        if (this.vertexList.contains(vertex)) {
            for (int i = 0; i < this.edgeList.size(); i++) {
                Edge<T> cur = this.edgeList.get(i);
                if (cur.src.equals(vertex) || cur.dest.equals(vertex)) {
                    this.edgeList.remove(i);
                    i--;
                }
            }
            int len = this.vertexList.size();
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < this.graph.get(i).size(); j++) {
                    if (this.graph.get(i).get(j).vertex.equals(vertex)) {
                        this.graph.get(i).remove(j);
                        j--;
                    }
                }
            }
            int index = this.vertexList.indexOf(vertex);
            this.graph.remove(index);
            this.vertexList.remove(index);
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
            len = this.vertexList.size();
            for (int i = 0; i < len; i++) {
                int curLen = this.graph.get(i).size();
                for (int j = 0; j < curLen; j++) {
                    if (this.graph.get(i).get(j).vertex.equals(oldVertex)) {
                        int weight = this.graph.get(i).get(j).distance;
                        this.graph.get(i).set(j, new VertexDistance<T>(newVertex, weight));
                    }
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
            this.edgeList.add(edge);
            int index = this.vertexList.indexOf(edge.src);
            this.graph.get(index).add(new VertexDistance<T>(edge.dest, edge.weight));
        }
    }

    /**
     * Remove edge from the graph.
     *
     * @param edge - edge
     */
    public void removeEdge(Edge<T> edge) {
        if (this.edgeList.contains(edge)) {
            this.edgeList.remove(edge);
            int row = this.vertexList.indexOf(edge.src);
            int len = this.graph.get(row).size();
            for (int i = 0; i < len; i++) {
                if (this.graph.get(row).get(i).vertex.equals(edge.dest)) {
                    this.graph.get(row).remove(i);
                    break;
                }
            }
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
                int index = this.edgeList.indexOf(oldEdge);
                this.edgeList.set(index, newEdge);
                index = this.vertexList.indexOf(oldEdge.src);
                int len = this.graph.get(index).size();
                for (int i = 0; i < len; i++) {
                    if (this.graph.get(index).get(i).vertex.equals(oldEdge.dest)) {
                        this.graph.get(index).get(i).distance = newEdge.weight;
                        break;
                    }
                }
            }
        }
    }
}