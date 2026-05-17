package maiboroda.com.lab2_3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task3 {

    static int n, m;
    static int[] assignment;
    static boolean[] used;
    static int count = 0;
    static BufferedWriter writer;

    static void generate(int group) throws IOException {
        if (group == n) {
            count++;
            StringBuilder sb = new StringBuilder();
            sb.append(count).append(". ");
            for (int i = 0; i < n; i++) {
                sb.append("Гр").append(i + 1).append("→К").append(assignment[i] + 1);
                if (i < n - 1) sb.append(", ");
            }
            writer.write(sb.toString());
            writer.newLine();
            return;
        }

        int limit = (group == 0) ? m : n;

        for (int curator = 0; curator < limit; curator++) {
            if (!used[curator]) {
                used[curator] = true;
                assignment[group] = curator;
                generate(group + 1);
                used[curator] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Завдання 3: Генерація всіх розміщень у файл ===");

        System.out.print("Кількість кураторів (n): ");
        n = sc.nextInt();
        System.out.print("Кандидати для англомовної групи (m): ");
        m = sc.nextInt();

        assignment = new int[n];
        used = new boolean[n];

        String filename = "src/main/java/maiboroda/com/lab2_3/arrangements.txt";
        writer = new BufferedWriter(new FileWriter(filename));

        writer.write("Розміщення кураторів (Гр1=англомовна, куратор з К1-К" + m + ")");
        writer.newLine();
        writer.write("=".repeat(60));
        writer.newLine();

        generate(0);

        writer.write("=".repeat(60));
        writer.newLine();
        writer.write("Всього: " + count + " варіантів (= " + m + " × " + factorial(n - 1) + ")");
        writer.close();

        System.out.println("Записано " + count + " варіантів у файл: " + filename);
    }

    static long factorial(int n) {
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}
