package ru.geekbrains.java_one.lesson3;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '_';

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int win; //Количество подряд знаков для победы
    private static char[][] field;

    private static void initField() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        win = 4;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.println(String.format("%" + (fieldSizeX * 4) + "s", "").replace(' ', '-'));
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + " | ");
            }
            System.out.println();
        }
    }

    public static boolean isValidCell (int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    public static boolean isEmptyCell (int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до 3) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static void aiTurn() {
        if (!isAiTurn()) {
            int x;
            int y;
            do {
                x = RANDOM.nextInt(fieldSizeX);
                y = RANDOM.nextInt(fieldSizeY);
            } while (!isEmptyCell(x, y));
            field[y][x] = DOT_AI;
        }
    }

    //Попытка AI выиграть или блокировать человека
    private static boolean isAiTurn() {
        if (checkWin(DOT_AI, win-1)) {
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (field[y][x] == DOT_EMPTY) {
                        field[y][x] = DOT_AI;
                        if (checkWin(DOT_AI, win)) {
                            return true;
                        }
                        else { field[y][x] = DOT_EMPTY; }
                    }
                }
            }
        } else if (checkWin(DOT_HUMAN, win-1)) {
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (field[y][x] == DOT_EMPTY) {
                        field[y][x] = DOT_HUMAN;
                        if (checkWin(DOT_HUMAN, win)) {
                            field[y][x] = DOT_AI;
                            return true;
                        }
                        else { field[y][x] = DOT_EMPTY; }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    //Проверка победы
    private static boolean checkWin(char c, int win) {
        int leftDiagonal = 0;
        int maxLeftDiagonal = 0;
        int rightDiagonal = 0;
        int maxRightDiagonal = 0;
        for (int y = 0; y < fieldSizeY; y++) {
            int countY = 0;
            int countX = 0;
            int maxCountX = 0;
            int maxCountY = 0;
            for (int x = 0; x < fieldSizeX; x++) {
                //Поиск совпадений по строкам
                if (field[y][x] == c) { countX++; }
                if (maxCountX < countX) {
                    maxCountX = countX;
                } else { countX = 0; }
                //Поиск совпадений по столбцам
                if (field[x][y] == c) { countY++; }
                if (maxCountY < countY) {
                    maxCountY = countY;
                } else { countY = 0; }

                if (maxCountX >= win || maxCountY >= win) return true;
            }
            //Поиск совпадений по левой диагонали
            if (field[y][y] == c) { leftDiagonal++; }
            if (maxLeftDiagonal < leftDiagonal) {
                maxLeftDiagonal = leftDiagonal;
            } else { leftDiagonal = 0; }
            //Поиск совпадений по правой диагонали
            if (field[y][field.length - 1 - y] == c) { rightDiagonal++; }
            if (maxLeftDiagonal < rightDiagonal) {
                maxRightDiagonal = rightDiagonal;
            } else { rightDiagonal = 0; }

            if (maxLeftDiagonal >= win || maxRightDiagonal >= win) return true;
        }
        return false;
    }

    private static void playOneRound() {
        initField();
        printField();
        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_HUMAN, win)) {
                System.out.println("Выиграл игрок!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_AI, win)) {
                System.out.println("Выиграл компьютер!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
        playOneRound();
        System.out.println("Play again?");
        if (SCANNER.next().equals("no"))
            break;
        }
    }
}
