package ru.nsu.palkin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class MainTest {
    @Test
    public void TestHeapsort() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{3, 2, 5, 1, 4}));
        assertArrayEquals(new int[]{0, 0, 0, 1, 1}, Main.heapsort(new int[]{0, 1, 0, 1, 0}));
        assertArrayEquals(new int[]{}, Main.heapsort(new int[]{}));
        assertArrayEquals(new int[]{-2, -1, 0, 1, 2}, Main.heapsort(new int[]{0, 1, -2, -1, 2}));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{1, 2, 3, 4, 5}));
    }
}
