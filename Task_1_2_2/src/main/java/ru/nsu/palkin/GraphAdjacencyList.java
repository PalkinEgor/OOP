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
            Vertex<T> src = edgeList.get(i).getSrc();
            Vertex<T> dest = edgeList.get(i).getDest();
            int weight = edgeList.get(i).getWeight();
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
    @Override
    public int addVertex(Vertex<T> vertex) {
        if (!this.vertexList.contains(vertex)) {
            this.vertexList.add(vertex);
            ArrayList<VertexDistance<T>> cur = new ArrayList<>();
            this.graph.add(cur);
            return 1;
        }
        return -1;
    }

    /**
     * Remove vertex from the graph.
     *
     * @param vertex - vertex
     */
    @Override
    public void removeVertex(Vertex<T> vertex) {
        if (this.vertexList.contains(vertex)) {
            for (int i = 0; i < this.edgeList.size(); i++) {
                Edge<T> cur = this.edgeList.get(i);
                if (cur.getSrc().equals(vertex) || cur.getDest().equals(vertex)) {
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
    @Override
    public void changeVertex(Vertex<T> oldVertex, Vertex<T> newVertex) {
        if (this.vertexList.contains(oldVertex) && !this.vertexList.contains(newVertex)) {
            int index = this.vertexList.indexOf(oldVertex);
            this.vertexList.set(index, newVertex);
            int len = this.edgeList.size();
            for (int i = 0; i < len; i++) {
                if (this.edgeList.get(i).getSrc().equals(oldVertex)) {
                    this.edgeList.get(i).setSrc(newVertex);
                }
                if (this.edgeList.get(i).getDest().equals(oldVertex)) {
                    this.edgeList.get(i).setDest(newVertex);
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
    @Override
    public void addEdge(Edge<T> edge) {
        if (this.vertexList.contains(edge.getSrc()) && this.vertexList.contains(edge.getDest())) {
            this.edgeList.add(edge);
            int index = this.vertexList.indexOf(edge.getSrc());
            this.graph.get(index).add(new VertexDistance<T>(edge.getDest(), edge.getWeight()));
        }
    }

    /**
     * Remove edge from the graph.
     *
     * @param edge - edge
     */
    @Override
    public void removeEdge(Edge<T> edge) {
        if (this.edgeList.contains(edge)) {
            this.edgeList.remove(edge);
            int row = this.vertexList.indexOf(edge.getSrc());
            int len = this.graph.get(row).size();
            for (int i = 0; i < len; i++) {
                if (this.graph.get(row).get(i).vertex.equals(edge.getDest())) {
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
    @Override
    public void changeEdge(Edge<T> oldEdge, Edge<T> newEdge) {
        if (oldEdge.getSrc().equals(newEdge.getSrc())
                && oldEdge.getDest().equals(newEdge.getDest())) {
            if (this.edgeList.contains(oldEdge)) {
                int index = this.edgeList.indexOf(oldEdge);
                this.edgeList.set(index, newEdge);
                index = this.vertexList.indexOf(oldEdge.getSrc());
                int len = this.graph.get(index).size();
                for (int i = 0; i < len; i++) {
                    if (this.graph.get(index).get(i).vertex.equals(oldEdge.getDest())) {
                        this.graph.get(index).get(i).distance = newEdge.getWeight();
                        break;
                    }
                }
            }
        }
    }
}