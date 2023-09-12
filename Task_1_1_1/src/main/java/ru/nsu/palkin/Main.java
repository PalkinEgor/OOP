package ru.nsu.palkin;

public class Main {
    public static void heapify(int[] arr, int len, int i) {
        int largest = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int tmp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = tmp;

            heapify(arr, len, largest);
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