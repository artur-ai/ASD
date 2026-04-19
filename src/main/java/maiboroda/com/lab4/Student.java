package maiboroda.com.lab4;

public class Student {
    String lastName;
    int day, month, year;

    public Student(String lastName, int day, int month, int year) {
        this.lastName = lastName;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%-14s %02d.%02d.%04d", lastName, day, month, year);
    }
}
