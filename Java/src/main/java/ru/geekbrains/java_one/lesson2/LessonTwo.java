package ru.geekbrains.java_one.lesson2;

import java.util.Arrays;

public class LessonTwo {

    //#1
    public static int[] replacingArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }
        return array;
    }

    //#2
    public static int[] fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 3 + 1;
        }
        return array;
    }

    //#3
    public static int[] arrayMultiplication(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] < 6) ? array[i] * 2 : array[i];
        }
        return array;
    }

    //#4
    public static int maxNum(int[] array) {
        int maxNum = array[0];
        for (int i = 1; i < array.length; i++) {
            maxNum = (maxNum < array[i]) ? array[i] : maxNum;
        }
        return maxNum;
    }

    //#4
    public static int minNum(int[] array) {
        int minNum = array[0];
        for (int i = 1; i < array.length; i++) {
            minNum = (minNum > array[i]) ? array[i] : minNum;
        }
        return minNum;
    }

    //#6
    public static boolean isCheckBalance(int[] array) {
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < array.length; i++) {
            sumLeft += array[i];
            for (int j = (i + 1); j < array.length; j++) {
                sumRight += array[j];
            }
            if (sumLeft == sumRight) return true;
            sumRight = 0;
        }
        return false;
    }

    //#7
    public static int[] offsetCopyArray(int[] array, int n) {
        int[] newArray = new int[array.length];
        n = Math.abs(n) % array.length;
        //shift right
        if (n > 0) {
            System.arraycopy(array, 0, newArray, n, array.length - n);
            System.arraycopy(array, array.length - n, newArray, 0, n);
        }
        //shift left
        else {
            System.arraycopy(array, n, newArray, 0, array.length - n);
            System.arraycopy(array, 0, newArray, array.length - n, n);
        }
        return newArray;
    }

    //#8
    public static int[] offsetArray (int[] array, int n) {
        n = Math.abs(n) % array.length;
        //shift right
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = array.length - 2; j >= 0 ; j--) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        //shift left
        else {
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < array.length ; j++) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {

        //Test method replacingArray
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        System.out.println(Arrays.toString(replacingArray(array)));

        //Test method fillArray
        int[] arrEight = new int[8];
        System.out.println(Arrays.toString(fillArray(arrEight)));

        //Test method arrayMultiplication
        int[] arrayMult = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(arrayMultiplication(arrayMult)));
        //Задача #4
        System.out.println(maxNum(arrayMult));
        System.out.println(minNum(arrayMult));

        //Задача #5
        int[][] arrSquare = new int[15][15];
        for (int y = 0; y < arrSquare.length; y++) {
            for (int x = 0; x < arrSquare[y].length; x++) {
                System.out.printf("%4d", (x == y) ? 1 : x);
            }
            System.out.println();
        }

        //Test method arrayMultiplication isCheckBalance
        System.out.println(isCheckBalance(arrayMult));

        //Test methods offsetArray, offsetCopyArray
        int[] arrayOffset = {1, 2, 3, 4};
        System.out.println(Arrays.toString(offsetArray(arrayOffset, 1)));
        System.out.println(Arrays.toString(offsetCopyArray(arrayOffset, -2)));

    }
}