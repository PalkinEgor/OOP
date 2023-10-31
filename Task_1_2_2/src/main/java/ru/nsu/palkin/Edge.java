package ru.nsu.palkin;

/**
 * Edge class.
 *
 * @param <T> - type of edge
 */
public class Edge<T> {
    private Vertex<T> src;
    private Vertex<T> dest;
    private int weight;

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
     * Getter for first vertex.
     *
     * @return first vertex
     */
    Vertex<T> getSrc() {
        return this.src;
    }

    /**
     * Setter for first vertex.
     *
     * @param src - new first vertex
     */
    void setSrc(Vertex<T> src) {
        this.src = src;
    }

    /**
     * Getter for second vertex.
     *
     * @return second vertex
     */
    Vertex<T> getDest() {
        return this.dest;
    }

    /**
     * Setter for second vertex.
     *
     * @param dest - new second vertex
     */
    void setDest(Vertex<T> dest) {
        this.dest = dest;
    }

    /**
     * Getter for weight.
     *
     * @return weight
     */
    int getWeight() {
        return this.weight;
    }

    /**
     * Setter for weight.
     *
     * @param weight - new weight
     */
    void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Override equals method.
     * Edges are called the same if they have same first vertex, second vertex and weight
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
        return ((this.src.equals(edge.src)) && (this.dest.equals(edge.dest))
                && (this.weight == edge.weight));
    }

    /**
     * Override hashCode method.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.src.hashCode();
        result = 17 * result + this.dest.hashCode();
        result = result + this.weight;
        return result;
    }
}
