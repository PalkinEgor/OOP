package ru.nsu.palkin;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Data preprocessor class.
 */
public final class DataPreprocessor {
    /**
     * Method that separates array into pieces.
     *
     * @param array  - array
     * @param chunks - number of pieces
     * @return queue with strings
     */
    public static BlockingQueue<String> arraySeparator(int[] array, int chunks)
            throws InterruptedException {
        int subArrLen = array.length / chunks;
        int residual = array.length % chunks;
        BlockingQueue<String> result = new ArrayBlockingQueue<>(chunks);
        int previousPos = 0;
        for (int i = 0; i < chunks; i++) {
            if (i < residual) {
                result.put(makeString(Arrays.copyOfRange(array, previousPos, previousPos
                        + subArrLen + 1)));
                previousPos = previousPos + subArrLen + 1;
            } else {
                result.put(makeString(Arrays.copyOfRange(array, previousPos,
                        previousPos + subArrLen)));
                previousPos = previousPos + subArrLen;
            }
        }
        return result;
    }

    /**
     * Method that makes string from array.
     *
     * @param array - array
     * @return string
     */
    public static String makeString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                result.append(array[i]);
            } else {
                result.append(array[i]).append(" ");
            }
        }
        return new String(result);
    }

    /**
     * Method that makes array from string.
     *
     * @param value - string
     * @return array
     */
    public static int[] makeArray(String value) {
        String[] values = value.split(" ");
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = Integer.parseInt(values[i]);
        }
        return result;
    }
}
