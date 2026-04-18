package maiboroda.com.lab3;

class Node {
    Student data;
    Node left, right;

    Node(Student s) {
        data = s;
    }
}

public class BinaryTree {
    protected Node root;

    public void insert(Student s) {
        root = insertRec(root, s);
    }

    private Node insertRec(Node node, Student s) {
        if (node == null) return new Node(s);
        if (s.studentId < node.data.studentId)
            node.left = insertRec(node.left, s);
        else if (s.studentId > node.data.studentId)
            node.right = insertRec(node.right, s);
        return node;
    }

    public void inorder() {
        System.out.println("\n--- Послідовний обхід (inorder) ---");
        System.out.printf("%-5s %-12s %-10s %-8s %-8s %-8s%n",
                "№", "Прізвище", "Ім'я", "Зріст", "Маса", "Квиток");
        System.out.println("-".repeat(55));
        int[] counter = {1};
        inorderRec(root, counter);
    }

    private void inorderRec(Node node, int[] counter) {
        if (node == null) return;
        inorderRec(node.left, counter);
        System.out.printf("%-5d %s%n", counter[0]++, node.data);
        inorderRec(node.right, counter);
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 1: Бінарне дерево, послідовний обхід ===");

        BinaryTree tree = new BinaryTree();

        tree.insert(new Student("Коваль", "Іван", 175, 68.0, 1005));
        tree.insert(new Student("Бондар", "Олег", 180, 72.0, 1002));
        tree.insert(new Student("Шевченко", "Марія", 165, 55.0, 1008));
        tree.insert(new Student("Мельник", "Петро", 170, 60.0, 1003));
        tree.insert(new Student("Лисенко", "Анна", 162, 52.0, 1007));
        tree.insert(new Student("Гриценко", "Сергій", 185, 75.0, 1001));
        tree.insert(new Student("Савченко", "Юлія", 168, 58.0, 1006));
        tree.insert(new Student("Іваненко", "Дмитро", 190, 80.0, 1004));

        tree.inorder();
    }
}
