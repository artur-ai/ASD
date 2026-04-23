package maiboroda.com.lab5;

public class Task3 extends Task2 {
    public Student searchAndPromote(int missed) {
        root = splay(root, missed);
        if (root != null && root.data.missed == missed)
            return root.data;
        return null;
    }

    private Node splay(Node node, int missed) {
        if (node == null) return null;
        if (missed == node.data.missed) return node;

        if (missed < node.data.missed) {
            if (node.left == null) return node;
            if (missed < node.left.data.missed) {
                node.left.left = splay(node.left.left, missed);
                node = rotateRight(node);
            } else if (missed > node.left.data.missed) {
                node.left.right = splay(node.left.right, missed);
                if (node.left.right != null)
                    node.left = rotateLeft(node.left);
            }
            return node.left == null ? node : rotateRight(node);
        } else {
            if (node.right == null) return node;
            if (missed > node.right.data.missed) {
                node.right.right = splay(node.right.right, missed);
                node = rotateLeft(node);
            } else if (missed < node.right.data.missed) {
                node.right.left = splay(node.right.left, missed);
                if (node.right.left != null)
                    node.right = rotateRight(node.right);
            }
            return node.right == null ? node : rotateLeft(node);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 3: BST з оптимізацією (splay) ===");

        Task3 tree = new Task3();

        Student[] students = {
                new Student("Коваль", "Іван", 101, "Ч", 4.2, 3),
                new Student("Бондар", "Олена", 102, "Ж", 4.8, 1),
                new Student("Шевченко", "Марія", 101, "Ж", 4.7, 7),
                new Student("Мельник", "Петро", 103, "Ч", 3.9, 5),
                new Student("Лисенко", "Анна", 102, "Ж", 4.6, 0),
                new Student("Гриценко", "Сергій", 101, "Ч", 4.1, 4),
                new Student("Савченко", "Юлія", 103, "Ж", 4.9, 2),
        };

        System.out.println("\nБудуємо дерево:");
        for (Student s : students) tree.insert(s);
        tree.printBFS();

        System.out.println("\n--- Пошук з оптимізацією ---");
        int[] keys = {5, 1, 5};

        for (int key : keys) {
            Student found = tree.searchAndPromote(key);
            System.out.printf("%nПошук (пропуски=%d): %s%n",
                    key, found != null ? found : "не знайдено");
            System.out.println("Дерево після пошуку:");
            tree.printBFS();
        }
    }
}