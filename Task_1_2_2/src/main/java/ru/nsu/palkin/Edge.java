package ru.nsu.palkin;

public class Edge<T> {
    public T src;
    public T dest;
    public int weight;

    Edge(T src, T dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Edge<T> edge = (Edge<T>) obj;
        return (this.src == edge.src) && (this.dest == edge.dest) && (this.weight == edge.weight);
    }
}