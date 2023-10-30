package ru.nsu.palkin;

/**
 * Vertex class.
 *
 * @param <T> - type of vertex
 */
public class Vertex<T> {
    public T object;

    /**
     * Class constructor.
     *
     * @param object - object in vertex
     */
    Vertex(T object) {
        this.object = object;
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
        Vertex<T> vertex = (Vertex<T>) obj;
        return this.object.equals(vertex.object);
    }
}
