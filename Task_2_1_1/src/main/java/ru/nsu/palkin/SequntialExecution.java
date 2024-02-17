package ru.nsu.palkin;

/**
 * Class of the sequential solution.
 */
public class SequntialExecution implements Prime {
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
            if (isNotPrime(k)) {
                return true;
            }
        }
        return false;
    }
}
