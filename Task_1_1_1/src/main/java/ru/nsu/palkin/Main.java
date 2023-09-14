package ru.nsu.palkin;

public class Main {
    public static void heapify(int[] arr, int len, int i) {
        int largest_index = i;
        int left_child_index = i * 2 + 1;
        int right_child_index = i * 2 + 2;
        if (left_child_index < len && arr[left_child_index] > arr[largest_index]) {
            largest_index = left_child_index;
        }
        if (right_child_index < len && arr[right_child_index] > arr[largest_index]) {
            largest_index = right_child_index;
        }
        if (largest_index != i) {
            int tmp = arr[largest_index];
            arr[largest_index] = arr[i];
            arr[i] = tmp;

            heapify(arr, len, largest_index);
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