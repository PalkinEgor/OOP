package ru.nsu.palkin;

import java.util.logging.Logger;

/**
 * Courier class.
 */
public class Courier {
    private int capacity;
    private int time;

    /**
     * Class constructor.
     *
     * @param capacity - capacity
     * @param time     - time
     */
    public Courier(int capacity, int time) {
        this.capacity = capacity;
        this.time = time;
    }

    /**
     * Courier task.
     *
     * @param vault           - vault
     * @param remainingOrders - remaining orders
     * @return true or false
     */
    public boolean courierTask(MyBlockingQueue<Order> vault,
                               MyBlockingQueue<Order> remainingOrders, Logger logger) {
        try {
            int pizzaCount = 0;
            synchronized (vault) {
                pizzaCount = Math.min(this.capacity, vault.size());
            }
            if (pizzaCount != 0) {
                Order[] orders = new Order[pizzaCount];
                for (int i = 0; i < pizzaCount; i++) {
                    orders[i] = vault.get();
                    logger.info("Order " + orders[i].getId() + " is on the way");
                }
                Thread.sleep((long) pizzaCount * this.time);
                for (int i = 0; i < pizzaCount; i++) {
                    logger.info("Order " + orders[i].getId() + " has been delivered");
                    remainingOrders.remove(orders[i]);
                }
            } else {
                Order currentOrder = vault.get();
                logger.info("Order " + currentOrder.getId() + " is on the way");
                Thread.sleep(this.time);
                logger.info("Order " + currentOrder.getId() + " has been delivered");
                remainingOrders.remove(currentOrder);
            }
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
