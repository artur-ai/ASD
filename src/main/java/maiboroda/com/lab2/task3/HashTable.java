package maiboroda.com.lab2.task3;


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
                System.out.printf("  [OK] pos=%d, ключ=%.2f%n", pos, c.area());
                return true;
            }
        }
        System.out.println("  [ВІДМОВА] таблиця повна");
        return false;
    }

    public void deleteByPerimeter(double threshold) {
        System.out.printf("\nВидалення кіл з периметром > %.2f:%n", threshold);
        for (int i = 0; i < size; i++) {
            if (data[i] != null && data[i].perimeter() > threshold) {
                System.out.printf("  Видалено з pos=%d: %s%n", i, data[i]);
                data[i] = null;
            }
        }
    }

    public void print() {
        System.out.println("\n--- Хеш-таблиця ---");
        for (int i = 0; i < size; i++) {
            if (data[i] == null)
                System.out.printf("  [%2d] порожньо%n", i);
            else
                System.out.printf("  [%2d] ключ=%.2f | периметр=%.2f | %s%n",
                        i, data[i].area(), data[i].perimeter(), data[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 3: Видалення за периметром ===");

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

        ht.deleteByPerimeter(20.0);

        ht.print();
    }
}
