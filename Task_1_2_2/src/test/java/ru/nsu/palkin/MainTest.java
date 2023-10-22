package ru.nsu.palkin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void graphAdjacencyMatrixTest() {
        ArrayList<String> vertices = new ArrayList<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        ArrayList<Edge<String>> edges = new ArrayList<>();
        edges.add(new Edge<>("A", "B", 10));
        edges.add(new Edge<>("B", "C", 15));
        edges.add(new Edge<>("A", "C", 30));
        edges.add(new Edge<>("C", "D", 20));
        edges.add(new Edge<>("D", "A", 25));

        GraphAdjacencyMatrix<String> graph = new GraphAdjacencyMatrix<>(vertices, edges);
        graph.addVertice("F");
        graph.addEdge(new Edge<>("A", "F", 10));
        graph.removeEdge(new Edge<>("A", "B", 10));
        graph.removeVertice("D");
        graph.changeEdge(new Edge<>("A", "F", 10), new Edge<>("A", "F", 11));
        graph.changeVertice("C", "D");

        String expectedResult = "[A(0), F(11), D(30), B(infinity)]";
        StringBuilder result = graph.shortestPaths("A");
        assertEquals(expectedResult, result.toString());
    }

    @Test
    public void graphAdjacencyListTest() {
        ArrayList<String> vertices = new ArrayList<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        ArrayList<Edge<String>> edges = new ArrayList<>();
        edges.add(new Edge<>("A", "B", 10));
        edges.add(new Edge<>("B", "C", 15));
        edges.add(new Edge<>("A", "C", 30));
        edges.add(new Edge<>("C", "D", 20));
        edges.add(new Edge<>("D", "A", 25));

        GraphAdjacencyList<String> graph = new GraphAdjacencyList<>(vertices, edges);
        graph.addVertice("F");
        graph.addEdge(new Edge<>("A", "F", 10));
        graph.removeEdge(new Edge<>("A", "B", 10));
        graph.removeVertice("D");
        graph.changeEdge(new Edge<>("A", "F", 10), new Edge<>("A", "F", 11));
        graph.changeVertice("C", "D");

        String expectedResult = "[A(0), F(11), D(30), B(infinity)]";
        StringBuilder result = graph.shortestPaths("A");
        assertEquals(expectedResult, result.toString());
    }

    @Test
    public void graphIncidenceMatrix() {
        ArrayList<String> vertices = new ArrayList<>();
        vertices.add("A");
        vertices.add("B");
        vertices.add("C");
        vertices.add("D");
        ArrayList<Edge<String>> edges = new ArrayList<>();
        edges.add(new Edge<>("A", "B", 10));
        edges.add(new Edge<>("B", "C", 15));
        edges.add(new Edge<>("A", "C", 30));
        edges.add(new Edge<>("C", "D", 20));
        edges.add(new Edge<>("D", "A", 25));

        GraphIncidenceMatrix<String> graph = new GraphIncidenceMatrix<>(vertices, edges);
        graph.addVertice("F");
        graph.addEdge(new Edge<>("A", "F", 10));
        graph.removeEdge(new Edge<>("A", "B", 10));
        graph.removeVertice("D");
        graph.changeEdge(new Edge<>("A", "F", 10), new Edge<>("A", "F", 11));
        graph.changeVertice("C", "D");

        String expectedResult = "[A(0), F(11), D(30), B(infinity)]";
        StringBuilder result = graph.shortestPaths("A");
        assertEquals(expectedResult, result.toString());
    }
}
