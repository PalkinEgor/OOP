package ru.nsu.palkin;

/**
 * Vertex class.
 *
 * @param <T> - type of vertex
 */
public class Vertex<T> {
    private T object;

    /**
     * Class constructor.
     *
     * @param object - object in vertex
     */
    Vertex(T object) {
        this.object = object;
    }

    /**
     * getter for object.
     *
     * @return object value
     */
    T getObject() {
        return this.object;
    }

    /**
     * setter for object.
     *
     * @param object - new object value
     */
    void setObject(T object) {
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

    /**
     * Override hashCode method.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.object.hashCode();
        result = result * 7;
        return result;
    }
}
