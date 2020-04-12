package ru.geekbrains.java_two.lesson3;

import java.util.*;

public class PhoneBook {
    private Map<String, List<Person>> mapPhoneBook = new HashMap<>();

    public PhoneBook(List<Person> listPerson) {
        for (Person person : listPerson) {
            if (mapPhoneBook.containsKey(person.getFio())) {
                mapPhoneBook.get(person.getFio()).add(person);
            } else {
                mapPhoneBook.put(person.getFio(), new ArrayList<>(Arrays.asList(person)));
            }
        }
    }

    public List<String> searchPhoneNumber(String lastName) {
        List<Person> listSearchPerson = mapPhoneBook.get(lastName);
        List<String> listSearchPhone = new ArrayList<>();
        if (listSearchPerson == null) {
            return null;
        } else {
            for (Person person : listSearchPerson) {
                for (String phone : person.getListPhone()) {
                    listSearchPhone.add(phone);
                }
            }
            return listSearchPhone;
        }
    }

    public List<String> searchEmail(String lastName) {
        List<Person> listSearchPerson = mapPhoneBook.get(lastName);
        List<String> listSearchEmail = new ArrayList<>();
        if (listSearchPerson == null) {
            return null;
        } else {
            for (Person person : listSearchPerson) {
                for (String phone : person.getListEmail()) {
                    listSearchEmail.add(phone);
                }
            }
            return listSearchEmail;
        }
    }
}