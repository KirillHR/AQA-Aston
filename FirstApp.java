import java.util.Arrays;

public class FirstApp {

    static void printThreeWords() {
        System.out.println("Orange, Banana, Apple");
    }

    static void checkSumSign() {
        int a = 2;
        int b = 3;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    static void printColor() {
        int value = 1;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    static void compareNumbers() {
        int a = 2;
        int b = 3;
        if (a >= b) {
            System.out.println(a + " >= " + b);
        } else {
            System.out.println(a + " < " + b);
        }
    }

    static boolean integerCheck(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    static void ratioNumber(int a) {
        if (a >= 0) {
            System.out.println("Число " + a + " положительное");
        } else {
            System.out.println("Число " + a + " отрицательное");
        }
    }

    static boolean negativeNumber(int a) {
        return a < 0;
    }

    static void printStringAndNumber(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    static boolean leapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    static void integerArray() {
        int[] array = {1, 1, 0, 0, 1, 1, 0, 1, 1};
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }
        System.out.println(Arrays.toString(array));
    }

    static void arrayOneHundred() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));
    }

    static void arrayMult() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 2;
            }
            System.out.println(array[i]);
        }
    }

    static void twoDimensionalArray() {
        int[][] table = new int[10][10];
        for (int i = 0; i < table.length; i++) {
            table[i][i] = 1;
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[] returnArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        integerCheck(2, 3);
        ratioNumber(0);
        System.out.println(negativeNumber(-1));
        printStringAndNumber("Hello world", 2);
        System.out.println(leapYear(24));
        integerArray();
        arrayOneHundred();
        arrayMult();
        twoDimensionalArray();
        int[] resultArray = returnArray(5, 7);
    }
}
