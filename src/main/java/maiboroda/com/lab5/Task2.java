package maiboroda.com.lab5;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    Student data;
    Node left, right;

    Node(Student s) {
        data = s;
    }
}

public class Task2 {
    protected Node root;

    protected Node rotateRight(Node y) {
        Node x = y.left;
        Node B = x.right;
        x.right = y;
        y.left = B;
        return x;
    }

    protected Node rotateLeft(Node x) {
        Node y = x.right;
        Node B = y.left;
        y.left = x;
        x.right = B;
        return y;
    }

    public void insert(Student s) {
        root = insertRoot(root, s);
    }

    private Node insertRoot(Node node, Student s) {
        if (node == null) return new Node(s);

        if (s.missed < node.data.missed) {
            node.left = insertRoot(node.left, s);
            return rotateRight(node);
        } else if (s.missed > node.data.missed) {
            node.right = insertRoot(node.right, s);
            return rotateLeft(node);
        }
        return node;
    }

    public Student search(int missed) {
        Node cur = root;
        while (cur != null) {
            if (missed < cur.data.missed) cur = cur.left;
            else if (missed > cur.data.missed) cur = cur.right;
            else return cur.data;
        }
        return null;
    }

    public void printBFS() {
        if (root == null) {
            System.out.println("Дерево порожнє");
            return;
        }
        System.out.printf("%-5s %-12s %-10s %-6s %-4s %-6s %-8s%n",
                "Рів.", "Прізвище", "Ім'я", "Група", "Ст.", "Бал", "Пропуски");
        System.out.println("-".repeat(57));
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        queue.add(root);
        levels.add(0);
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            int lv = levels.poll();
            System.out.printf("%-5d %s%n", lv, n.data);
            if (n.left != null) {
                queue.add(n.left);
                levels.add(lv + 1);
            }
            if (n.right != null) {
                queue.add(n.right);
                levels.add(lv + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 2: BST, вставка в корінь, ключ=пропуски ===");

        Task2 tree = new Task2();

        Student[] students = {
                new Student("Коваль", "Іван", 101, "Ч", 4.2, 3),
                new Student("Бондар", "Олена", 102, "Ж", 4.8, 1),
                new Student("Шевченко", "Марія", 101, "Ж", 4.7, 7),
                new Student("Мельник", "Петро", 103, "Ч", 3.9, 5),
                new Student("Лисенко", "Анна", 102, "Ж", 4.6, 0),
                new Student("Гриценко", "Сергій", 101, "Ч", 4.1, 4),
                new Student("Савченко", "Юлія", 103, "Ж", 4.9, 2),
        };

        for (Student s : students) {
            tree.insert(s);
            System.out.printf("%nДодано: %s%n", s);
            tree.printBFS();
        }

        int key = 4;
        Student found = tree.search(key);
        System.out.printf("%nПошук (пропуски=%d): %s%n",
                key, found != null ? found : "не знайдено");
    }
}
