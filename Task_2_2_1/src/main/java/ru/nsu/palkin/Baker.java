package ru.nsu.palkin;

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
    public boolean bakerTask(MyBlockingQueue<Order> orders, MyBlockingQueue<Order> vault) {
        try {
            Order order = orders.get();
            System.out.println("Order " + order.getId() + " is cooking");
            Thread.sleep(this.time);
            System.out.println("Order " + order.getId() + " is ready");
            vault.put(order);
            System.out.println("Order " + order.getId() + " has been sent to the vault");
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
