package ru.geekbrains.java_two.lesson2;

import ru.geekbrains.java_two.lesson2.exception.ArrayDataException;
import ru.geekbrains.java_two.lesson2.exception.ArraySizeException;
import ru.geekbrains.java_two.lesson2.exception.MyArrayException;

public class Main {
    private static final int ARR_LENGHT = 4;

    private static String[][] convertStringToArray(String str) throws MyArrayException {
        boolean isCheckLenghtArray = false;
        String[] strArr = str.split("\\n");
        String[][] result = new String[strArr.length][];
        if (result.length == ARR_LENGHT) {
            isCheckLenghtArray = true;
            for (int i = 0; i < result.length; i++) {
                result[i] = strArr[i].split("\\s");
                if (result[i].length != ARR_LENGHT) {
                    isCheckLenghtArray = false;
                }
            }
        }
        if (!isCheckLenghtArray) {
            throw new ArraySizeException(ARR_LENGHT);
        } else {
            return result;
        }
    }

    private static double halfSummElementArray(String[][] array) throws MyArrayException {
        int summ = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    summ += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    String element = "Array[" + i + "]" + "[" + j + "]";
                    throw new ArrayDataException(element, e.getMessage());
                }
            }
        }
        return summ / 2.0;
    }

    public static void main(String[] args) {
        try {
            String[][] str = convertStringToArray("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0");
            System.out.println(halfSummElementArray(str));
        } catch (MyArrayException e) {
            System.out.println(e.getMessage());
        }
    }
}
