package ru.geekbrains.java_one.lesson5.abstractclass;

public abstract class Waterfowl extends Animal {
    private int distanceSail;

    public Waterfowl(String name, int distanceRun, int distanceSail, double heightJump) {
        super(name, distanceRun, heightJump);
        this.distanceSail = distanceSail;
    }

    public boolean sail(int distanceSail) {
        return this.distanceSail >= distanceSail;
    }

    @Override
    public String toString() {
        return super.toString() + ", Sail max: " + distanceSail;
    }
}
