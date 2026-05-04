package maiboroda.com.lab2_1;

import java.util.Scanner;


public class Task2 {

    static final double EPS = 1e-6;
    static final int MAX_ITER = 1000;

    static double f(double x) {
        return (x - 2) * (x - 2) - x;
    }

    static double df(double x) {
        return 2 * (x - 2) - 1;
    }

    static Double bisection(double a, double b) {
        if (f(a) * f(b) > 0) {
            System.out.println("  Бісекція: немає кореня на [" + a + ", " + b + "]");
            return null;
        }
        for (int i = 0; i < MAX_ITER; i++) {
            double mid = (a + b) / 2.0;
            if (Math.abs(f(mid)) < EPS) return mid;
            if (f(a) * f(mid) < 0) b = mid;
            else a = mid;
        }
        return (a + b) / 2.0;
    }

    static Double newton(double x0) {
        double x = x0;
        for (int i = 0; i < MAX_ITER; i++) {
            double fx = f(x);
            double dfx = df(x);
            if (Math.abs(dfx) < 1e-12) {
                System.out.println("  Ньютон: похідна = 0, метод не застосовний");
                return null;
            }
            double xNew = x - fx / dfx;
            if (Math.abs(xNew - x) < EPS) return xNew;
            x = xNew;
        }
        return x;
    }

    static Double chord(double a, double b) {
        if (f(a) * f(b) > 0) {
            System.out.println("  Хорди: немає кореня на [" + a + ", " + b + "]");
            return null;
        }
        double x = a;
        for (int i = 0; i < MAX_ITER; i++) {
            double fa = f(a), fb = f(b);
            double xNew = a - fa * (b - a) / (fb - fa);
            if (Math.abs(f(xNew)) < EPS) return xNew;
            if (f(xNew) * fa < 0) b = xNew;
            else a = xNew;
            x = xNew;
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Завдання 2: Корені рівняння (x-2)² - x = 0 ===");
        System.out.println("Аналітично: x² - 5x + 4 = 0 → корені: x=1, x=4");

        System.out.print("\nВведіть a: ");
        double a = sc.nextDouble();
        System.out.print("Введіть b: ");
        double b = sc.nextDouble();

        System.out.printf("%nІнтервал [%.1f, %.1f]:%n", a, b);
        System.out.printf("f(a)=%.4f, f(b)=%.4f%n", f(a), f(b));

        Double r1 = bisection(a, b);
        Double r2 = newton((a + b) / 2);
        Double r3 = chord(a, b);

        System.out.println("\nРезультати:");
        System.out.printf("Бісекція:  %s  (f=%.8f)%n",
                r1 != null ? String.format("%.6f", r1) : "немає",
                r1 != null ? f(r1) : 0);
        System.out.printf("Ньютон:    %s  (f=%.8f)%n",
                r2 != null ? String.format("%.6f", r2) : "немає",
                r2 != null ? f(r2) : 0);
        System.out.printf("Хорди:     %s  (f=%.8f)%n",
                r3 != null ? String.format("%.6f", r3) : "немає",
                r3 != null ? f(r3) : 0);
    }
}
