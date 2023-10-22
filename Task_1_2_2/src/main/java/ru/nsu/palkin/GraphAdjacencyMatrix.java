package ru.nsu.palkin;

import java.util.*;

public class GraphAdjacencyMatrix<T> implements Graphable<T> {

    private ArrayList<ArrayList<Integer>> graph;
    private ArrayList<T> verticesList;
    private static final int inf = 2147483647;

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

    @Override
    public void changeVertice(T oldVert, T newVert) {
        if (this.verticesList.contains(oldVert)) {
            int index = this.verticesList.indexOf(oldVert);
            this.verticesList.set(index, newVert);
        }
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (this.verticesList.contains(edge.src) && this.verticesList.contains(edge.dest)) {
            int row = this.verticesList.indexOf(edge.src);
            int col = this.verticesList.indexOf(edge.dest);
            this.graph.get(row).set(col, edge.weight);
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        if (this.verticesList.contains(edge.src) && this.verticesList.contains(edge.dest)) {
            int row = this.verticesList.indexOf(edge.src);
            int col = this.verticesList.indexOf(edge.dest);
            this.graph.get(row).set(col, -1);
        }
    }

    @Override
    public void changeEdge(Edge<T> oldEdge, Edge<T> newEdge) {
        if (this.verticesList.contains(oldEdge.src) && this.verticesList.contains(oldEdge.dest)) {
            int row = this.verticesList.indexOf(oldEdge.src);
            int col = this.verticesList.indexOf(oldEdge.dest);
            this.graph.get(row).set(col, newEdge.weight);
        }
    }

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
                    result.append(map.get(i).vertice).append("(").append(map.get(i).distance).append("), ");
                } else {
                    result.append(map.get(i).vertice).append("(").append("infinity").append("), ");
                }
            } else {
                if (map.get(i).distance != inf) {
                    result.append(map.get(i).vertice).append("(").append(map.get(i).distance).append(")]");
                } else {
                    result.append(map.get(i).vertice).append("(").append("infinity").append(")]");
                }
            }
        }
        return result;
    }

    private class VerticeDistance<T> implements Comparable<VerticeDistance> {
        private T vertice;
        private int distance;

        VerticeDistance(T vertice, int distance) {
            this.vertice = vertice;
            this.distance = distance;
        }

        @Override
        public int compareTo(VerticeDistance verticeDistance) {
            return this.distance - verticeDistance.distance;
        }
    }
}