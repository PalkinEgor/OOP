package ru.nsu.palkin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class MainTest {
    @Test
    public void SimpleHeapsortTest() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{3, 2, 5, 1, 4}));
    }

    @Test
    public void RepeatedValuesTest() {
        assertArrayEquals(new int[]{0, 0, 0, 1, 1}, Main.heapsort(new int[]{0, 1, 0, 1, 0}));
    }

    @Test
    public void EmptyArrayTest() {
        assertArrayEquals(new int[]{}, Main.heapsort(new int[]{}));
    }

    @Test
    public void NegativeValuesTest() {
        assertArrayEquals(new int[]{-2, -1, 0, 1, 2}, Main.heapsort(new int[]{0, 1, -2, -1, 2}));
    }

    @Test
    public void SortedArrayTest() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void LongArrayTest() {
        assertArrayEquals(new int[]{1, 14, 16, 17, 19, 22, 27, 34, 42, 43, 50, 52, 55, 58, 59, 73, 75, 75, 80, 85}, Main.heapsort(new int[]{75, 80, 55, 59, 73, 17, 34, 27, 22, 85, 14, 43, 52, 19, 42, 1, 75, 58, 50, 16}));
    }
}
