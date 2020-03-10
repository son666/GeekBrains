package ru.geekbrains.java_one.lesson2;

import java.util.Arrays;

public class LessonTwo {

    //#1
    public static void replacingArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0; //a[i] + 1 % 2
        }
    }

    //#2
    public static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 3 + 1;
        }
    }

    //#3
    public static void arrayMultiplication(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) array[i] *= 2;
        }
    }

    //#4
    public static int maxNum(int[] array) {
        int maxNum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maxNum < array[i]) maxNum = array[i];
        }
        return maxNum;
    }

    //#4
    public static int minNum(int[] array) {
        int minNum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (minNum > array[i]) minNum = array[i];
        }
        return minNum;
    }

    //#5
    public static void fillArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1;
            array[i][array.length - 1 - i] = 1;
        }
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

    //#8 сдвиг влево на n == сдвигу впрово на на array.lenght - n
    public static void offsetArray (int[] array, int n) {
        boolean sign = n < 0;
        if (sign) n = -n;
        n %= array.length;

        int nextIndex = (sign) ? array.length - n : n;
        for (int i = 0; i < nextIndex; i++) {
            for (int j = 1; j < array.length; j++) {
                int tmp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = tmp;
            }
        }

    }

    public static void main(String[] args) {

        //Test method replacingArray
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        replacingArray(array);
        System.out.println(Arrays.toString(array));

        //Test method fillArray
        int[] arrEight = new int[8];
        fillArray(arrEight);
        System.out.println(Arrays.toString(arrEight));

        //Test method arrayMultiplication
        int[] arrayMult = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        arrayMultiplication(arrayMult);
        System.out.println(Arrays.toString(arrayMult));
        //Задача #4
        System.out.println(maxNum(arrayMult));
        System.out.println(minNum(arrayMult));

        //Test method fillArray
        int[][] arrSquare = new int[10][10];
        fillArray(arrSquare);
        for (int y = 0; y < arrSquare.length; y++) {
            for (int x = 0; x < arrSquare[y].length; x++) {
                System.out.printf("%4d", arrSquare[y][x]);
            }
            System.out.println();
        }

        //Test method arrayMultiplication isCheckBalance
        System.out.println(isCheckBalance(arrayMult));

        //Test methods offsetArray, offsetCopyArray
        int[] arrayOffset = {1, 2, 3, 4};
        offsetArray(arrayOffset, 1);
        System.out.println(Arrays.toString(arrayOffset));
        System.out.println(Arrays.toString(offsetCopyArray(arrayOffset, -2)));

    }
}