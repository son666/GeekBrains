package ru.geekbrains.java_one.lesson5;

import ru.geekbrains.java_one.lesson5.abstractclass.Animal;

public class Dog extends Animal {

    public Dog(String name, int distanceRun, int distanceSail, double heightJump) {
        super("Dog", name, distanceRun, distanceSail, heightJump);
    }

}
