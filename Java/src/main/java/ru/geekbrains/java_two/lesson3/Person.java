package ru.geekbrains.java_two.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
    private String fio;
    private List<String> listPhone;
    private List<String> listEmail;

    public Person(String fio, String[] arrPhone, String[] arrEmail) {
        this.fio = fio;
        listPhone = new ArrayList<>(Arrays.asList(arrPhone));
        listEmail = new ArrayList<>(Arrays.asList(arrEmail));
    }

    public String getFio() {
        return fio;
    }

    public void addPhone(String phone) {
        listPhone.add(phone);
    }

    public List<String> getListPhone() {
        return listPhone;
    }

    public void addEmail(String email) {
        listEmail.add(email);
    }

    public List<String> getListEmail() {
        return listEmail;
    }
}
