package ru.geekbrains.java_one.lesson4;

import java.math.BigDecimal;

public class Employee {
    private static int id;
    private int number;
    private String surname;
    private BigDecimal salary;
    private int age;
    private String position;

    public Employee (String surname, BigDecimal salary, int age, String position) {
        this.surname = surname;
        this.salary = salary;
        this.age = age;
        this.position = position;
        this.number = ++id;
    }

    public String getSurname() {
        return surname;
    }

    public BigDecimal getSalary() {
        return salary.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    //4 Вывести при помощи методов из пункта 3 ФИО и должность
    @Override
    public String toString() {
        return "Фамилия: " + getSurname() + "\n" +
               "Должность: " + getPosition() + "\n" +
                "---------------------------------";
    }
}
