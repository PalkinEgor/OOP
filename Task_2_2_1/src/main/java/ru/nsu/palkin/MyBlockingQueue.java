package ru.nsu.palkin;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * My blocking queue class.
 *
 * @param <T> - type of queue items
 */
public class MyBlockingQueue<T> {
    private ArrayList<T> queue;
    private int size;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    /**
     * Class constructor.
     *
     * @param size - size
     */
    public MyBlockingQueue(int size) {
        this.queue = new ArrayList<>();
        this.size = size;
        this.lock = new ReentrantLock();
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
    }

    /**
     * Class constructor.
     */
    public MyBlockingQueue() {
        this.queue = new ArrayList<>();
        this.size = Integer.MAX_VALUE;
        this.lock = new ReentrantLock();
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
    }

    /**
     * Get element method.
     *
     * @return element of the queue
     */
    public T get() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.queue.isEmpty()) {
                this.notEmpty.await();
            }
            T result = this.queue.get(0);
            this.queue.remove(0);
            this.notFull.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Put element method.
     *
     * @param object - object
     */
    public void put(T object) throws InterruptedException {
        this.lock.lock();
        try {
            while (this.queue.size() == this.size) {
                this.notFull.await();
            }
            this.queue.add(object);
            this.notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Get size method.
     * Using only in synchronized block!
     *
     * @return size
     */
    public int size() {
        this.lock.lock();
        int result = this.queue.size();
        this.lock.unlock();
        return result;
    }

    /**
     * Remove method.
     *
     * @param object - object
     */
    public void remove(T object) {
        this.lock.lock();
        this.queue.remove(object);
        this.lock.unlock();
    }
}
