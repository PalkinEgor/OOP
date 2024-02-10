package ru.nsu.palkin;

import java.util.Arrays;

/**
 * Class of the parallel solution with stream api.
 */
public class ParallelStreamExecution {
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
     * Is number prime or not method.
     *
     * @param a - number
     * @return true or false
     */
    private boolean isNotPrime(int a) {
        int sqrt = (int) Math.sqrt(a);
        for (int i = 2; i <= sqrt; i++) {
            if (a % i == 0) {
                return true;
            }
        }
        return false;
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
