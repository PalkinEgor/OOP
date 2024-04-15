package ru.nsu.palkin;

import org.junit.jupiter.api.Test;

/**
 * Pizzeria test class.
 */
public class PizzeriaTest {
    /**
     * Get pizzeria method.
     *
     * @param bakerNumber     - number of bakers
     * @param bakerSpeed      - baker speed
     * @param courierNumber   - number of couriers
     * @param courierSpeed    - courier speed
     * @param courierCapacity - courier capacity
     * @param vaultCapacity   - capacity of vault
     * @param workingTime     - working time
     * @return pizzeria
     */
    private Pizzeria getPizzeria(int bakerNumber, int[] bakerSpeed, int courierNumber,
                                 int[] courierSpeed, int[] courierCapacity,
                                 int vaultCapacity, long workingTime) {
        PizzeriaJson pizzeriaJson = new PizzeriaJson(bakerNumber, bakerSpeed, courierNumber,
                courierSpeed, courierCapacity, vaultCapacity, workingTime);
        JsonConverter jsonConverter = new JsonConverter(pizzeriaJson, "file.json");
        jsonConverter.serialization();
        return jsonConverter.deserialization();
    }

    @Test
    public void pizzeriaTest() throws InterruptedException {
        int bakerNumber = 5;
        int[] bakerSpeed = new int[]{100, 150, 200, 250, 300};
        int courierNumber = 2;
        int[] courierSpeed = new int[]{300, 400};
        int[] courierCapacity = new int[]{2, 2};
        int vaultCapacity = 4;
        long workingTime = 5000;
        Pizzeria pizzeria = getPizzeria(bakerNumber, bakerSpeed, courierNumber, courierSpeed,
                courierCapacity, vaultCapacity, workingTime);
        Runnable pizzeriaTask = () -> {
            try {
                pizzeria.startPizzeria();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread pizzeriaThread = new Thread(pizzeriaTask);
        pizzeriaThread.start();
        for (int i = 0; i < 50; i++) {
            pizzeria.addOrder();
        }
        pizzeriaThread.join();
    }

    @Test
    public void pizzeriaOrderAfterClosingTest() throws InterruptedException {
        int bakerNumber = 5;
        int[] bakerSpeed = new int[]{100, 150, 200, 250, 300};
        int courierNumber = 2;
        int[] courierSpeed = new int[]{300, 400};
        int[] courierCapacity = new int[]{2, 2};
        int vaultCapacity = 4;
        long workingTime = 5000;
        Pizzeria pizzeria = getPizzeria(bakerNumber, bakerSpeed, courierNumber, courierSpeed,
                courierCapacity, vaultCapacity, workingTime);
        Runnable pizzeriaTask = () -> {
            try {
                pizzeria.startPizzeria();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread pizzeriaThread = new Thread(pizzeriaTask);
        pizzeriaThread.start();
        for (int i = 0; i < 10; i++) {
            pizzeria.addOrder();
        }
        Thread.sleep(workingTime * 2);
        pizzeria.addOrder();
        pizzeriaThread.join();
    }

    @Test
    public void pizzeriaBigGapBetweenOrdersTest() throws InterruptedException {
        int bakerNumber = 5;
        int[] bakerSpeed = new int[]{100, 150, 200, 250, 300};
        int courierNumber = 2;
        int[] courierSpeed = new int[]{300, 400};
        int[] courierCapacity = new int[]{2, 2};
        int vaultCapacity = 4;
        long workingTime = 5000;
        Pizzeria pizzeria = getPizzeria(bakerNumber, bakerSpeed, courierNumber, courierSpeed,
                courierCapacity, vaultCapacity, workingTime);
        Runnable pizzeriaTask = () -> {
            try {
                pizzeria.startPizzeria();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread pizzeriaThread = new Thread(pizzeriaTask);
        pizzeriaThread.start();
        pizzeria.addOrder();
        Thread.sleep(3000);
        pizzeria.addOrder();
        pizzeriaThread.join();
    }
}
