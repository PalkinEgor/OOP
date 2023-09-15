package ru.nsu.palkin;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void simpleHeapsortTest() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{3, 2, 5, 1, 4}));
    }

    @Test
    public void repeatedValuesTest() {
        assertArrayEquals(new int[]{0, 0, 0, 1, 1}, Main.heapsort(new int[]{0, 1, 0, 1, 0}));
    }

    @Test
    public void emptyArrayTest() {
        assertArrayEquals(new int[]{}, Main.heapsort(new int[]{}));
    }

    @Test
    public void negativeValuesTest() {
        assertArrayEquals(new int[]{-2, -1, 0, 1, 2}, Main.heapsort(new int[]{0, 1, -2, -1, 2}));
    }

    @Test
    public void sortedArrayTest() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void longArrayTest() {
        assertArrayEquals(new int[]{14, 17, 19, 27, 34, 42, 43, 50, 55, 58, 59, 75, 75, 85},
                Main.heapsort(new int[]{75, 55, 59, 17, 34, 27, 85, 14, 43, 19, 42, 75, 58, 50}));
    }
}
