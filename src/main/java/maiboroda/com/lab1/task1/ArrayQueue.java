package maiboroda.com.lab1.task1;

public class ArrayQueue {
    private String[] data;
    private int size, head, tail;

    public ArrayQueue(int capacity) {
        data = new String[capacity];
    }

    public boolean isFull() {
        return size == data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean enqueue(String val) {
        if (isFull()) return false;
        data[tail] = val;
        tail = (tail + 1) % data.length;
        size++;
        return true;
    }

    public String dequeue() {
        if (isEmpty()) throw new RuntimeException("Черга порожня!");
        String val = data[head];
        head = (head + 1) % data.length;
        size--;
        return val;
    }

    public void print() {
        System.out.print("Черга: ");
        for (int i = 0; i < size; i++)
            System.out.print("[" + data[(head + i) % data.length] + "] ");
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue(5);

        System.out.println("Завдання 1: Черга");

        q.enqueue("Alpha");
        q.enqueue("Beta");
        q.enqueue("Gamma");
        q.enqueue("Delta");
        q.enqueue("Epsilon");
        System.out.print("Після вставки: ");
        q.print();

        System.out.println("Видалено: " + q.dequeue());
        System.out.println("Видалено: " + q.dequeue());
        System.out.print("Після видалення: ");
        q.print();
    }
}
