package ru.nsu.palkin;

import java.util.logging.Logger;

/**
 * Baker class.
 */
public class Baker {
    private int time;

    /**
     * Class constructor.
     *
     * @param time - time
     */
    public Baker(int time) {
        this.time = time;
    }

    /**
     * Baker task.
     *
     * @param orders - orders
     * @param vault  - vault
     * @return true or false
     */
    public boolean bakerTask(MyBlockingQueue<Order> orders, MyBlockingQueue<Order> vault,
                             Logger logger) {
        try {
            Order order = orders.get();
            logger.info("Order " + order.getId() + " is cooking");
            Thread.sleep(this.time);
            logger.info("Order " + order.getId() + " is ready");
            vault.put(order);
            logger.info("Order " + order.getId() + " has been sent to the vault");
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
