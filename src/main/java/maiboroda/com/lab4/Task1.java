package maiboroda.com.lab4;

public class Task1 {
    public static Student[] countingSort(Student[] arr) {
        int[] count = new int[13];
        for (Student s : arr)
            count[s.month]++;
        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        Student[] result = new Student[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int pos = count[arr[i].month] - 1;
            result[pos] = arr[i];
            count[arr[i].month]--;
        }

        return result;
    }

    public static void print(Student[] arr) {
        System.out.printf("%-5s %-14s %-12s%n", "№", "Прізвище", "Дата народж.");
        System.out.println("-".repeat(33));
        for (int i = 0; i < arr.length; i++)
            System.out.printf("%-5d %s%n", i + 1, arr[i]);
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 1: Counting Sort за місяцем народження ===");

        Student[] students = {
                new Student("Коваль", 15, 8, 2003),
                new Student("Бондар", 22, 3, 2002),
                new Student("Шевченко", 5, 11, 2003),
                new Student("Мельник", 18, 1, 2004),
                new Student("Лисенко", 9, 8, 2003),
                new Student("Гриценко", 30, 5, 2002),
                new Student("Савченко", 12, 3, 2003),
                new Student("Іваненко", 7, 12, 2002),
                new Student("Петренко", 25, 6, 2003),
                new Student("Кравченко", 3, 1, 2004),
        };

        System.out.println("\nДо сортування:");
        print(students);

        students = countingSort(students);

        System.out.println("\nПісля сортування (за місяцем):");
        print(students);
    }
}
