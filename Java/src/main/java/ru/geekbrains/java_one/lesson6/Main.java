package ru.geekbrains.java_one.lesson6;

/*
1. Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
2. Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
3. * Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
4. ** Написать метод, проверяющий, есть ли указанное слово в папке
 */

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    private static boolean createFile (String pathFile, String text) {
        try {
            PrintStream file = new PrintStream(new FileOutputStream(pathFile));
            file.println(text);
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean mergeFile(String filePathResult, String filePathRead) {
        StringBuilder string = new StringBuilder("");
        try {
            PrintStream fileMerge = new PrintStream(new FileOutputStream(filePathResult, true));
                Scanner reader = new Scanner(new FileInputStream(filePathRead));
                while (reader.hasNext()) {
                    string.append(reader.nextLine());
                }
            reader.close();
            fileMerge.println(string);
            fileMerge.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        //#1
        String [][] filePathArray = {
                {"C:\\firstFile.txt", "Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);"},
                {"C:\\secondFile.txt", "Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго."}
        };
        String [] fileCreate = new String[filePathArray.length];
        for (int i = 0; i < filePathArray.length; i++) {
            for (int j = 1; j < filePathArray[i].length; j++) {
                if (createFile(filePathArray[i][0], filePathArray[i][j])) {
                    fileCreate[i] = filePathArray[i][0];
                    System.out.println("Файл: " + filePathArray[i][0] + " создан!");
                }
            }
        }

        //#2
        String mergeFilePath = "C:\\mergeFile.txt";
        System.out.println("Объединение файлов:");
        for (String file : fileCreate) {
            System.out.print("Merge files: " + mergeFilePath + " + " + file + " --> ");
            System.out.println((mergeFile(mergeFilePath, file)) ? "Успешно" : "Ошибка");
        }
    }
}
