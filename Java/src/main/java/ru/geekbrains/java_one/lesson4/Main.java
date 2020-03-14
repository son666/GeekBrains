package ru.geekbrains.java_one.lesson4;

import java.math.BigDecimal;
import java.util.Random;

public class Main {

    public static void salaryIncrease (Employee[] arrayEmployee, int age, BigDecimal increasSalary) {
        for (Employee employee : arrayEmployee) {
            if (employee.getAge() > age) {
                BigDecimal salary = employee.getSalary();
                employee.setSalary(salary.add(increasSalary));
            }
        }
    }

    public static void main(String[] args) {
        Employee[] arrEmployee = new Employee[5];
        arrEmployee[0] = new Employee("Поляков", new BigDecimal(50000.00), 34, "Бухгалтер");
        arrEmployee[1] = new Employee("Иванов", new BigDecimal(75000.00), 46, "Менеджер");
        arrEmployee[2] = new Employee("Сидоров", new BigDecimal(90000.00), 44, "Руководитель");
        arrEmployee[3] = new Employee("Петров", new BigDecimal(120000.00), 40, "Начальник отдела");
        arrEmployee[4] = new Employee("Котов", new BigDecimal(120000.00), 47, "Начальник департамента");

        for (Employee employee : arrEmployee) {
            if (employee.getAge() > 45) System.out.println(employee);
        }

        salaryIncrease(arrEmployee, 45, new BigDecimal(5000.50));
        for (Employee employee : arrEmployee) {
            if (employee.getAge() > 45) System.out.println(employee);
        }

    }
}
