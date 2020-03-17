package ru.geekbrains.java_one.lesson5;

public class Horse extends Animal {

    public Horse(String name, int distanceRun, int distanceSail, double heightJump) {
        super(name, distanceRun, distanceSail, heightJump);
    }

    //плыть
    public boolean sail(int distanceSail) {
        return this.distanceSail >= distanceSail;
    }
}
