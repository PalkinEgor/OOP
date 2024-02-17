package ru.nsu.palkin;

/**
 * Prime interface.
 */
public interface Prime {
    /**
     * Default method that check number is prime or not.
     *
     * @param x - number
     * @return true or false
     */
    default boolean isNotPrime(int x) {
        int sqrt = (int) Math.sqrt(x);
        for (int i = 2; i <= sqrt; i++) {
            if (x % i == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that check is any number not prime.
     *
     * @return true or false
     */
    public boolean hasNotPrime() throws InterruptedException;
}
