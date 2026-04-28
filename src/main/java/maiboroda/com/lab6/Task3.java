package maiboroda.com.lab6;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.ArrayList;

public class Task3 {
    static final int SIZE = 10000;
    static final int RUNS = 10;

    static int[] random() {
        Random rnd = new Random(42);
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) arr[i] = rnd.nextInt(SIZE);
        return arr;
    }

    static int[] sorted() {
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) arr[i] = i;
        return arr;
    }

    static int[] reverseSorted() {
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) arr[i] = SIZE - i;
        return arr;
    }

    static long measurePQ(int[] input) {
        long total = 0;
        for (int r = 0; r < RUNS; r++) {
            int[] arr = Arrays.copyOf(input, input.length);
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            long s = System.nanoTime();
            for (int x : arr) pq.add(x);
            int[] res = new int[arr.length];
            for (int i = 0; i < arr.length; i++) res[i] = pq.poll();
            total += System.nanoTime() - s;
        }
        return total / RUNS;
    }

    static long measureClassic(int[] input) {
        long total = 0;
        for (int r = 0; r < RUNS; r++) {
            int[] arr = Arrays.copyOf(input, input.length);
            long s = System.nanoTime();
            heapSort(arr);
            total += System.nanoTime() - s;
        }
        return total / RUNS;
    }

    static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int lg = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[lg]) lg = l;
        if (r < n && arr[r] > arr[lg]) lg = r;
        if (lg != i) {
            int t = arr[i];
            arr[i] = arr[lg];
            arr[lg] = t;
            heapify(arr, n, lg);
        }
    }

    public static void main(String[] args) {
        int[] rnd = random();
        int[] asc = sorted();
        int[] desc = reverseSorted();

        System.out.println("=== Завдання 3: Найкращий / Середній / Найгірший (N=10000) ===");
        System.out.printf("%-20s %-20s %-20s%n", "Порядок", "PQ (нс)", "Класичний (нс)");
        System.out.println("-".repeat(60));

        System.out.printf("%-20s %-20d %-20d%n",
                "Відсортований", measurePQ(asc), measureClassic(asc));
        System.out.printf("%-20s %-20d %-20d%n",
                "Випадковий", measurePQ(rnd), measureClassic(rnd));
        System.out.printf("%-20s %-20d %-20d%n",
                "Зворотній", measurePQ(desc), measureClassic(desc));

        System.out.println("\nДля heap sort:");
        System.out.println("Найменший час  — відсортований масив (купа будується швидше)");
        System.out.println("Середній час   — випадковий порядок");
        System.out.println("Найбільший час — зворотній порядок (максимум обмінів)");
    }
}
