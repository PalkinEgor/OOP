package ru.nsu.palkin;

/**
 * Interface Graphable.
 *
 * @param <T> - type of object
 */
public interface Graphable<T> {
    void addVertice(T vert);

    void removeVertice(T vert);

    void changeVertice(T oldVert, T newVert);

    void addEdge(Edge<T> edge);

    void removeEdge(Edge<T> edge);

    void changeEdge(Edge<T> oldEdge, Edge<T> newEdge);

    StringBuilder shortestPaths(T vert);
}