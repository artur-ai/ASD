package maiboroda.com.lab2_1;

import java.util.Scanner;

public class Task1 {

    static double f(double x) {
        return Math.sqrt(6 * x + 5);
    }

    static double trapezoid(double a, double b, double h) {
        double sum = (f(a) + f(b)) / 2.0;
        for (double x = a + h; x < b; x += h)
            sum += f(x);
        return sum * h;
    }

    static double rectangle(double a, double b, double h) {
        double sum = 0;
        for (double x = a; x < b; x += h)
            sum += f(x + h / 2.0);
        return sum * h;
    }

    static double simpson(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);
        if (n % 2 != 0) n++;
        double step = (b - a) / n;
        double sum = f(a) + f(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * step;
            sum += (i % 2 == 0) ? 2 * f(x) : 4 * f(x);
        }
        return sum * step / 3.0;
    }

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Завдання 1: Чисельне інтегрування ∫√(6x+5)dx ===");
        System.out.print("Введіть a (початок): ");
        double a = sc.nextDouble();
        System.out.print("Введіть b (кінець):  ");
        double b = sc.nextDouble();
        System.out.print("Введіть h (крок):    ");
        double h = sc.nextDouble();

        System.out.println("\nРезультати:");
        System.out.printf("Метод трапецій:     %.6f%n", trapezoid(a, b, h));
        System.out.printf("Метод прямокутників:%.6f%n", rectangle(a, b, h));
        System.out.printf("Метод Сімпсона:     %.6f%n", simpson(a, b, h));

        double exact = Math.pow(6 * b + 5, 1.5) / 9.0 - Math.pow(6 * a + 5, 1.5) / 9.0;
        System.out.printf("Точне значення:     %.6f%n", exact);
    }
}
