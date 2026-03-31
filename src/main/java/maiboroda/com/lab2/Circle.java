package maiboroda.com.lab2;

public class Circle {
    double x, y;
    double radius;

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Коло(центр=(%.2f, %.2f), r=%.2f, площа=%.2f, периметр=%.2f)",
                x, y, radius, area(), perimeter());
    }
}
