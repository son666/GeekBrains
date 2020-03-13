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
        if (!isCheckWinAI() && !isBlockWinHuman()) {
            int x;
            int y;
            do {
                x = RANDOM.nextInt(fieldSizeX);
                y = RANDOM.nextInt(fieldSizeY);
            } while (!isEmptyCell(x, y));
            field[y][x] = DOT_AI;
        }
    }

    //Попытка AI выиграть
    private static boolean isCheckWinAI() {
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
        }
        return false;
    }

    //Блокировка Human
    private static boolean isBlockWinHuman() {
        if (checkWin(DOT_HUMAN, win-1)) {
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
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x <= (field[y].length - win); x++) {
                if (countXY(true, y, x, c, win) ||
                    countXY(false, x, y, c, win) ||
                    countDiagonal(true, x, c, win) ||
                    countDiagonal(false, x, c, win))
                    return true;
            }
        }
        return false;
    }

    //Поиск совпадений по строкам или столбцам
    private static boolean countXY(boolean isRow, int y, int x, char c, int win) {
            int count = 0;
            for (int j = 0; j < win; j++) {
                    if (((isRow) ? field[y][x + j] : field[y + j][x]) == c) count++;
            }
        return count == win;
    }

    //Поиск совпадений по диагоналям
    private static boolean countDiagonal(boolean isLeftDiagonal, int x, char c, int win) {
        int count = 0;
        for (int j = 0; j < win; j++) {
            if (((isLeftDiagonal) ? field[x + j][x + j] : field[x + j][field.length - 1 - (x + j)]) == c) count++;
        }
        return count == win;
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
