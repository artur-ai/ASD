package maiboroda.com.lab2_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    enum State {START, S1, S2, S3, ERROR}

    enum CharClass {
        SLASH,
        LOWER,
        FK,
        OTHER
    }

    static final Map<String, State> TABLE = new HashMap<>();

    static {
        TABLE.put(key(State.START, CharClass.SLASH), State.S1);
        TABLE.put(key(State.START, CharClass.LOWER), State.ERROR);
        TABLE.put(key(State.START, CharClass.FK), State.ERROR);
        TABLE.put(key(State.START, CharClass.OTHER), State.ERROR);

        TABLE.put(key(State.S1, CharClass.SLASH), State.S2);
        TABLE.put(key(State.S1, CharClass.LOWER), State.ERROR);
        TABLE.put(key(State.S1, CharClass.FK), State.ERROR);
        TABLE.put(key(State.S1, CharClass.OTHER), State.ERROR);

        TABLE.put(key(State.S2, CharClass.SLASH), State.ERROR);
        TABLE.put(key(State.S2, CharClass.LOWER), State.S2);
        TABLE.put(key(State.S2, CharClass.FK), State.S3);
        TABLE.put(key(State.S2, CharClass.OTHER), State.ERROR);

        TABLE.put(key(State.S3, CharClass.SLASH), State.ERROR);
        TABLE.put(key(State.S3, CharClass.LOWER), State.ERROR);
        TABLE.put(key(State.S3, CharClass.FK), State.S3);
        TABLE.put(key(State.S3, CharClass.OTHER), State.ERROR);
    }

    static String key(State s, CharClass c) {
        return s + ":" + c;
    }

    static CharClass classify(char c) {
        if (c == '/') return CharClass.SLASH;
        if (c >= 'a' && c <= 'z') return CharClass.LOWER;
        if (c >= 'F' && c <= 'K') return CharClass.FK;
        return CharClass.OTHER;
    }

    public static boolean recognize(String word) {
        State state = State.START;

        for (int i = 0; i < word.length(); i++) {
            CharClass cc = classify(word.charAt(i));
            state = TABLE.getOrDefault(key(state, cc), State.ERROR);
            if (state == State.ERROR) break;
        }

        return state == State.S3;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("=== Завдання 3: Автомат (таблиця переходів) ===");
        System.out.println("Роздільники: \"+-\" і \"-+\"");
        System.out.println("Регулярний вираз: \\/\\/[a-z]*[F-K]+\n");

        BufferedReader reader = new BufferedReader(
                new FileReader("src/main/java/maiboroda/com/lab2_2/words_task3.txt"));

        String line;
        int total = 0, valid = 0;

        System.out.printf("%-22s %-12s%n", "Слово", "Результат");
        System.out.println("-".repeat(34));

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\+\\-|\\-\\+");

            for (String word : words) {
                word = word.trim();
                if (word.isEmpty()) continue;
                total++;
                boolean ok = recognize(word);
                if (ok) valid++;
                System.out.printf("%-22s %-12s%n", word, ok ? "ТАК ✓" : "ні ✗");
            }
        }
        reader.close();

        System.out.printf("%nВсього слів: %d, правильних: %d, неправильних: %d%n",
                total, valid, total - valid);
    }
}
