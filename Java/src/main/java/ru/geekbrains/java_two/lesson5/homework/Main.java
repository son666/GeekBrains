package ru.geekbrains.java_two.lesson5.homework;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static float[] calculateArr() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(String.format("One thread method: calculateArr() --> work %s ms", System.currentTimeMillis() - start));
        return arr;
    }

    public static float[] threadCalculateArr() {
        float[] arr = new float[size];
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }

        long start = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        Runnable arrThread1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        };
        Runnable arrThread2 = new Runnable() {
            @Override
            public void run() {
                int j = h;
                for (int i = 0; i < h; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                    j++;
                }
            }
        };
        Thread threadArr1 = new Thread(arrThread1);
        Thread threadArr2 = new Thread(arrThread2);
        threadArr1.start();
        threadArr2.start();
        while (threadArr1.isAlive() || threadArr2.isAlive()) { }
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(String.format("Two thread method: threadCalculateArr() --> work %s ms", System.currentTimeMillis() - start));
        return arr;
    }

    public static void main(String[] args) {
        System.out.println((Arrays.equals(calculateArr(), threadCalculateArr())) ? "Arrays equal" : "Arrays not equal");

    }
}
