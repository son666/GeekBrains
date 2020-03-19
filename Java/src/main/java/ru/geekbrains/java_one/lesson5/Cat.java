package ru.geekbrains.java_one.lesson5;

import ru.geekbrains.java_one.lesson5.abstractclass.Animal;

public class Cat extends Animal {
    private int distanceRun;
    private double heightJump;

    public Cat(String name, int distanceRun, double heightJump) {
        super(name);
        this.distanceRun = distanceRun;
        this.heightJump = heightJump;
    }

    public boolean run(int distanceRun) {
        return this.distanceRun >= distanceRun;
    }

    public boolean sail(int distanceSail) {
        return false;
    }

    public boolean jump(double heightJump) {
        return this.heightJump >= heightJump;
    }

    public String toString() {
        return super.toString() + ", " +
                "Run max: " + distanceRun + ", " +
                "Jump max: " + heightJump + ", " +
                "Sail max: Не умеет плавать!";
    }
}
