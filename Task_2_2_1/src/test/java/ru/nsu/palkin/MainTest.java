package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {

    @Test
    public void pizzeriaTest() throws InterruptedException {
        int bakerNumber = 5;
        int[] bakerSpeed = new int[]{100, 150, 200, 250, 300};
        int courierNumber = 2;
        int[] courierSpeed = new int[]{300, 400};
        int[] courierCapacity = new int[]{2, 2};
        int vaultCapacity = 4;
        long workingTime = 5000;

        PizzeriaJson pizzeriaJson = new PizzeriaJson(bakerNumber, bakerSpeed, courierNumber,
                courierSpeed, courierCapacity, vaultCapacity, workingTime);
        JsonConverter jsonConverter = new JsonConverter(pizzeriaJson, "file.json");
        jsonConverter.serialization();
        Pizzeria pizzeria = jsonConverter.deserialization();
        pizzeria.startPizzeria();
    }

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
    public void myBlockingQueueGetTest() throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);
        queue.put(10);
        assertEquals(queue.get(), 10);
    }

    @Test
    public void myBlockingQueueSizeTest() throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);
        queue.put(1);
        queue.put(2);
        queue.put(1);
        assertEquals(3, queue.size());
    }

    @Test
    public void myBlockingQueueTest() throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.remove(2);
        assertEquals(2, queue.size());
    }
}
