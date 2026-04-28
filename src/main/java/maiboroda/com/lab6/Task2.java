package maiboroda.com.lab6;

import java.util.PriorityQueue;
import java.util.Random;

public class Task2 {
    public static int[] heapSortPQ(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : arr) pq.add(x);
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = pq.poll();
        return result;
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    public static int[] generate(int size) {
        Random rnd = new Random(42);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rnd.nextInt(10000);
        return arr;
    }

    public static long measurePQ(int size) {
        int RUNS = 5;
        long total = 0;
        for (int r = 0; r < RUNS; r++) {
            int[] arr = generate(size);
            long s = System.nanoTime();
            heapSortPQ(arr);
            total += System.nanoTime() - s;
        }
        return total / RUNS;
    }

    public static long measureClassic(int size) {
        int RUNS = 5;
        long total = 0;
        for (int r = 0; r < RUNS; r++) {
            int[] arr = generate(size);
            long s = System.nanoTime();
            heapSort(arr);
            total += System.nanoTime() - s;
        }
        return total / RUNS;
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 2: PriorityQueue vs Класичний HeapSort ===");
        System.out.printf("%-12s %-20s %-20s%n", "Розмір N", "PQ (нс)", "Класичний (нс)");
        System.out.println("-".repeat(52));

        int N = 100;
        int[] sizes = {N, N * N, N * N * N};
        for (int size : sizes) {
            long tPQ = measurePQ(size);
            long tClassic = measureClassic(size);
            System.out.printf("%-12d %-20d %-20d%n", size, tPQ, tClassic);
        }
    }
}
