package ru.geekbrains.java_two.lesson3;

import java.util.*;

public class Main {
    private static String str = "Паттерн проектирования это типичный способ решения какой-либо часто встречающейся проблемы, возникающей при проектировании программ.\n" +
            "Паттерн не являются готовыми решениями, которые можно сразу скопировать в свой код. Они представляют собой общее описание решения проблемы,\n" +
            "которое после некоторой доводки можно использовать в самых разных ситуациях.";

    private static Set<String> searchWordInText(String[] str) {
        return new LinkedHashSet<>(Arrays.asList(str));
    }

    private static Map<String, Integer> countWordInText(String[] str) {
        Map<String, Integer> mapCountWord = new LinkedHashMap<>();
            for (int i = 0; i < str.length; i++) {
                if (mapCountWord.containsKey(str[i])) {
                    mapCountWord.put(str[i], mapCountWord.get(str[i]) + 1);
                } else {
                    mapCountWord.put(str[i], 1);
                }
        }
        return mapCountWord;
    }

    public static void main(String[] args) {
        //#1
        String[] arrWord = str.replaceAll("[,.]", "").split("\\s");
        System.out.println(searchWordInText(arrWord));
        System.out.println(countWordInText(arrWord));

        //#2
        Person person1 = new Person("Иванов", new String[]{"(111) 11-11-111"}, new String[]{"person1@mail.ru"});
        Person person2 = new Person("Сидоров", new String[]{"(333) 11-11-111"}, new String[]{"person2_0@mail.ru", "person2_1@mail.ru"});
        Person person3 = new Person("Иванов", new String[]{"(222) 11-11-111", "(222) 22-22-222"}, new String[]{"person3@mail.ru"});
        Person person4 = new Person("Петров", new String[]{"(444) 11-11-111", "(444) 22-22-222"}, new String[]{"person4_0@mail.ru"});
        List<Person> listPerson = new ArrayList<>(Arrays.asList(person1, person2, person4));
        PhoneBook phoneBook = new PhoneBook(listPerson);
        phoneBook.add(person3);

        String searchLastName = "Иванов";
        List<String> searchPhone = phoneBook.searchPhoneNumber(searchLastName);
        System.out.printf("Search phone for: %s \n%s", searchLastName, (searchPhone != null) ? searchPhone.toString() : "No search phone!");
        System.out.println("\n" + String.format("%40s", "").replaceAll("", "-"));
        List<String> searchEmail = phoneBook.searchEmail(searchLastName);
        System.out.printf("Search email for: %s \n%s", searchLastName, (searchEmail != null) ? searchEmail.toString() : "No search email!");

    }
}
