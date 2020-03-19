package ru.geekbrains.java_one.lesson5;

import ru.geekbrains.java_one.lesson5.abstractclass.Animal;

public class Horse extends Animal {
    private int distanceRun;
    private int distanceSail;
    private double heightJump;

    public Horse(String name, int distanceRun, int distanceSail, double heightJump) {
        super(name);
        this.distanceRun = distanceRun;
        this.distanceSail = distanceSail;
        this.heightJump = heightJump;
    }

    public boolean run(int distanceRun) {
        return this.distanceRun >= distanceRun;
    }

    public boolean sail(int distanceSail) {
        return this.distanceSail >= distanceSail;
    }

    public boolean jump(double heightJump) {
        return this.heightJump >= heightJump;
    }

    public String toString() {
        return super.toString() + ", " +
                "Run max: " + distanceRun + ", " +
                "Jump max: " + heightJump + ", " +
                "ail max: " + distanceSail;
    }

}