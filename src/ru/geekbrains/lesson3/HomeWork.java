package ru.geekbrains.lesson3;

import java.util.Random;
import java.util.Scanner;

public class HomeWork {
    public static char[][] field;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_PLAYER = 'X';
    public static final char DOT_AI = 'O';
    public static Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    public static void initField(){
        field = new char[SIZE][SIZE];
        for (int i = 0; i < field.length ; i++) {
            for (int j = 0; j < field.length ; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printField(){
        System.out.print("* ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void playerTurn(){
        int x, y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до 3) через пробел: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x,y) || !isCellEmpty(x, y));
        field[y][x] = DOT_PLAYER;
    }

    private static boolean isCellEmpty(int x,int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static boolean isCellValid(int x, int y){
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    private static void aiTurn(){
        int x, y;
        do {
            x = RANDOM.nextInt(SIZE);
            y = RANDOM.nextInt(SIZE);
        } while (!isCellEmpty(x, y));
        field[y][x] = DOT_AI;
    }

    private static boolean checkWin(char dot) { //условие проверки работает для любого поля, но для фишек
        int countDotsDiagonal = 0;              // кол-во которых равно длине поля :) для меньшего кол-ва не успел :(
        int countDotsHorizontal;
        int countDotsVertical;
        for (int i = 0; i < SIZE; i++) {
            if(field[i][i] == dot) countDotsDiagonal++; //проверяем диагонали
            else if(field[i][SIZE - i - 1] == dot) countDotsDiagonal++;
            countDotsHorizontal = 0;
            countDotsVertical = 0;
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == dot) countDotsHorizontal++; //проверяем вертикаль и горизонталь
                if (field[j][i] == dot) countDotsVertical++;
            }
            if(countDotsHorizontal == DOTS_TO_WIN) return true;
            if(countDotsVertical == DOTS_TO_WIN) return true;
        }
        return countDotsDiagonal == DOTS_TO_WIN;
    }




    public static boolean checkDraw() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    private static boolean gameChecks(char dot, String s) {
        if (checkWin(dot)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Draw!");
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        while(true) {
            initField();
            printField();
            while (true) {
                playerTurn();
                printField();
                if (gameChecks(DOT_PLAYER, "Player win!")) break;
                //aiTurn();
                //printField();
               // if (gameChecks(DOT_AI, "Skynet win!")) break;
            }
            System.out.println("Play again?");
                if (!SCANNER.next().equals("Y"))
                    break;
        }

    }
}

