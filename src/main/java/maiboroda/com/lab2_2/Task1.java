package maiboroda.com.lab2_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Task1 {
    static final String REGEX = "\\/\\/[a-z]*[F-K]+";

    public static void main(String[] args) throws IOException {
        System.out.println("=== Завдання 1: Пошук слів за регулярним виразом ===");
        System.out.println("Регулярний вираз: \\/\\/[a-z]*[F-K]+");
        System.out.println("Опис: починається з //, потім a-z (0+), закінчується F-K (1+)\n");

        Pattern pattern = Pattern.compile(REGEX);

        BufferedReader reader = new BufferedReader(
                new FileReader("src/main/java/maiboroda/com/lab2_2/words_task1.txt"));

        String line;
        int lineNum = 0, found = 0;
        System.out.printf("%-5s %-20s %-10s%n", "№", "Слово", "Відповідає?");
        System.out.println("-".repeat(37));

        while ((line = reader.readLine()) != null) {
            lineNum++;
            boolean matches = pattern.matcher(line).matches();
            System.out.printf("%-5d %-20s %-10s%n",
                    lineNum, line, matches ? "ТАК ✓" : "ні");
            if (matches) found++;
        }
        reader.close();

        System.out.println("\nЗнайдено: " + found + " слів з " + lineNum);
    }
}
