package ru.geekbrains.java_one.lesson1;

public class FirstClass {

    //Задание 1
    private static double resultExpression(int a, int b, int c, int d) {
        return a * (b + (c / (d * 1.0)));
    }

    //Задание 2
    private static boolean isWithinNumbers(int a, int b) {
        int sum = a + b;
        return  sum <= 20 && sum >= 10;
    }

    //Задание 3
    private static boolean isPositive(int num) {
        return num >= 0;
    }

    //Задание 4
    private static String printHello(String name) {
        return "Привет, " + name + "!";
    }

    //Задание *
    private static boolean isLeapYears(int year) {
        return  (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        System.out.println(printHello("Александр"));
        System.out.println(isLeapYears(2020));

    }
}
