package maiboroda.com.lab2.task2;


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
        double frac = (key * A) - Math.floor(key * A);
        return (int) Math.floor(size * frac);
    }

    public boolean insert(Circle c) {
        int base = hash(c.area());
        for (int i = 0; i < size; i++) {
            int pos = (base + i * i) % size;
            if (data[pos] == null) {
                data[pos] = c;
                System.out.printf("  [OK] спроба=%d, pos=%d, ключ=%.2f%n", i, pos, c.area());
                return true;
            }
        }
        System.out.println("  [ВІДМОВА] таблиця повна, колізія не вирішена");
        return false;
    }

    public void print() {
        System.out.println("\n--- Хеш-таблиця (квадратичне зондування) ---");
        for (int i = 0; i < size; i++) {
            if (data[i] == null)
                System.out.printf("  [%2d] порожньо%n", i);
            else
                System.out.printf("  [%2d] ключ=%.2f | %s%n", i, data[i].area(), data[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 2: Квадратичне зондування ===");

        HashTable ht = new HashTable(10);

        Circle[] circles = {
                new Circle(Math.random() * 10, Math.random() * 10, 1.0),
                new Circle(Math.random() * 10, Math.random() * 10, 2.0),
                new Circle(Math.random() * 10, Math.random() * 10, 3.0),
                new Circle(Math.random() * 10, Math.random() * 10, 4.0),
                new Circle(Math.random() * 10, Math.random() * 10, 5.0),
                new Circle(Math.random() * 10, Math.random() * 10, 1.5),
                new Circle(Math.random() * 10, Math.random() * 10, 2.5),
        };

        System.out.println("\nВставка елементів:");
        for (Circle c : circles) ht.insert(c);

        ht.print();
    }
}
