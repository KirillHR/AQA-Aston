package main.java;

public class ArithmeticOperations {
    public static int arithmeticOperations(int x, int y) {
        System.out.println(getArithmeticOperationsResult(x, y));
        return x;
    }

    public static String getArithmeticOperationsResult(int x, int y) {
        StringBuilder result = new StringBuilder();
        result.append("Сложение: ").append(x).append(" + ").append(y).append(" = ").append(x + y).append("\n");
        result.append("Вычитание: ").append(x).append(" - ").append(y).append(" = ").append(x - y).append("\n");
        result.append("Умножение: ").append(x).append(" * ").append(y).append(" = ").append(x * y).append("\n");
        if (y != 0) {
            result.append("Деление: ").append(x).append(" / ").append(y).append(" = ").append((double) x / y).append("\n");
        } else {
            result.append("Деление: на 0 делить нельзя\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        arithmeticOperations(8, 4);
    }
}
