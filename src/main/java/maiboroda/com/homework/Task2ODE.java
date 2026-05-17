package maiboroda.com.homework;

import java.util.Scanner;

public class Task2ODE {

    static double[] f(double x, double[] y) {
        return new double[]{y[1], y[2], -y[2] - y[1] + Math.sin(x)};
    }

    static double[] addScaled(double[] a, double[] b, double scale) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++)
            result[i] = a[i] + scale * b[i];
        return result;
    }

    static void rungeKutta3(double x0, double[] y0, double xEnd, double h) {
        System.out.printf("%-10s %-14s %-14s %-14s%n", "x", "y", "y'", "y''");
        System.out.println("-".repeat(52));
        System.out.printf("%-10.4f %-14.6f %-14.6f %-14.6f%n", x0, y0[0], y0[1], y0[2]);

        double x = x0;
        double[] y = y0.clone();

        while (x < xEnd - 1e-9) {
            double[] k1 = f(x, y);
            double[] k2 = f(x + h / 2.0, addScaled(y, k1, h / 2.0));
            double[] k3 = f(x + h, addScaled(addScaled(y, k1, -h), k2, 2 * h));

            double[] yNew = new double[y.length];
            for (int i = 0; i < y.length; i++)
                yNew[i] = y[i] + h * (k1[i] + 4 * k2[i] + k3[i]) / 6.0;

            x += h;
            y = yNew;
            System.out.printf("%-10.4f %-14.6f %-14.6f %-14.6f%n", x, y[0], y[1], y[2]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Завдання 2 (ДР): y''' = -y'' - y' + sin(x) ===");
        System.out.println("Метод Рунге-Кутта 3-го порядку\n");

        System.out.print("x0 (початкове x): ");
        double x0 = sc.nextDouble();
        System.out.print("y0 (початкове y):  ");
        double y0 = sc.nextDouble();
        System.out.print("y'0 (початкове y'): ");
        double dy0 = sc.nextDouble();
        System.out.print("y''0 (початкове y''): ");
        double ddy0 = sc.nextDouble();
        System.out.print("xEnd (кінцеве x): ");
        double xEnd = sc.nextDouble();
        System.out.print("h (крок):         ");
        double h = sc.nextDouble();

        System.out.println("\nТаблиця розв'язку:");
        rungeKutta3(x0, new double[]{y0, dy0, ddy0}, xEnd, h);
    }
}
