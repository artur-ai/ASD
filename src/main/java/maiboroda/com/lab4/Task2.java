package maiboroda.com.lab4;

class Node {
    Student data;
    Node next;

    Node(Student s) {
        data = s;
    }
}

public class Task2 {
    Node head;
    int size;

    public void add(Student s) {
        Node node = new Node(s);
        if (head == null) {
            head = node;
        } else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = node;
        }
        size++;
    }

    public void countingSort() {
        Student[] arr = new Student[size];
        Node cur = head;
        for (int i = 0; i < size; i++) {
            arr[i] = cur.data;
            cur = cur.next;
        }

        int[] count = new int[13];
        for (Student s : arr) count[s.month]++;
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
        Student[] result = new Student[size];
        for (int i = size - 1; i >= 0; i--) {
            result[count[arr[i].month] - 1] = arr[i];
            count[arr[i].month]--;
        }

        cur = head;
        for (Student s : result) {
            cur.data = s;
            cur = cur.next;
        }
    }

    public void print() {
        System.out.printf("%-5s %-14s %-12s%n", "№", "Прізвище", "Дата народж.");
        System.out.println("-".repeat(33));
        Node cur = head;
        int i = 1;
        while (cur != null) {
            System.out.printf("%-5d %s%n", i++, cur.data);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 2: Односпрямований список, Counting Sort ===");

        Task2 list = new Task2();
        list.add(new Student("Коваль", 15, 8, 2003));
        list.add(new Student("Бондар", 22, 3, 2002));
        list.add(new Student("Шевченко", 5, 11, 2003));
        list.add(new Student("Мельник", 18, 1, 2004));
        list.add(new Student("Лисенко", 9, 8, 2003));
        list.add(new Student("Гриценко", 30, 5, 2002));
        list.add(new Student("Савченко", 12, 3, 2003));
        list.add(new Student("Іваненко", 7, 12, 2002));
        list.add(new Student("Петренко", 25, 6, 2003));
        list.add(new Student("Кравченко", 3, 1, 2004));

        System.out.println("\nДо сортування:");
        list.print();

        list.countingSort();

        System.out.println("\nПісля сортування (за місяцем):");
        list.print();
    }
}
