package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Class with tests.
 */
public class MainTest {

    /**
     * Support method for tests.
     *
     * @param array - array of numbers
     * @param flag  - true or false flag for assert
     */
    public void supFunc(int[] array, boolean flag) throws InterruptedException {
        List<Prime> objectList = new ArrayList<>();
        objectList.add(new SequntialExecution(array));
        objectList.add(new ParallelExecution(array, 6));
        objectList.add(new ParallelStreamExecution(array));
        for (Prime obj : objectList) {
            if (flag) {
                assertTrue(obj.hasNotPrime());
            } else {
                assertFalse(obj.hasNotPrime());
            }
        }
    }

    @Test
    public void largeTest() throws InterruptedException {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        supFunc(array, false);
    }

    @Test
    public void trueTest() throws InterruptedException {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        supFunc(array, true);
    }

    @Test
    public void oneNotPrimeNumber() throws InterruptedException {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[99999] = 4;
        supFunc(array, true);
    }

    @Test
    public void oneThreadTest() throws InterruptedException {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        ParallelExecution solver = new ParallelExecution(array, 1);
        assertFalse(solver.hasNotPrime());
    }

    @Test
    public void tooManyThreads() throws InterruptedException {
        int[] array = new int[]{1, 2, 3};
        ParallelExecution solver = new ParallelExecution(array, 6);
        assertFalse(solver.hasNotPrime());
    }
}
