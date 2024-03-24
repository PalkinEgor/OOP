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

        /**
         * Class constructor.
         *
         * @param worker - object worker
         */
        private WorkerTask(Worker worker) {
            this.worker = worker;
        }

        /**
         * Run method.
         */
        @Override
        public void run() {
            try {
                this.worker.receiveSignal();
                this.worker.calculateTask();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
    public void testConstructor(int[] array, int workersCount, int chunks, boolean result) throws InterruptedException {
        Boss boss = new Boss("230.0.0.0", 8000, 8001, array, chunks);
        BossTask bossTask = new BossTask(boss);
        Thread bossThread = new Thread(bossTask);
        WorkerTask[] workerTasks = new WorkerTask[workersCount];
        Thread[] workerThread = new Thread[workersCount];
        for (int i = 0; i < workersCount; i++) {
            Worker worker = new Worker("230.0.0.0", 8000);
            workerTasks[i] = new WorkerTask(worker);
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
        testConstructor(array, 5, 10, true);
    }

    @Test
    public void falseTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 5, 10, false);
    }

    @Test
    public void oneWorkerTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 1, 10, false);
    }

    @Test
    public void notMultipleNumberOfChunksTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 5, 13, false);
    }

    @Test
    public void equalNumberOfWorkersAndChunksTest() throws InterruptedException {
        int[] array = new int[10000];
        Arrays.fill(array, Integer.MAX_VALUE);
        testConstructor(array, 5, 5, false);
    }
}
