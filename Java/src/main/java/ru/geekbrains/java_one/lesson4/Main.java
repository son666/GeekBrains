package ru.geekbrains.java_one.lesson4;

import java.math.BigDecimal;

public class Main {

    public static void salaryIncrease(Employee[] arrayEmployee, int age, BigDecimal increaseSalary) {
        for (Employee employee : arrayEmployee) {
            if (employee.getAge() > age) {
                BigDecimal salary = employee.getSalary();
                employee.setSalary(salary.add(increaseSalary));
            }
        }
    }

    public static double absEmployeesAge(Employee[] arrayEmployee) {
        int sumAge = 0;
        for (int i = 0; i < arrayEmployee.length; i++) {
            sumAge += arrayEmployee[i].getAge();
        }
        return sumAge / arrayEmployee.length;
    }

    public static BigDecimal absEmployeesSalary(Employee[] arrayEmployee) {
        BigDecimal sumSalary = new BigDecimal(0);
        for (int i = 0; i < arrayEmployee.length; i++) {
            sumSalary = sumSalary.add(arrayEmployee[i].getSalary());
        }
        return sumSalary.divide(new BigDecimal(arrayEmployee.length));
    }

    public static void printEmployees(Employee[] arrayEmployee, int age) {
        for (Employee employee : arrayEmployee) {
            if (employee.getAge() > age) System.out.println(employee);
        }
    }

    public static void main(String[] args) {
        Employee[] arrEmployee = new Employee[5];
        arrEmployee[0] = new Employee("Поляков", new BigDecimal(65000.00), 34, "Бухгалтер");
        arrEmployee[1] = new Employee("Иванов", new BigDecimal(75000.00), 46, "Менеджер");
        arrEmployee[2] = new Employee("Сидоров", new BigDecimal(90000.00), 44, "Руководитель");
        arrEmployee[3] = new Employee("Петров", new BigDecimal(100000.00), 40, "Начальник отдела");
        arrEmployee[4] = new Employee("Котов", new BigDecimal(125000.50), 47, "Начальник департамента");

        //#5
        printEmployees(arrEmployee, 40);
        //#6
        salaryIncrease(arrEmployee, 45, new BigDecimal(5000));

        //#7
        System.out.println("Среднее по зарплате " + absEmployeesSalary(arrEmployee));
        System.out.println("Среднее по возрасту " + absEmployeesAge(arrEmployee));

    }
}
