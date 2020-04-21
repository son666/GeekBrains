package ru.geekbrains.java_two.lesson5.homework;

public class ThreadArr extends Thread {

    private float[] array;
    private int offset;

    public ThreadArr(float[] array, int offset) {
        this.array = array;
        this.offset = offset;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] *
                    Math.sin(0.2f + (i + offset) / 5) *
                    Math.cos(0.2f + (i + offset) / 5) *
                    Math.cos(0.4f + (i + offset) / 2));
        }
    }
}
