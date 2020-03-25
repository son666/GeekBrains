package ru.geekbrains.java_one.lesson6;

/*
1. Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
2. Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
3. * Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
4. ** Написать метод, проверяющий, есть ли указанное слово в папке
 */

import java.io.*;
import java.util.Scanner;

public class Main {

    //TODO сделать метод генерирующий последовательности букв
    private static final String[][] FILE_PATH_ARRAY = {
            {"C:\\Dell\\firstFile.txt", "Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);"},
            {"C:\\Dell\\secondFile.txt", "Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго."}
    };
    private static String[] fileCreate = new String[FILE_PATH_ARRAY.length];
    private static int createFiles = 0;

    private static boolean createFile(String pathFile, String text) {
        try {
            PrintStream file = new PrintStream(new FileOutputStream(pathFile));
            file.print(text);
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Убрать Scanner и работать с байтами
    private static boolean mergeFile(String filePathResult, String filePathRead) {
        try {
            PrintStream fileMerge = new PrintStream(new FileOutputStream(filePathResult, true));
            Scanner reader = new Scanner(new FileInputStream(filePathRead));
            while (reader.hasNext()) {
                fileMerge.print(reader.nextLine());
            }
            reader.close();
            fileMerge.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO Убрать Scanner и работать с байтами
    private static boolean searchWord(String pathFile, String wordSearch) {
        StringBuilder string = new StringBuilder("");
        try {
            Scanner reader = new Scanner(new FileInputStream(pathFile));
            while (reader.hasNext()) {
                string.append(reader.nextLine());
                if (string.substring(0).contains(" " + wordSearch + " ")) {
                    reader.close();
                    return true;
                }
                string.delete(0, string.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static File searchWordInDir(String dirPath, String wordSearch) {
        File dirFile = new File(dirPath);
        if (dirFile.isDirectory()) {
            for (File file : dirFile.listFiles()) {
                if (!file.isDirectory()) {
                    if (searchWord(file.getAbsolutePath(), wordSearch)) {
                        return file;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //#1
        for (int i = 0; i < FILE_PATH_ARRAY.length; i++) {
            for (int j = 1; j < FILE_PATH_ARRAY[i].length; j++) {
                if (createFile(FILE_PATH_ARRAY[i][0], FILE_PATH_ARRAY[i][j])) {
                    fileCreate[i] = FILE_PATH_ARRAY[i][0];
                    System.out.println("File: " + FILE_PATH_ARRAY[i][0] + " create!");
                    createFiles++;
                }
            }
        }

        //#2
        String mergeFilePath = "C:\\Dell\\mergeFile.txt";
        if (createFiles > 0) {
            System.out.println("Объединение файлов:");
            for (String file : fileCreate) {
                System.out.print("Merge files: " + mergeFilePath + " + " + file + " --> ");
                System.out.println((mergeFile(mergeFilePath, file)) ? "Ok" : "Error");
            }
        } else {
            System.out.println("Нет файлов для обьединения.");
        }

        //#3
        String filePath = "C:\\Dell\\searchWord.txt";
        String word = "depends";
        System.out.print("Search word \"" + word + "\" in file " + filePath + " --> ");
        System.out.println((searchWord(filePath, word)) ? "Word found" : "Word not found");

        //#4
        String dir = "C:\\Dell";
        System.out.println("Search word \"" + word + "\" in directory " + dir);
        File fileFound = searchWordInDir(dir, word);
        System.out.println((fileFound != null) ? "Word found in file --> " + fileFound.getName() : "Word not found");
    }
}
