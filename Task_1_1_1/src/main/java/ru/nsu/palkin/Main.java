package ru.nsu.palkin;

public class Main {
    public static void heapify(int[] arr, int len, int i) {
        int largestIndex = i;
        int leftChildIndex = i * 2 + 1;
        int rightChildIndex = i * 2 + 2;
        if (leftChildIndex < len && arr[leftChildIndex] > arr[largestIndex]) {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < len && arr[rightChildIndex] > arr[largestIndex]) {
            largestIndex = rightChildIndex;
        }
        if (largestIndex != i) {
            int tmp = arr[largestIndex];
            arr[largestIndex] = arr[i];
            arr[i] = tmp;

            heapify(arr, len, largestIndex);
        }
    }

    public static int[] heapsort(int[] arr) {
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, len, i);
        }
        for (int i = 0; i < len; i++) {
            int tmp = arr[0];
            arr[0] = arr[len - 1 - i];
            arr[len - 1 - i] = tmp;

            heapify(arr, len - 1 - i, 0);
        }
        return arr;
    }

    public static void main(String[] args) {
    }
}
