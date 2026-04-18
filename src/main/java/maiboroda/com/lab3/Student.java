package maiboroda.com.lab3;

public class Student {
    String lastName;
    String firstName;
    int height;
    double weight;
    int studentId;

    public Student(String lastName, String firstName, int height, double weight, int studentId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.height = height;
        this.weight = weight;
        this.studentId = studentId;
    }

    public boolean isIdealWeight() {
        return weight == (height - 110);
    }

    @Override
    public String toString() {
        return String.format("%-12s %-10s зріст=%-4d маса=%-6.1f квиток=%d",
                lastName, firstName, height, weight, studentId);
    }
}
