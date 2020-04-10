package ru.geekbrains.java_two.lesson2.exception;

public class ArraySizeException extends MyArrayException {

    public ArraySizeException(int arrayLenght) {
        super("Array lenght not equal " + arrayLenght + "x" + arrayLenght);
    }
}
