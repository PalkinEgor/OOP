package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GraphAdjacencyList<T> implements Graphable<T> {

    private ArrayList<ArrayList<VerticeDistance<T>>> graph;
    private ArrayList<T> verticesList;
    private static final int inf = 2147483647;

    GraphAdjacencyList(ArrayList<T> vertices, ArrayList<Edge<T>> edges) {
        this.verticesList = vertices;
        this.graph = new ArrayList<>();
        int len = vertices.size();
        for (int i = 0; i < len; i++) {
            ArrayList<VerticeDistance<T>> t = new ArrayList<>();
            this.graph.add(t);
        }
        len = edges.size();
        for (int i = 0; i < len; i++) {
            T src = edges.get(i).src;
            T dest = edges.get(i).dest;
            int weight = edges.get(i).weight;
            VerticeDistance<T> cur = new VerticeDistance<>(dest, weight);
            this.graph.get(this.verticesList.indexOf(src)).add(cur);
        }
    }

    @Override
    public void addVertice(T vert) {
        if (!this.verticesList.contains(vert)) {
            this.verticesList.add(vert);
            ArrayList<VerticeDistance<T>> cur = new ArrayList<>();
            this.graph.add(cur);
        }
    }

    @Override
    public void removeVertice(T vert) {
        if (this.verticesList.contains(vert)) {
            int len = this.verticesList.size();
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < this.graph.get(i).size(); j++) {
                    if (this.graph.get(i).get(j).vertice == vert) {
                        this.graph.get(i).remove(j);
                        j--;
                    }
                }
            }
            this.graph.remove(this.verticesList.indexOf(vert));
            this.verticesList.remove(vert);
        }
    }

    @Override
    public void changeVertice(T oldVert, T newVert) {
        if (this.verticesList.contains(oldVert)) {
            int len = this.verticesList.size();
            for (int i = 0; i < len; i++) {
                int curLen = this.graph.get(i).size();
                for (int j = 0; j < curLen; j++) {
                    if (this.graph.get(i).get(j).vertice == oldVert) {
                        this.graph.get(i).get(j).vertice = newVert;
                    }
                }
            }
            this.verticesList.set(this.verticesList.indexOf(oldVert), newVert);
        }
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (this.verticesList.contains(edge.src) && this.verticesList.contains(edge.dest)) {
            int index = this.verticesList.indexOf(edge.src);
            this.graph.get(index).add(new VerticeDistance<>(edge.dest, edge.weight));
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        if (this.verticesList.contains(edge.src) && this.verticesList.contains(edge.dest)) {
            int index = this.verticesList.indexOf(edge.src);
            int len = this.graph.get(index).size();
            for (int i = 0; i < len; i++) {
                if (this.graph.get(index).get(i).vertice == edge.dest) {
                    this.graph.get(index).remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public void changeEdge(Edge<T> oldEdge, Edge<T> newEdge) {
        if (this.verticesList.contains(oldEdge.src) && this.verticesList.contains(oldEdge.dest)) {
            int index1 = this.verticesList.indexOf(oldEdge.src);
            int len = this.graph.get(index1).size();
            for (int i = 0; i < len; i++) {
                if (this.graph.get(index1).get(i).vertice == oldEdge.dest) {
                    this.graph.get(index1).get(i).distance = newEdge.weight;
                    break;
                }
            }
        }
    }

    @Override
    public StringBuilder shortestPaths(T vert) {
        int len = verticesList.size();
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
            int curLen = this.graph.get(shortest).size();
            for (int j = 0; j < curLen; j++) {
                int index = this.verticesList.indexOf(this.graph.get(shortest).get(j).vertice);
                if (distance[shortest] + this.graph.get(shortest).get(j).distance < distance[index]) {
                    distance[index] = distance[shortest] + this.graph.get(shortest).get(j).distance;
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