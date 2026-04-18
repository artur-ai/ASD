package maiboroda.com.lab3;

public class BinaryTreeSearch extends BinaryTree {

    public void search() {
        System.out.println("\n--- Пошук: студенти з ідеальною масою (зріст - 110 = маса) ---");
        System.out.printf("%-5s %-12s %-10s %-8s %-8s %-8s%n",
                "№", "Прізвище", "Ім'я", "Зріст", "Маса", "Квиток");
        System.out.println("-".repeat(55));
        int[] counter = {1};
        searchRec(root, counter);
        if (counter[0] == 1) System.out.println("  Не знайдено жодного студента.");
    }

    private void searchRec(Node node, int[] counter) {
        if (node == null) return;
        searchRec(node.left, counter);
        if (node.data.isIdealWeight())
            System.out.printf("%-5d %s%n", counter[0]++, node.data);
        searchRec(node.right, counter);
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 2: Пошук студентів з ідеальною масою ===");

        BinaryTreeSearch tree = new BinaryTreeSearch();

        tree.insert(new Student("Коваль", "Іван", 175, 68.0, 1005)); // ідеал: 65 — ні
        tree.insert(new Student("Бондар", "Олег", 180, 70.0, 1002)); // ідеал: 70 — ТАК
        tree.insert(new Student("Шевченко", "Марія", 165, 55.0, 1008)); // ідеал: 55 — ТАК
        tree.insert(new Student("Мельник", "Петро", 170, 60.0, 1003)); // ідеал: 60 — ТАК
        tree.insert(new Student("Лисенко", "Анна", 162, 52.0, 1007)); // ідеал: 52 — ТАК
        tree.insert(new Student("Гриценко", "Сергій", 185, 75.0, 1001)); // ідеал: 75 — ТАК
        tree.insert(new Student("Савченко", "Юлія", 168, 58.0, 1006)); // ідеал: 58 — ні
        tree.insert(new Student("Іваненко", "Дмитро", 190, 80.0, 1004)); // ідеал: 80 — ТАК

        tree.inorder();
        tree.search();
    }
}
