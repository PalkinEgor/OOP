package ru.nsu.palkin;

/**
 * Data calculator class.
 */
public class DataCalculator {
    private int[] array;

    /**
     * Class constructor.
     *
     * @param array - array
     */
    public DataCalculator(int[] array) {
        this.array = array;
    }

    /**
     * Number is prime or not.
     *
     * @param number - number
     * @return true or false
     */
    private boolean isPrime(int number) {
        int sqrt = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Has array of numbers not prime number.
     *
     * @param array - array
     * @return true or false
     */
    private boolean hasNotPrime(int[] array) {
        for (int j : array) {
            if (!isPrime(j)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get result method.
     *
     * @return true or false
     */
    public boolean getResult() {
        return hasNotPrime(this.array);
    }
}
