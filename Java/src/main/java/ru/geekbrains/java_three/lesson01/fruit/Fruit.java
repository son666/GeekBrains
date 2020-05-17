package ru.geekbrains.java_three.lesson01.fruit;

public abstract class Fruit {
    private String name;
    private float weight;

    public Fruit(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + getName() + '\'' +
                ", weight=" + getWeight() +
                '}';
    }
}
