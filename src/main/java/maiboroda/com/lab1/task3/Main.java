package maiboroda.com.lab1.task3;


import maiboroda.com.lab1.task1.ArrayQueue;
import maiboroda.com.lab1.task2.LinkedStack;

public class Main {

    static boolean isFemale(String name) {
        return name.equals("Olha") || name.equals("Maria") || name.equals("Natalia");
    }

    public static void main(String[] args) {
        System.out.println("Завдання 3: Стек -> дві черги");

        LinkedStack stack = new LinkedStack();
        String[] names = {"Olha", "Ivan", "Maria", "Andrii", "Natalia", "Dmytro"};
        for (String n : names) stack.push(n);

        System.out.print("Початковий стек: ");
        stack.print();

        ArrayQueue femaleQueue = new ArrayQueue(10);
        ArrayQueue maleQueue = new ArrayQueue(10);

        while (!stack.isEmpty()) {
            String name = stack.pop();
            if (isFemale(name)) femaleQueue.enqueue(name);
            else maleQueue.enqueue(name);
        }

        System.out.print("Стек після переміщення: ");
        stack.print();
        System.out.print("Черга жіночих імен: ");
        femaleQueue.print();
        System.out.print("Черга чоловічих імен: ");
        maleQueue.print();
    }
}
