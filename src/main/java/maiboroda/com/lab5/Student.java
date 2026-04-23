package maiboroda.com.lab5;

public class Student {
    String lastName;
    String firstName;
    int group;
    String gender;
    double gpa;
    int missed;

    public Student(String lastName, String firstName, int group, String gender, double gpa, int missed) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
        this.gender = gender;
        this.gpa = gpa;
        this.missed = missed;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-10s гр=%-4d %s  бал=%.1f  пропуски=%d",
                lastName, firstName, group, gender, gpa, missed);
    }
}
