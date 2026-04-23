package maiboroda.com.lab5;

public class Task1 {
    public static int insertOrdered(Student[] arr, int size, Student s) {
        int pos = size;
        for (int i = 0; i < size; i++) {
            if (s.group <= arr[i].group) {
                pos = i;
                break;
            }
        }
        for (int i = size; i > pos; i--)
            arr[i] = arr[i - 1];
        arr[pos] = s;
        return size + 1;
    }

    public static int search(Student[] arr, int size, int targetGroup) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i].group == targetGroup
                    && arr[i].gender.equals("Ж")
                    && arr[i].gpa > 4.5) {
                count++;
            }
        }
        return -count;
    }

    public static void print(Student[] arr, int size) {
        System.out.printf("%-5s %-12s %-10s %-6s %-4s %-6s %-8s%n",
                "№", "Прізвище", "Ім'я", "Група", "Стать", "Бал", "Пропуски");
        System.out.println("-".repeat(57));
        for (int i = 0; i < size; i++)
            System.out.printf("%-5d %s%n", i + 1, arr[i]);
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 1: Послідовний пошук, масив упорядкований за групою ===");

        Student[] arr = new Student[25];
        int size = 0;

        Student[] input = {
                new Student("Коваль", "Іван", 101, "Ч", 4.2, 3),
                new Student("Бондар", "Олена", 102, "Ж", 4.8, 1),
                new Student("Шевченко", "Марія", 101, "Ж", 4.7, 2),
                new Student("Мельник", "Петро", 103, "Ч", 3.9, 5),
                new Student("Лисенко", "Анна", 102, "Ж", 4.6, 0),
                new Student("Гриценко", "Сергій", 101, "Ч", 4.1, 4),
                new Student("Савченко", "Юлія", 103, "Ж", 4.9, 1),
                new Student("Іваненко", "Дмитро", 102, "Ч", 3.7, 6),
                new Student("Петренко", "Оксана", 101, "Ж", 4.8, 0),
                new Student("Кравченко", "Олег", 103, "Ч", 4.3, 2),
                new Student("Романенко", "Тетяна", 102, "Ж", 4.5, 1),
                new Student("Ткаченко", "Василь", 101, "Ч", 3.8, 7),
                new Student("Назаренко", "Ірина", 103, "Ж", 4.7, 0),
                new Student("Харченко", "Микола", 102, "Ч", 4.0, 3),
                new Student("Захаренко", "Софія", 101, "Ж", 4.6, 1),
                new Student("Поліщук", "Андрій", 103, "Ч", 3.5, 8),
                new Student("Павленко", "Катерина", 102, "Ж", 4.9, 0),
                new Student("Симоненко", "Роман", 101, "Ч", 4.4, 2),
                new Student("Власенко", "Діана", 103, "Ж", 4.8, 1),
                new Student("Остапенко", "Ігор", 102, "Ч", 3.6, 5),
        };

        for (Student s : input)
            size = insertOrdered(arr, size, s);

        System.out.println("\nМасив (упорядкований за групою):");
        print(arr, size);

        int targetGroup = 101;
        int result = search(arr, size, targetGroup);
        int count = Math.abs(result);
        System.out.printf("%nПошук: студентки з балом > 4.5 у групі %d%n", targetGroup);
        System.out.printf("Результат: %d студенток%n", count);
    }
}
