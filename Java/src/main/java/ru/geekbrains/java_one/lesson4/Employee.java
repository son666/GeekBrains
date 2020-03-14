package ru.geekbrains.java_one.lesson4;

import java.math.BigDecimal;

public class Employee {
    private String surname;
    private BigDecimal salary;
    private int age;
    private String position;

    public Employee (String surname, BigDecimal salary, int age, String position) {
        this.surname = surname;
        this.salary = salary;
        this.age = age;
        this.position = position;
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

    @Override
    public String toString() {
        return "Фамилия: " + getSurname() + "\n" +
               "Должность: " + getPosition() + "\n" +
               "Зарплата: " + getSalary() + "\n" +
                "---------------------------------";
    }
}
