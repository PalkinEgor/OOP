package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void graphAdjacencyMatrixTest() {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();
        try {
            File file = new File("data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int vertexLen = Integer.parseInt(br.readLine());
            for (int i = 0; i < vertexLen; i++) {
                String cur = br.readLine();
                vertices.add(new Vertex<>(cur));
            }
            int edgeLen = Integer.parseInt(br.readLine());
            for (int i = 0; i < edgeLen; i++) {
                String[] curArr = br.readLine().split(" ");
                edges.add(new Edge<>(new Vertex<>(curArr[0]), new Vertex<>(curArr[1]),
                        Integer.parseInt(curArr[2])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        GraphAdjacencyMatrix<String> graph = new GraphAdjacencyMatrix<>(vertices, edges);
        graph.addVertex(new Vertex<>("F"));
        graph.addEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10));
        graph.removeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10),
                new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 11));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        ArrayList<Vertex<String>> expectedResult = new ArrayList<>();
        expectedResult.add(new Vertex<>("A"));
        expectedResult.add(new Vertex<>("F"));
        expectedResult.add(new Vertex<>("D"));
        expectedResult.add(new Vertex<>("B"));
        ArrayList<Vertex<String>> result = graph.shortestPath(new Vertex<>("A"));
        assertArrayEquals(result.toArray(), expectedResult.toArray());
    }

    @Test
    public void graphAdjacencyListTest() {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();
        try {
            File file = new File("data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int vertexLen = Integer.parseInt(br.readLine());
            for (int i = 0; i < vertexLen; i++) {
                String cur = br.readLine();
                vertices.add(new Vertex<>(cur));
            }
            int edgeLen = Integer.parseInt(br.readLine());
            for (int i = 0; i < edgeLen; i++) {
                String[] curArr = br.readLine().split(" ");
                edges.add(new Edge<>(new Vertex<>(curArr[0]), new Vertex<>(curArr[1]),
                        Integer.parseInt(curArr[2])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        GraphAdjacencyList<String> graph = new GraphAdjacencyList<>(vertices, edges);
        graph.addVertex(new Vertex<>("F"));
        graph.addEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10));
        graph.removeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10),
                new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 11));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        ArrayList<Vertex<String>> expectedResult = new ArrayList<>();
        expectedResult.add(new Vertex<>("A"));
        expectedResult.add(new Vertex<>("F"));
        expectedResult.add(new Vertex<>("D"));
        expectedResult.add(new Vertex<>("B"));
        ArrayList<Vertex<String>> result = graph.shortestPath(new Vertex<>("A"));
        assertArrayEquals(result.toArray(), expectedResult.toArray());
    }

    @Test
    public void graphIncidenceMatrixTest() {
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        ArrayList<Edge<String>> edges = new ArrayList<>();
        try {
            File file = new File("data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int vertexLen = Integer.parseInt(br.readLine());
            for (int i = 0; i < vertexLen; i++) {
                String cur = br.readLine();
                vertices.add(new Vertex<>(cur));
            }
            int edgeLen = Integer.parseInt(br.readLine());
            for (int i = 0; i < edgeLen; i++) {
                String[] curArr = br.readLine().split(" ");
                edges.add(new Edge<>(new Vertex<>(curArr[0]), new Vertex<>(curArr[1]),
                        Integer.parseInt(curArr[2])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        GraphIncidenceMatrix<String> graph = new GraphIncidenceMatrix<>(vertices, edges);
        graph.addVertex(new Vertex<>("F"));
        graph.addEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10));
        graph.removeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10));
        graph.removeVertex(new Vertex<>("D"));
        graph.changeEdge(new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 10),
                new Edge<>(new Vertex<>("A"), new Vertex<>("F"), 11));
        graph.changeVertex(new Vertex<>("C"), new Vertex<>("D"));

        ArrayList<Vertex<String>> expectedResult = new ArrayList<>();
        expectedResult.add(new Vertex<>("A"));
        expectedResult.add(new Vertex<>("F"));
        expectedResult.add(new Vertex<>("D"));
        expectedResult.add(new Vertex<>("B"));
        ArrayList<Vertex<String>> result = graph.shortestPath(new Vertex<>("A"));
        assertArrayEquals(result.toArray(), expectedResult.toArray());
    }

    @Test
    public void getVertexTest() {
        Vertex<String> vertex = new Vertex<>("A");
        assertEquals(vertex.getObject(), "A");
    }

    @Test
    public void setVertexTest() {
        Vertex<String> vertex = new Vertex<>("B");
        Vertex<String> expectedResult = new Vertex<>("A");
        vertex.setObject("A");
        assertEquals(vertex, expectedResult);
    }

    @Test
    public void setWeightEdgeTest() {
        Edge<String> edge = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10);
        Edge<String> expectedResult = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 15);
        edge.setWeight(15);
        assertEquals(edge, expectedResult);
    }

    @Test
    public void vertexHashCodeTest() {
        Vertex<String> vertex1 = new Vertex<>("A");
        Vertex<String> vertex2 = new Vertex<>("A");
        assertEquals(vertex1.hashCode(), vertex2.hashCode());
    }

    @Test
    public void edgeHashCodeTest() {
        Edge<String> edge1 = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10);
        Edge<String> edge2 = new Edge<>(new Vertex<>("A"), new Vertex<>("B"), 10);
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }
}
