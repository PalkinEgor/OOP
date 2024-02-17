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

    @Test
    public void largeTest() throws InterruptedException {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        List<Prime> objectList = new ArrayList<>();
        objectList.add(new SequntialExecution(array));
        objectList.add(new ParallelExecution(array, 6));
        objectList.add(new ParallelStreamExecution(array));
        for (Prime obj : objectList) {
            assertFalse(obj.hasNotPrime());
        }
    }

    @Test
    public void trueTest() throws InterruptedException {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Prime> objectList = new ArrayList<>();
        objectList.add(new SequntialExecution(array));
        objectList.add(new ParallelExecution(array, 6));
        objectList.add(new ParallelStreamExecution(array));
        for (Prime obj : objectList) {
            assertTrue(obj.hasNotPrime());
        }
    }

    @Test
    public void oneNotPrimeNumber() throws InterruptedException {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[99999] = 4;
        List<Prime> objectList = new ArrayList<>();
        objectList.add(new SequntialExecution(array));
        objectList.add(new ParallelExecution(array, 6));
        objectList.add(new ParallelStreamExecution(array));
        for (Prime obj : objectList) {
            assertTrue(obj.hasNotPrime());
        }
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
