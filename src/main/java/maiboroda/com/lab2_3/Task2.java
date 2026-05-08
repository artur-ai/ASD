package maiboroda.com.lab2_3;

import java.util.Scanner;

public class Task2 {

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static long combination(int n, int k) {
        if (k > n) return 0;
        long result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }

    public static long combinationWithRepetition(int n, int k) {
        return combination(n + k - 1, k);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Завдання 2: Комбінації з повтореннями ===");
        System.out.println("Задача: букви a-k (11 букв), слово довжиною 10, букви можуть повторюватись\n");

        System.out.print("Введіть кількість різних букв (n): ");
        int n = sc.nextInt();
        System.out.print("Введіть довжину слова (k): ");
        int k = sc.nextInt();

        long result = combinationWithRepetition(n, k);

        System.out.println("\nРозв'язок:");
        System.out.println("Тип вибірки: комбінація з повтореннями");
        System.out.printf("Формула: C'(n,k) = C(n+k-1, k) = C(%d+%d-1, %d) = C(%d, %d)%n",
                n, k, k, n + k - 1, k);
        System.out.printf("C(%d, %d) = %d! / (%d! × %d!) = %d%n",
                n + k - 1, k, n + k - 1, k, n - 1, result);
        System.out.printf("%nВідповідь: %d способів%n", result);
    }
}
