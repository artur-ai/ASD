package maiboroda.com.homework;

import java.util.Scanner;

public class Task1 {

    static int n;
    static double[][] A;
    static double[] b;
    static double[][] L, U, P;

    static void lupDecompose() {
        L = new double[n][n];
        U = new double[n][n];
        P = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                U[i][j] = A[i][j];
                P[i][j] = (i == j) ? 1 : 0;
                L[i][j] = 0;
            }
        }

        for (int col = 0; col < n; col++) {
            int maxRow = col;
            for (int row = col + 1; row < n; row++) {
                if (Math.abs(U[row][col]) > Math.abs(U[maxRow][col]))
                    maxRow = row;
            }

            double[] tmp = U[col];
            U[col] = U[maxRow];
            U[maxRow] = tmp;
            tmp = P[col];
            P[col] = P[maxRow];
            P[maxRow] = tmp;
            for (int j = 0; j < col; j++) {
                double t = L[col][j];
                L[col][j] = L[maxRow][j];
                L[maxRow][j] = t;
            }

            for (int row = col + 1; row < n; row++) {
                if (Math.abs(U[col][col]) < 1e-12) continue;
                double factor = U[row][col] / U[col][col];
                L[row][col] = factor;
                for (int j = col; j < n; j++)
                    U[row][j] -= factor * U[col][j];
            }
        }

        for (int i = 0; i < n; i++) L[i][i] = 1;
    }

    static double[] forwardSubstitution(double[] pb) {
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = pb[i];
            for (int j = 0; j < i; j++)
                y[i] -= L[i][j] * y[j];
        }
        return y;
    }

    static double[] backSubstitution(double[] y) {
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = y[i];
            for (int j = i + 1; j < n; j++)
                x[i] -= U[i][j] * x[j];
            x[i] /= U[i][i];
        }
        return x;
    }

    static void printMatrix(String name, double[][] M) {
        System.out.println(name + ":");
        for (double[] row : M) {
            System.out.print("  [");
            for (int j = 0; j < row.length; j++)
                System.out.printf("%8.3f", row[j]);
            System.out.println(" ]");
        }
    }

    static void printSystem() {
        System.out.println("Система рівнянь:");
        String[] vars = {"x1", "x2", "x3"};
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder("  ");
            for (int j = 0; j < n; j++) {
                if (j > 0) sb.append(A[i][j] >= 0 ? " + " : " - ");
                else if (A[i][j] < 0) sb.append("-");
                sb.append(String.format("%.0f%s", Math.abs(A[i][j]), vars[j]));
            }
            sb.append(String.format(" = %.0f", b[i]));
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== LUP-розкладання СЛАР ===");
        System.out.print("Розмір системи n: ");
        n = sc.nextInt();

        A = new double[n][n];
        b = new double[n];

        System.out.println("Введіть коефіцієнти (рядок за рядком, потім вільний член):");
        for (int i = 0; i < n; i++) {
            System.out.printf("Рядок %d: ", i + 1);
            for (int j = 0; j < n; j++) A[i][j] = sc.nextDouble();
            b[i] = sc.nextDouble();
        }

        printSystem();

        lupDecompose();

        System.out.println("\nРезультати розкладання PA = LU:");
        printMatrix("P (матриця перестановок)", P);
        printMatrix("L (нижня трикутна)", L);
        printMatrix("U (верхня трикутна)", U);

        double[] pb = new double[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                pb[i] += P[i][j] * b[j];

        double[] y = forwardSubstitution(pb);
        double[] x = backSubstitution(y);

        System.out.println("\nРозв'язок:");
        for (int i = 0; i < n; i++)
            System.out.printf("  x%d = %.4f%n", i + 1, x[i]);

        System.out.println("\nПеревірка (Ax = b):");
        for (int i = 0; i < n; i++) {
            double check = 0;
            for (int j = 0; j < n; j++) check += A[i][j] * x[j];
            System.out.printf("  Рівняння %d: %.4f (має бути %.4f)%n",
                    i + 1, check, b[i]);
        }
    }
}
