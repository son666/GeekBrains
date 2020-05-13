package ru.geekbrains.java_three.lesson01.fruit;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> listFruit;

    public Box() {
        listFruit = new ArrayList<T>();
    }

    public Box(ArrayList<T> listFruit) {
        this.listFruit = listFruit;
    }

    public void addFruit(T fruit) {
        listFruit.add(fruit);
    }

    public float getWeight() {
        if (listFruit.size() == 0) {
            return 0.0f;
        }
        return listFruit.size() * listFruit.get(0).getWeight();
    }

    public boolean compare(Box<?> o) {
        return Math.abs(this.getWeight() - o.getWeight()) < 0.001f;
    }

    public void movingFruit(Box<? super T> objBox) {
        if (this != objBox) {
            objBox.listFruit.addAll(listFruit);
            listFruit.clear();
        }
    }

    public ArrayList<T> getAllFruit() {
        return listFruit;
    }
}
