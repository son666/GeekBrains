package ru.geekbrains.java_two.lesson5.homework;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static float[] calculateArr(float[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(String.format("One thread method: calculateArr() --> work %sms", System.currentTimeMillis() - start));
        return arr;
    }

    public static float[] threadCalculateArr(float[] arr) {
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        long start = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        ThreadArr firstThreadArr = new ThreadArr(arr1, 0);
        ThreadArr secondThreadArr = new ThreadArr(arr2, h);

        try {
            firstThreadArr.join();
            secondThreadArr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(String.format("Two thread method: threadCalculateArr() --> work %s ms", System.currentTimeMillis() - start));
        return arr;
    }

    public static void main(String[] args) {
        float[] arrOneThread = new float[size];
        Arrays.fill(arrOneThread, 1);
        float[] oneThread = calculateArr(arrOneThread);

        float[] arrTwoThread = new float[size];
        Arrays.fill(arrTwoThread, 1);
        float[] twoThread = threadCalculateArr(arrTwoThread);

        System.out.println((Arrays.equals(oneThread, twoThread)) ? "Arrays equal" : "Arrays not equal");
    }
}
