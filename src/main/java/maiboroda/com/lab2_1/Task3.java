package maiboroda.com.lab2_1;

import java.util.Scanner;

public class Task3 {

    static double f(double x, double y) {
        return (y * y - y) / x;
    }

    static void rungeKutta3(double x0, double y0, double xEnd, double h) {
        System.out.printf("%-12s %-15s%n", "x", "y");
        System.out.println("-".repeat(27));
        System.out.printf("%-12.4f %-15.6f%n", x0, y0);

        double x = x0, y = y0;
        while (x < xEnd - 1e-9) {
            double k1 = h * f(x, y);
            double k2 = h * f(x + h / 2.0, y + k1 / 2.0);
            double k3 = h * f(x + h, y - k1 + 2 * k2);
            y = y + (k1 + 4 * k2 + k3) / 6.0;
            x = x + h;
            System.out.printf("%-12.4f %-15.6f%n", x, y);
        }
    }

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Завдання 3: dy/dx = (y²-y)/x, Рунге-Кутта 3-го порядку ===");

        System.out.print("Початкове x0: ");
        double x0 = sc.nextDouble();
        System.out.print("Початкове y0: ");
        double y0 = sc.nextDouble();
        System.out.print("Кінцеве xEnd: ");
        double xEnd = sc.nextDouble();
        System.out.print("Крок h:       ");
        double h = sc.nextDouble();

        System.out.println("\nТаблиця розв'язку:");
        rungeKutta3(x0, y0, xEnd, h);
    }
}
