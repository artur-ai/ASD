package maiboroda.com.lab2.task1;


import maiboroda.com.lab2.Circle;

public class HashTable {
    private Circle[] data;
    private int size;

    private static final double A = 0.6180339887;

    public HashTable(int size) {
        this.size = size;
        this.data = new Circle[size];
    }

    private int hash(double key) {
        double val = key * A;
        double frac = val - Math.floor(val);
        return (int) Math.floor(size * frac);
    }

    public boolean insert(Circle c) {
        int pos = hash(c.area());
        if (data[pos] != null) {
            System.out.printf("  [ВІДМОВА] позиція %d зайнята для %s%n", pos, c);
            return false;
        }
        data[pos] = c;
        System.out.printf("  [OK] pos=%d  ключ(площа)=%.2f%n", pos, c.area());
        return true;
    }

    public void print() {
        System.out.println("\n--- Хеш-таблиця ---");
        for (int i = 0; i < size; i++) {
            if (data[i] == null)
                System.out.printf("  [%2d] порожньо%n", i);
            else
                System.out.printf("  [%2d] ключ=%.2f | %s%n", i, data[i].area(), data[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 1: Хеш-таблиця, метод множення, ключ=площа ===");

        HashTable ht = new HashTable(10);

        Circle[] circles = {
                new Circle(Math.random() * 10, Math.random() * 10, 1.0),
                new Circle(Math.random() * 10, Math.random() * 10, 2.0),
                new Circle(Math.random() * 10, Math.random() * 10, 3.0),
                new Circle(Math.random() * 10, Math.random() * 10, 4.0),
                new Circle(Math.random() * 10, Math.random() * 10, 5.0),
        };

        System.out.println("\nВставка елементів:");
        for (Circle c : circles) ht.insert(c);

        ht.print();
    }
}
