package main.java;

public class CompareNumbers {
    public static void compareNumbers(int a, int b) {
        System.out.println(getComparisonResult(a, b));
    }

    public static String getComparisonResult(int a, int b) {
        if (a > b) {
            return a + " больше, чем " + b;
        } else if (a < b) {
            return a + " меньше, чем " + b;
        } else {
            return a + " равно " + b;
        }
    }

    public static void main(String[] args) {
        compareNumbers(10, 5);
        compareNumbers(3, 8);
        compareNumbers(7, 7);
    }
}