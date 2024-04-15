package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Blocking queue test class.
 */
public class MyBlockingQueueTest {
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
    public void myBlockingQueueRemoveTest() throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.remove(2);
        assertEquals(2, queue.size());
    }
}
