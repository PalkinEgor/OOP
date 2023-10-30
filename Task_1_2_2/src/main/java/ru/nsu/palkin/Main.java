package ru.nsu.palkin;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        vertices.add(new Vertex<>("A"));
        vertices.add(new Vertex<>("B"));
        vertices.add(new Vertex<>("C"));
        vertices.add(new Vertex<>("D"));
        ArrayList<Edge<String>> edges = new ArrayList<>();
        edges.add(new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10));
        edges.add(new Edge<>(new Vertex<>("B"), new Vertex<>("C"), 15));
        edges.add(new Edge<>(new Vertex<>("A"), new Vertex<>("C"), 30));
        edges.add(new Edge<>(new Vertex<>("C"), new Vertex<>("D"), 20));
        edges.add(new Edge<>(new Vertex<>("D"), new Vertex<>("A"), 25));

        GraphIncidenceMatrix<String> graph = new GraphIncidenceMatrix<>(vertices, edges);
        graph.addVertex(new Vertex<>("F"));
        graph.addEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10));
        graph.removeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10), new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 11));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        StringBuilder result = graph.shortestPath(new Vertex<>("A"));
        System.out.println(result);
    }
}