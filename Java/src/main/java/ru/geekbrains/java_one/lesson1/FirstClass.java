package ru.geekbrains.java_one.lesson1;

public class FirstClass {

    //Задание 1
    private static double resultExpression(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    //Задание 2
    private static boolean isWithinNumbers(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20) ? true : false;
    }

    //Задание 3
    private static void positiveOrNegativeNum(int num) {
        String result = (num < 0) ? "Отрицательное" : "Положительное";
        System.out.println(result);
    }

    //Задание 4
    private static String printHello(String name) {
        return "Привет, " + name + "!";
    }

    //Задание *
    private static String leapNonleapYears(int year) {
        if (((year % 4) == 0 && (year % 100) != 0) || ((year) % 4 == 0 && ((year % 100) == 0) && ((year % 400) == 0))) {
            return "Год високосный!";
        }
        else {
            return "Год не високосный!";
        }
    }

    public static void main(String[] args) {
        System.out.println(printHello("Александр"));
        System.out.println(leapNonleapYears(2020));

    }
}
