package ru.nsu.palkin;

/**
 * Class edge.
 *
 * @param <T> - objects in edge
 */
public class Edge<T> {
    public T src;
    public T dest;
    public int weight;

    /**
     * Class constructor.
     *
     * @param src    - begin of the edge
     * @param dest   - end of the edge
     * @param weight - weight of the edge
     */
    Edge(T src, T dest, int weight) {
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
        Edge<T> edge = (Edge<T>) obj;
        return (this.src == edge.src) && (this.dest == edge.dest) && (this.weight == edge.weight);
    }
}