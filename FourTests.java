package test;

import main.java.Factorial;
import main.java.TriangleArea;
import main.java.ArithmeticOperations;
import main.java.CompareNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FourTests {

    @Test
    @DisplayName("Проверка факториала")
    void testFactorial() {
        assertEquals(1, Factorial.factorial(0));
        assertEquals(1, Factorial.factorial(1));
        assertEquals(120, Factorial.factorial(5));
    }

    @Test
    @DisplayName("Проверка площади треугольника для различных значений оснований и высот")
    void testTriangleArea() {
        assertEquals(25.0, TriangleArea.areaByBaseAndHeight(10, 5), 0.0001);
        assertEquals(0.0, TriangleArea.areaByBaseAndHeight(0, 10), 0.0001);
        assertEquals(12.5, TriangleArea.areaByBaseAndHeight(5, 5), 0.0001);
    }

    @Test
    @DisplayName("Проверка арифметических операций для различных чисел")
    void testArithmeticOperations() {
        int x = 8, y = 4;
        String expected =
                "Сложение: " + x + " + " + y + " = " + (x + y) + "\n" +
                        "Вычитание: " + x + " - " + y + " = " + (x - y) + "\n" +
                        "Умножение: " + x + " * " + y + " = " + (x * y) + "\n" +
                        "Деление: " + x + " / " + y + " = " + (double) x / y + "\n";
        assertEquals(expected, ArithmeticOperations.arithmeticOperations(x, y));
    }

    @Test
    @DisplayName("Тест на сравнение большего числа")
    void testGreaterThan() {
        int a = 10, b = 5;
        String expected = a + " больше, чем " + b;
        String actual = CompareNumbers.getComparisonResult(a, b);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест на сравнение меньшего числа")
    void testLessThan() {
        int a = 3, b = 8;
        String expected = a + " меньше, чем " + b;
        String actual = CompareNumbers.getComparisonResult(a, b);
        assertEquals(expected, actual);
    }
}
