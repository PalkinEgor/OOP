package ru.nsu.palkin;

/**
 * Edge class.
 *
 * @param <T> - type of edge
 */
public class Edge<T> {
    public Vertex<T> src;
    public Vertex<T> dest;
    public int weight;

    /**
     * Class constructor.
     *
     * @param src    - first vertex
     * @param dest   - second vertex
     * @param weight - weight of vertex
     */
    Edge(Vertex<T> src, Vertex<T> dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Override equals method.
     *
     * @param obj - object
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Edge<Vertex<T>> edge = (Edge<Vertex<T>>) obj;
        return ((this.src.equals(edge.src)) && (this.dest.equals(edge.dest)) && (this.weight == edge.weight));
    }
}
