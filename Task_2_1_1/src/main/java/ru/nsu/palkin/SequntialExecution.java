package ru.nsu.palkin;

/**
 * Class of the sequential solution.
 */
public class SequntialExecution {
    private int[] array;

    /**
     * Class constructor.
     *
     * @param array - array of numbers
     */
    public SequntialExecution(int[] array) {
        this.array = array;
    }

    /**
     * Solution method.
     *
     * @return true or false
     */
    public boolean hasNotPrime() {
        int len = this.array.length;
        for (int k : this.array) {
            int sqrt = (int) Math.sqrt(k);
            for (int j = 2; j <= sqrt; j++) {
                if (k % j == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
