package ru.geekbrains.java_one.lesson5;


import ru.geekbrains.java_one.lesson5.abstractclass.Animal;

public class Cat extends Animal {

    public Cat(String name, int distanceRun, int distanceSail, double heightJump) {
        super("Cat", name, distanceRun, distanceSail, heightJump);
    }

    @Override
    public int sail(int distanceSail) {
        return Animal.SWIM_WTF;
    }


}
