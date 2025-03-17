package main.java;

public class TriangleArea {
    public static double areaByBaseAndHeight(double base, double height) {
        return 0.5 * base * height;
    }

    public static void main(String[] args) {
        double base = 10, height = 5;
        System.out.println("Площадь треугольника: " + areaByBaseAndHeight(base, height));
    }
}


