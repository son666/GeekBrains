package ru.geekbrains.java_three.lesson01;

import ru.geekbrains.java_three.lesson01.fruit.Apple;
import ru.geekbrains.java_three.lesson01.fruit.Box;
import ru.geekbrains.java_three.lesson01.fruit.Orange;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Apple[] apples = {new Apple("apple1"), new Apple("apple2")};
        Orange[] oranges = {new Orange("orange1"), new Orange("orange2")};
        //Test method arrayToArrayList
        ArrayList<Apple> listApple = arrayToArrayList(apples);
        ArrayList<Orange> listOrange = arrayToArrayList(oranges);

        Box<Apple> appleBox = new Box<>(listApple);
        Box<Orange> orangeBox = new Box<>(listOrange);
        //Test method getWeight
        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        //Test method compare
        System.out.println(appleBox.compare(orangeBox));

        //Test method movingFruit
        Box<Apple> appleBoxTwo = new Box<>();
        appleBoxTwo.addFruit(new Apple("apple3"));
        appleBoxTwo.addFruit(new Apple("apple4"));
        appleBoxTwo.movingFruit(appleBox);
        System.out.println(appleBoxTwo.getAllFruit());
        System.out.println(appleBox.getAllFruit());

    }

    public static void swapObj(Object[] arr, int n1, int n2) {
        Object temp = arr[n1];
        arr[n1] = arr[n2];
        arr[n2] = temp;
    }

    public static <T> ArrayList<T> arrayToArrayList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}
