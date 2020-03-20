package ru.geekbrains.java_one.lesson5;

import ru.geekbrains.java_one.lesson5.abstractclass.Animal;

public class Bird extends Animal {

    public Bird(String name, int distanceRun, int distanceSail, double heightJump) {
        super("Bird", name, distanceRun, distanceSail, heightJump);

    }

    @Override
    public int sail(int distanceSail) {
        return Animal.SWIM_WTF;
    }

}
