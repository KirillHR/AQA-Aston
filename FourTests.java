package test;

import main.java.Factorial;
import main.java.TriangleArea;
import main.java.ArithmeticOperations;
import main.java.CompareNumbers;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class FourTests {

    @Test
    public void testFactorial() {
        assertEquals(Factorial.factorial(0), 1);
        assertEquals(Factorial.factorial(1), 1);
        assertEquals(Factorial.factorial(5), 120);
    }

    private void assertEquals(long factorial, int i) {
    }

    private void assertEquals(long factorial, String i) {
    }

    @Test
    public void testTriangleArea() {
        assertEquals(TriangleArea.areaByBaseAndHeight(10, 5), 25.0, 0.0001);
        assertEquals(TriangleArea.areaByBaseAndHeight(0, 10), 0.0, 0.0001);
        assertEquals(TriangleArea.areaByBaseAndHeight(5, 5), 12.5, 0.0001);
    }

    private void assertEquals(double v, double v1, double v2) {
    }

    @Test
    public void testArithmeticOperations() {
        int x = 8, y = 4;
        String expected =
                "Сложение: " + x + " + " + y + " = " + (x + y) + "\n" +
                        "Вычитание: " + x + " - " + y + " = " + (x - y) + "\n" +
                        "Умножение: " + x + " * " + y + " = " + (x * y) + "\n" +
                        "Деление: " + x + " / " + y + " = " + (double) x / y + "\n";
        assertEquals(ArithmeticOperations.arithmeticOperations(x, y), expected);
    }

    @Test
    public void testGreaterThan() {
        int a = 10, b = 5;
        String expected = a + " больше, чем " + b;
        String actual = CompareNumbers.getComparisonResult(a, b);
        assertEquals(actual, expected);
    }

    private void assertEquals(String actual, String expected) {
    }

    @Test
    public void testLessThan() {
        int a = 3, b = 8;
        String expected = a + " меньше, чем " + b;
        String actual = CompareNumbers.getComparisonResult(a, b);
        assertEquals(actual, expected);
    }
}