package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void graphAdjacencyMatrixTest() {
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

        GraphAdjacencyMatrix<String> graph = new GraphAdjacencyMatrix<>(vertices, edges);
        graph.addVertex(new Vertex<>("F"));
        graph.addEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10));
        graph.removeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10),
                new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 11));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        StringBuilder result = graph.shortestPath(new Vertex<>("A"));
        String expectedResult = "[A(0), F(11), D(30), B(infinity)]";
        assertEquals(expectedResult, result.toString());
    }

    @Test
    public void graphAdjacencyListTest() {
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

        GraphAdjacencyList<String> graph = new GraphAdjacencyList<>(vertices, edges);
        graph.addVertex(new Vertex<>("F"));
        graph.addEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10));
        graph.removeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10),
                new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 11));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        StringBuilder result = graph.shortestPath(new Vertex<>("A"));
        String expectedResult = "[A(0), F(11), D(30), B(infinity)]";
        assertEquals(expectedResult, result.toString());
    }

    @Test
    public void graphIncidenceMatrixTest() {
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
        graph.changeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10),
                new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 11));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        StringBuilder result = graph.shortestPath(new Vertex<>("A"));
        String expectedResult = "[A(0), F(11), D(30), B(infinity)]";
        assertEquals(expectedResult, result.toString());
    }
}
