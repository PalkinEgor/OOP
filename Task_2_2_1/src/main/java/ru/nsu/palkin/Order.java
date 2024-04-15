package ru.nsu.palkin;

/**
 * Order class.
 */
public class Order {
    private int id;

    /**
     * Class constructor.
     *
     * @param id - id
     */
    public Order(int id) {
        this.id = id;
    }

    /**
     * Get id method.
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Override equals method.
     *
     * @param object - object
     * @return true or false
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Order order = (Order) object;
        return this.id == order.id;
    }

    /**
     * Override hashCode method.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        return 17 + 31 * this.id + this.id;
    }
}
