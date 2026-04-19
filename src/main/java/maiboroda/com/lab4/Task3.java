package maiboroda.com.lab4;

public class Task3 {
    public static void quickSort3Way(Student[] arr, int lo, int hi) {
        if (lo >= hi) return;

        int lt = lo, gt = hi, i = lo + 1;
        int pivot = arr[lo].month;

        while (i <= gt) {
            if (arr[i].month < pivot) swap(arr, lt++, i++);
            else if (arr[i].month > pivot) swap(arr, i, gt--);
            else i++;
        }
        quickSort3Way(arr, lo, lt - 1);
        quickSort3Way(arr, gt + 1, hi);
    }

    private static void swap(Student[] arr, int i, int j) {
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void print(Student[] arr) {
        System.out.printf("%-5s %-14s %-12s%n", "№", "Прізвище", "Дата народж.");
        System.out.println("-".repeat(33));
        for (int i = 0; i < arr.length; i++)
            System.out.printf("%-5d %s%n", i + 1, arr[i]);
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 3: 3-Way Quicksort за місяцем народження ===");

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

        quickSort3Way(students, 0, students.length - 1);

        System.out.println("\nПісля сортування (за місяцем):");
        print(students);
    }
}