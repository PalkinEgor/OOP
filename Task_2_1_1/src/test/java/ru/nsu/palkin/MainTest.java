package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

/**
 * Class with tests.
 */
public class MainTest {
    @Test
    public void sequentialLargeTest() {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        SequntialExecution solver = new SequntialExecution(array);
        assertFalse(solver.hasNotPrime());
    }

    @Test
    public void parallelLargeTest() throws InterruptedException {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        ParallelExecution solver = new ParallelExecution(array, 6);
        assertFalse(solver.hasNotPrime());
    }

    @Test
    public void parallelStreamLargeTest() {
        int[] array = new int[100000];
        Arrays.fill(array, Integer.MAX_VALUE);
        ParallelStreamExecution solver = new ParallelStreamExecution(array);
        assertFalse(solver.hasNotPrime());
    }

    @Test
    public void sequentialTrueTest() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SequntialExecution solver = new SequntialExecution(array);
        assertTrue(solver.hasNotPrime());
    }

    @Test
    public void parallelTrueTest() throws InterruptedException {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ParallelExecution solver = new ParallelExecution(array, 6);
        assertTrue(solver.hasNotPrime());
    }

    @Test
    public void parallelStreamTrueTest() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ParallelStreamExecution solver = new ParallelStreamExecution(array);
        assertTrue(solver.hasNotPrime());
    }
}
