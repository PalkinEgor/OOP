package ru.nsu.palkin;

import java.util.Arrays;

/**
 * Class of the parallel solution with stream api.
 */
public class ParallelStreamExecution implements Prime {
    private int[] array;

    /**
     * Class constructor.
     *
     * @param array - array of numbers
     */
    public ParallelStreamExecution(int[] array) {
        this.array = array;
    }

    /**
     * Solution method.
     *
     * @return true or false
     */
    public boolean hasNotPrime() {
        return Arrays.stream(this.array).parallel().anyMatch(this::isNotPrime);
    }
}
