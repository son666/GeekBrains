package ru.geekbrains.java_two.lesson3;

import java.util.*;

public class PhoneBook {
    private Map<String, List<Person>> mapPhoneBook = new HashMap<>();
    private List<Person> listSearchPerson = new ArrayList<>();
    private List<String> listSearchContact = new ArrayList<>();

    private boolean isSearchPerson(String lastName) {
        listSearchPerson = mapPhoneBook.get(lastName);
        if (listSearchPerson == null) {
            return false;
        } else {
            listSearchContact.clear();
            return true;
        }
    }

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
        if (!isSearchPerson(lastName)) {
            return null;
        } else {
            for (Person person : listSearchPerson) {
                for (String phone : person.getListPhone()) {
                    listSearchContact.add(phone);
                }
            }
            return listSearchContact;
        }
    }

    public List<String> searchEmail(String lastName) {
        if (!isSearchPerson(lastName)) {
            return null;
        } else {
            for (Person person : listSearchPerson) {
                for (String phone : person.getListEmail()) {
                    listSearchContact.add(phone);
                }
            }
            return listSearchContact;
        }
    }
}