package maiboroda.com.lab3;


public class BinaryTreeDelete extends BinaryTreeSearch {

    public void deleteIdealWeight() {
        System.out.println("\n--- Видалення студентів з ідеальною масою ---");
        root = deleteRec(root);
    }

    private Node deleteRec(Node node) {
        if (node == null) return null;

        node.left = deleteRec(node.left);
        node.right = deleteRec(node.right);

        if (node.data.isIdealWeight()) {
            System.out.printf("  Видаляємо: %s%n", node.data);

            if (node.left == null && node.right == null) return null;

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node minRight = findMin(node.right);
            node.data = minRight.data;
            node.right = deleteNode(node.right, minRight.data.studentId);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteNode(Node node, int id) {
        if (node == null) return null;
        if (id < node.data.studentId) node.left = deleteNode(node.left, id);
        else if (id > node.data.studentId) node.right = deleteNode(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = findMin(node.right);
            node.data = min.data;
            node.right = deleteNode(node.right, min.data.studentId);
        }
        return node;
    }

    public static void main(String[] args) {
        System.out.println("=== Завдання 3: Видалення студентів з ідеальною масою ===");

        BinaryTreeDelete tree = new BinaryTreeDelete();

        tree.insert(new Student("Бондар", "Олег", 180, 70.0, 1005)); // корінь, ідеал (2 дітей)
        tree.insert(new Student("Гриценко", "Сергій", 185, 75.0, 1002)); // ліве, ідеал (без дітей)
        tree.insert(new Student("Шевченко", "Марія", 165, 55.0, 1008)); // праве, ідеал (1 дитина)
        tree.insert(new Student("Коваль", "Іван", 175, 68.0, 1003)); // ліве від 1008, не ідеал
        tree.insert(new Student("Мельник", "Петро", 170, 60.0, 1007)); // ідеал, без дітей
        tree.insert(new Student("Лисенко", "Анна", 162, 52.0, 1001)); // не ідеал
        tree.insert(new Student("Савченко", "Юлія", 168, 59.0, 1006)); // не ідеал
        tree.insert(new Student("Іваненко", "Дмитро", 190, 80.0, 1004)); // ідеал (1 дитина)

        System.out.println("\nДерево ДО видалення:");
        tree.inorder();

        tree.search();
        tree.deleteIdealWeight();

        System.out.println("\nДерево ПІСЛЯ видалення:");
        tree.inorder();
    }
}