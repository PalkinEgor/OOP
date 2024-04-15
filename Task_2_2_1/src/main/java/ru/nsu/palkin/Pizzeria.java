package ru.nsu.palkin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Pizzeria class.
 */
public class Pizzeria {
    private int orderNumber;
    private MyBlockingQueue<Order> orderQueue;
    private MyBlockingQueue<Order> vaultQueue;
    private MyBlockingQueue<Order> remainingOrders;
    private ArrayList<Baker> bakerList;
    private ArrayList<Courier> courierList;
    private long workingTime;
    private Logger logger = Logger.getLogger(Pizzeria.class.getName());
    private FileHandler fileHandler;

    /**
     * Class constructor.
     *
     * @param bakerNumber     - number of bakers
     * @param bakerSpeed      - array with baker's speed
     * @param courierNumber   - number of couriers
     * @param courierCapacity - array with courier's capacity
     * @param courierSpeed    - array with courier's speed
     * @param vaultCapacity   - vault capacity
     * @param workingTime     - working time
     */
    public Pizzeria(int bakerNumber, int[] bakerSpeed, int courierNumber, int[] courierCapacity,
                    int[] courierSpeed, int vaultCapacity, long workingTime) {
        this.orderNumber = 0;
        this.orderQueue = new MyBlockingQueue<>();
        this.vaultQueue = new MyBlockingQueue<>(vaultCapacity);
        this.remainingOrders = new MyBlockingQueue<>();
        this.bakerList = new ArrayList<>();
        this.courierList = new ArrayList<>();
        this.workingTime = workingTime;
        for (int i = 0; i < bakerNumber; i++) {
            this.bakerList.add(new Baker(bakerSpeed[i]));
        }
        for (int i = 0; i < courierNumber; i++) {
            this.courierList.add(new Courier(courierCapacity[i], courierSpeed[i]));
        }
        try {
            this.fileHandler = new FileHandler("logs.log");
            this.fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(this.fileHandler);
        } catch (IOException e) {
            System.out.println("Error while creating handler: " + e.getMessage());
        }
    }

    /**
     * Baker thread.
     *
     * @param baker - baker
     * @return thread
     */
    private Thread bakerTask(Baker baker) {
        Runnable task = () -> {
            while (baker.bakerTask(this.orderQueue, this.vaultQueue, this.logger)) {
            }
            ;
        };
        Thread bakerThread = new Thread(task);
        bakerThread.start();
        return bakerThread;
    }

    /**
     * Courier thread.
     *
     * @param courier - courier
     * @return thread
     */
    private Thread courierTask(Courier courier) {
        Runnable task = () -> {
            while (courier.courierTask(this.vaultQueue, this.remainingOrders, this.logger)) {
            }
            ;
        };
        Thread courierThread = new Thread(task);
        courierThread.start();
        return courierThread;
    }

    /**
     * Start bakers method.
     *
     * @return array of baker's threads
     */
    private Thread[] startBakers() {
        Thread[] bakerThreads = new Thread[this.bakerList.size()];
        for (int i = 0; i < this.bakerList.size(); i++) {
            bakerThreads[i] = bakerTask(this.bakerList.get(i));
        }
        return bakerThreads;
    }

    /**
     * Add order method.
     */
    public void addOrder() throws InterruptedException {
        this.orderQueue.put(new Order(this.orderNumber));
        this.remainingOrders.put(new Order(this.orderNumber));
        this.orderNumber = this.orderNumber + 1;
    }

    /**
     * Start couriers method.
     *
     * @return array of courier's threads
     */
    private Thread[] startCouriers() {
        Thread[] courierThreads = new Thread[this.courierList.size()];
        for (int i = 0; i < this.courierList.size(); i++) {
            courierThreads[i] = courierTask(this.courierList.get(i));
        }
        return courierThreads;
    }

    /**
     * Close pizzeria method.
     *
     * @param bakerThreads   - baker's threads
     * @param courierThreads - courier's threads
     */
    private void closePizzeria(Thread[] bakerThreads, Thread[] courierThreads)
            throws InterruptedException {
        Runnable task = () -> {
            try {
                this.remainingOrders.check();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        for (int i = 0; i < this.bakerList.size(); i++) {
            bakerThreads[i].interrupt();
        }
        for (int i = 0; i < this.courierList.size(); i++) {
            courierThreads[i].interrupt();
        }
    }

    /**
     * Start pizzeria method.
     */
    public void startPizzeria() throws InterruptedException {
        Thread[] bakerThreads = startBakers();
        Thread[] courierThreads = startCouriers();
        Thread.sleep(this.workingTime);
        closePizzeria(bakerThreads, courierThreads);
    }
}
