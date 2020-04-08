package ru.geekbrains.java_two.lesson2.exception;

public class ArrayDataException extends MyArrayException {

    public ArrayDataException(String element, String message) {
        super(element + " contains not number --> " + message);
    }
}
