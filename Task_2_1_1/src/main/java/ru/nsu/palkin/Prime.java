package ru.nsu.palkin;

public interface Prime {
    default boolean isNotPrime(int x) {
        int sqrt = (int) Math.sqrt(x);
        for (int i = 2; i <= sqrt; i++) {
            if (x % i == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNotPrime() throws InterruptedException;
}
