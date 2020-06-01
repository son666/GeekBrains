package ru.geekbrains.java_three.lesson06;

import ru.geekbrains.java_two.lesson2.exception.ArrayDataException;
import ru.geekbrains.java_two.lesson2.exception.ArraySizeException;

public class MainArray {

    public int[] createNewArray(int[] arr) throws ArraySizeException, ArrayDataException {
        int[] resultArr;
        if (arr.length == 0) {
            throw new ArraySizeException(arr.length);
        } else if (arr[arr.length - 1] == 4) {
            throw  new ArrayDataException(String.valueOf(arr[arr.length - 1]), "Последний элемент не должен равняться 4!");
        } else {
            int index = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] == 4) {
                    index = i + 1;
                    break;
                }
            }
            if (index == 0) {
                throw new RuntimeException("В массиве нет элементов со значением 4!");
            }
            resultArr = new int[arr.length - index];
            System.arraycopy(arr, index, resultArr, 0, arr.length - index);
        }
        return resultArr;
    }

    private boolean containsArray(final int[]array, final int v) {
        boolean result = false;
        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean checkArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (containsArray(arr, 1) && containsArray(arr, 4)) {
                return true;
            }
        }
        return false;
    }
}
