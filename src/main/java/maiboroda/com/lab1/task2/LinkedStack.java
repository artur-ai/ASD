package maiboroda.com.lab1.task2;

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
    }
}

public class LinkedStack {
    private Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(String val) {
        Node n = new Node(val);
        n.next = top;
        top = n;
    }

    public String pop() {
        if (isEmpty()) throw new RuntimeException("Стек порожній!");
        String val = top.data;
        top = top.next;
        return val;
    }

    public void print() {
        System.out.print("Стек: ");
        Node cur = top;
        while (cur != null) {
            System.out.print("[" + cur.data + "] ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();

        String[] names = {"Olha", "Ivan", "Maria", "Andrii", "Natalia", "Dmytro"};

        System.out.println("Завдання 2: Стек");

        for (int i = 0; i < names.length; i++) {
            String gender = (i % 2 == 0) ? "жін." : "чол.";
            System.out.println("push(" + names[i] + ") [" + gender + ", поз." + i + "]");
            stack.push(names[i]);
        }
        System.out.print("Після вставки: ");
        stack.print();

        System.out.println("Видалено: " + stack.pop());
        System.out.println("Видалено: " + stack.pop());
        System.out.print("Після видалення: ");
        stack.print();
    }
}