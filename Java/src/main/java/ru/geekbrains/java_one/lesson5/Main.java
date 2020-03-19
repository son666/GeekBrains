package ru.geekbrains.java_one.lesson5;

import ru.geekbrains.java_one.lesson5.abstractclass.Animal;

public class Main {

    private static void printActionRun(Animal[] arrayAnimals, int distance) {
        System.out.println("Бег на дистанцию " + distance + "м.");
        for (Animal animal : arrayAnimals) {
            System.out.println(animal + " --> " + ((animal.run(distance)) ? "Пробежал!" : "Не пробежал!"));
        }
        System.out.println(String.format("%40s", "").replaceAll("", "-"));
    }

    private static void printActionSail(Animal[] arrayAnimals, int distance) {
        System.out.println("Заплыв на дистанцию " + distance + "м.");
        for (Animal animal : arrayAnimals) {
                System.out.println(animal + " --> " + ((animal.sail(distance)) ? "Проплыл!" : "Не проплыл!"));
            }
        System.out.println(String.format("%40s", "").replaceAll("", "-"));
    }

    private static void printActionJump(Animal[] arrayAnimals, double distance) {
        System.out.println("Прыжок на высоту " + distance + "м.");
        for (Animal animal : arrayAnimals) {
            System.out.println(animal + " --> " + ((animal.jump(distance)) ? "Прыгнул!" : "Не прыгнул!"));
        }
        System.out.println(String.format("%40s", "").replaceAll("", "-"));
    }

    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("Собака1", 400, 8, 0.5),
                new Dog("Собака2", 600, 10, 1),
                new Cat("Кот1", 200, 2),
                new Cat("Кот2", 150, 2.5),
                new Horse("Лошадь1", 1500, 100, 3),
                new Horse("Лошадь2", 900, 150, 3.5),
                new Bird("Птица1", 5, 0.2),
                new Bird("Птица2", 10, 0.5)
        };

//        printActionRun(animals, 450);
//        printActionJump(animals,0.5);
        printActionSail(animals,10);

    }
}
