package maiboroda.com.lab6;

import java.util.PriorityQueue;
import java.util.Random;

public class Task1 {
    public static int[] heapSortPQ(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : arr) pq.add(x);
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = pq.poll();
        return result;
    }

    public static int[] generate(int size) {
        Random rnd = new Random(42);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = rnd.nextInt(10000);
        return arr;
    }

    public static long measure(int size) {
        int RUNS = 5;
        long total = 0;
        for (int r = 0; r < RUNS; r++) {
            int[] arr = generate(size);
            long start = System.nanoTime();
            heapSortPQ(arr);
            total += System.nanoTime() - start;
        }
        return total / RUNS;
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 1: Heap Sort (PriorityQueue) ===");
        System.out.printf("%-12s %-20s%n", "Розмір N", "Час (нс)");
        System.out.println("-".repeat(32));

        int N = 100;
        int[] sizes = {N, N * N, N * N * N};
        for (int size : sizes) {
            long time = measure(size);
            System.out.printf("%-12d %-20d%n", size, time);
        }
    }
}
