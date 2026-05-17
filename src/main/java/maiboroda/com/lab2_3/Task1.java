package maiboroda.com.lab2_3;

import java.util.Scanner;

public class Task1 {
    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static long arrangement(int n, int k) {
        if (k > n) return 0;
        return factorial(n) / factorial(n - k);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Завдання 1: Розміщення без повторень ===");
        System.out.println("Задача: 7 кураторів, куратор англомовної групи — з 2 викладачів\n");

        System.out.print("Введіть загальну кількість кураторів (n): ");
        int n = sc.nextInt();
        System.out.print("Введіть кількість кандидатів для англомовної групи (m): ");
        int m = sc.nextInt();

        long step1 = m;
        long step2 = factorial(n - 1);

        long total = step1 * step2;

        System.out.println("\nРозв'язок (правило множення):");
        System.out.printf("Крок 1 — обираємо куратора англомовної групи: %d способи%n", step1);
        System.out.printf("Крок 2 — розставляємо решту %d кураторів на %d групи: %d! = %d%n",
                n - 1, n - 1, n - 1, step2);
        System.out.printf("%nВідповідь: %d × %d = %d способів%n", step1, step2, total);

        System.out.println("\n--- Перевірка через A(n,k) ---");
        System.out.printf("A(%d,%d) = %d! / %d! = %d%n",
                n, n, n, 0, factorial(n));
        System.out.printf("З обмеженням на англомовну групу: 2 × 6! = 2 × %d = %d%n",
                factorial(n - 1), total);
    }
}
