package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Order test class.
 */
public class OrderTest {
    @Test
    public void orderGetIdTest() {
        Order order = new Order(10);
        assertEquals(order.getId(), 10);
    }

    @Test
    public void orderEqualsTest() {
        Order order1 = new Order(10);
        Order order2 = new Order(10);
        assertEquals(order1, order2);
    }

    @Test
    public void orderHashCodeTest() {
        Order order1 = new Order(10);
        Order order2 = new Order(10);
        assertEquals(order1.hashCode(), order2.hashCode());
    }
}
