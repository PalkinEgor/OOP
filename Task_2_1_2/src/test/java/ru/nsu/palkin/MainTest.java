package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {
    /**
     * Boss task class.
     */
    private class BossTask implements Runnable {
        private Boss boss;
        private boolean result = false;

        /**
         * Class constructor.
         *
         * @param boss - object boss
         */
        private BossTask(Boss boss) {
            this.boss = boss;
        }

        /**
         * Run method.
         */
        @Override
        public void run() {
            try {
                this.result = this.boss.hasNotPrime();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Worker task class.
     */
    private class WorkerTask implements Runnable {
        private Worker worker;
        private boolean interrupt;

        /**
         * Class constructor.
         *
         * @param worker - object worker
         */
        private WorkerTask(Worker worker, boolean interrupt) {
            this.worker = worker;
            this.interrupt = interrupt;
        }

        /**
         * Run method.
         */
        @Override
        public void run() {
            try {
                this.worker.receiveSignal();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.worker.calculateTask(interrupt);
        }
    }

    /**
     * Support method for constructing tests.
     *
     * @param array        - array of numbers
     * @param workersCount - number of workers
     * @param chunks       - chunks
     * @param result       - expected result
     */
    public void testConstructor(int[] array, int workersCount, int chunks,
                                boolean result, boolean interrupt)
            throws InterruptedException {
        Boss boss = new Boss("230.0.0.0", 8000, 8001, array, chunks);
        BossTask bossTask = new BossTask(boss);
        Thread bossThread = new Thread(bossTask);
        WorkerTask[] workerTasks = new WorkerTask[workersCount];
        Thread[] workerThread = new Thread[workersCount];
        for (int i = 0; i < workersCount; i++) {
            Worker worker = new Worker("230.0.0.0", 8000);
            if (interrupt && i == 0) {
                workerTasks[i] = new WorkerTask(worker, true);
            } else {
                workerTasks[i] = new WorkerTask(worker, false);
            }
            workerThread[i] = new Thread(workerTasks[i]);
            workerThread[i].start();
        }
        Thread.sleep(1000);
        bossThread.start();
        bossThread.join();
        for (int i = 0; i < workersCount; i++) {
            workerThread[i].join();
        }

        if (result) {
            assertTrue(bossTask.result);
        } else {
            assertFalse(bossTask.result);
        }
    }

    @Test
    public void trueTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[10] = 4;
        testConstructor(array, 5, 10, true, false);
    }

    @Test
    public void falseTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 5, 10, false, false);
    }

    @Test
    public void oneWorkerTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 1, 10, false, false);
    }

    @Test
    public void notMultipleNumberOfChunksTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 5, 13, false, false);
    }

    @Test
    public void equalNumberOfWorkersAndChunksTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 5, 5, false, false);
    }

    @Test
    public void interruptTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[10] = 4;
        testConstructor(array, 5, 5, true, true);
    }

    @Test
    public void isPrimeTest() {
        int[] array = new int[1000];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[10] = 4;
        DataCalculator calculator = new DataCalculator(array);
        assertTrue(calculator.getResult());
    }
}
