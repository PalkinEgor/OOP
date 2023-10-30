package ru.nsu.palkin;

import java.util.ArrayList;

/**
 * Class GraphIncidenceMatrix.
 *
 * @param <T> - type of class
 */
public class GraphIncidenceMatrix<T> extends Graph<T> {
    private ArrayList<ArrayList<EdgeStatus<T>>> graph;

    /**
     * Class constructor.
     *
     * @param vertexList - list of vertexes
     * @param edgeList   - list of edges
     */
    GraphIncidenceMatrix(ArrayList<Vertex<T>> vertexList, ArrayList<Edge<T>> edgeList) {
        this.graph = new ArrayList<>();
        this.vertexList = vertexList;
        this.edgeList = edgeList;
        int lenVertex = vertexList.size();
        int lenEdge = edgeList.size();
        for (int i = 0; i < lenVertex; i++) {
            ArrayList<EdgeStatus<T>> cur = new ArrayList<>();
            for (int j = 0; j < lenEdge; j++) {
                Vertex<T> src = edgeList.get(j).getSrc();
                Vertex<T> dest = edgeList.get(j).getDest();
                Vertex<T> vertex = vertexList.get(i);
                if (vertex.equals(src) && vertex.equals(dest)) {
                    cur.add(new EdgeStatus<>(edgeList.get(j), 2));
                } else if (vertex.equals(src)) {
                    cur.add(new EdgeStatus<>(edgeList.get(j), 1));
                } else if (vertex.equals(dest)) {
                    cur.add(new EdgeStatus<>(edgeList.get(j), -1));
                } else {
                    cur.add(new EdgeStatus<>(null, 0));
                }
            }
            this.graph.add(cur);
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
            ArrayList<EdgeStatus<T>> cur = new ArrayList<>();
            int len = this.edgeList.size();
            for (int i = 0; i < len; i++) {
                cur.add(new EdgeStatus<>(null, 0));
            }
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
            int index = this.vertexList.indexOf(vertex);
            int lenEdge = this.edgeList.size();
            for (int i = 0; i < lenEdge; i++) {
                EdgeStatus<T> cur = new EdgeStatus<>(null, 0);
                this.graph.get(index).set(i, cur);
            }

            int lenVertex = this.vertexList.size();
            for (int i = 0; i < this.edgeList.size(); i++) {
                int count = 0;
                for (int j = 0; j < lenVertex; j++) {
                    if (this.graph.get(j).get(i).status == 1
                            || this.graph.get(j).get(i).status == -1) {
                        count = count + 1;
                    }
                    if (this.graph.get(j).get(i).status == 2) {
                        count = count + 2;
                    }
                }
                if (count < 2) {
                    removeEdge(this.edgeList.get(i));
                    i--;
                }
            }
            this.graph.remove(index);
            this.vertexList.remove(vertex);
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
            int lenVertex = this.vertexList.size();
            int lenEdge = this.edgeList.size();
            for (int i = 0; i < lenVertex; i++) {
                for (int j = 0; j < lenEdge; j++) {
                    if (this.graph.get(i).get(j).edge != null) {
                        if (this.graph.get(i).get(j).edge.getSrc().equals(oldVertex)) {
                            this.graph.get(i).get(j).edge.setSrc(newVertex);
                        }
                        if (this.graph.get(i).get(j).edge.getDest().equals(oldVertex)) {
                            this.graph.get(i).get(j).edge.setDest(newVertex);
                        }
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
            int len = this.vertexList.size();
            Vertex<T> src = edge.getSrc();
            Vertex<T> dest = edge.getDest();
            for (int i = 0; i < len; i++) {
                if (this.vertexList.get(i).equals(src) && src.equals(dest)) {
                    this.graph.get(i).add(new EdgeStatus<>(edge, 2));
                } else if (this.vertexList.get(i).equals(src)) {
                    this.graph.get(i).add(new EdgeStatus<>(edge, 1));
                } else if (this.vertexList.get(i).equals(dest)) {
                    this.graph.get(i).add(new EdgeStatus<>(edge, -1));
                } else {
                    this.graph.get(i).add(new EdgeStatus<>(null, 0));
                }
            }
            this.edgeList.add(edge);
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
            int len = this.vertexList.size();
            int index = this.edgeList.indexOf(edge);
            for (int i = 0; i < len; i++) {
                this.graph.get(i).remove(index);
            }
            this.edgeList.remove(index);
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
                int len = this.vertexList.size();
                for (int i = 0; i < len; i++) {
                    if (this.graph.get(i).get(index).status != 0) {
                        this.graph.get(i).get(index).edge = newEdge;
                    }
                }
            }
        }
    }

    /**
     * EdgeStatus class.
     *
     * @param <T> type of class
     */
    private class EdgeStatus<T> {
        private Edge<T> edge;
        private int status;

        /**
         * Class constructor.
         *
         * @param edge   - edge
         * @param status - status
         */
        EdgeStatus(Edge<T> edge, int status) {
            this.edge = edge;
            this.status = status;
        }
    }
}