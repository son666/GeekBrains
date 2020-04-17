package ru.geekbrains.java_two.lesson5.online;

public class MyThread extends Thread {

    int multiply;

    MyThread(int multiply) {
        super("MyThread");
        this.multiply = multiply;
        start();
    }

    @Override
    public void run() {
        System.out.println("0");
        try {
            sleep(multiply * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
