package maiboroda.com.lab2_2;

import java.util.Scanner;

public class Task2 {
    enum State {START, S1, S2, S3, ERROR}

    public static boolean recognize(String input) {
        State state = State.START;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (state) {
                case START:
                    if (c == '/') state = State.S1;
                    else state = State.ERROR;
                    break;

                case S1:
                    if (c == '/') state = State.S2;
                    else state = State.ERROR;
                    break;

                case S2:
                    if (c >= 'a' && c <= 'z') state = State.S2;
                    else if (c >= 'F' && c <= 'K') state = State.S3;
                    else state = State.ERROR;
                    break;

                case S3:
                    if (c >= 'F' && c <= 'K') state = State.S3;
                    else state = State.ERROR;
                    break;

                case ERROR:
                    break;
            }

            if (state == State.ERROR) break;
        }
        return state == State.S3;
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 2: Скінченний автомат (switch) ===");
        System.out.println("Розпізнає: \\/\\/[a-z]*[F-K]+\n");

        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть рядок: ");
        String input = sc.nextLine();

        boolean result = recognize(input);
        System.out.println("Результат: " + (result ? "ПРАВИЛЬНИЙ ✓" : "НЕПРАВИЛЬНИЙ ✗"));

        System.out.println("\n--- Тестові приклади ---");
        String[] tests = {
                "//abcF",
                "//G",
                "//xyzHK",
                "/abcF",
                "//abc",
                "//AbcF",
                "//F",
                "//abcL",
        };

        System.out.printf("%-20s %-12s%n", "Рядок", "Результат");
        System.out.println("-".repeat(32));
        for (String t : tests)
            System.out.printf("%-20s %-12s%n", t, recognize(t) ? "ТАК ✓" : "ні ✗");
    }
}
