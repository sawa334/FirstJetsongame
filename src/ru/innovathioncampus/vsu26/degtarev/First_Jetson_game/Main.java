package ru.innovathioncampus.vsu26.degtarev.First_Jetson_game;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //String person = "\uD83E\uDDD9\u200D";
        //int personLive = 3;

        String castle = "\uD83C\uDFF0";
        int sizeBoard = 5;
        //int personX = 1;
        //int personY = sizeBoard;

        Person person1 = new Person(sizeBoard);






        int step = 0;
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";
        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }


        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1 - 5;
        Random r = new Random();

        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test;
        while (count <= countMonster) {
            if (r.nextBoolean()) {
                test = new Monster(sizeBoard);
            } else {
                test = new BigMonster(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;


        board[castleY][castleX] = castle;


        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);


        switch (answer) {
            case "ДА" -> {

                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                System.out.println("Выбранная сложность:\t" + difficultGame);

                int maxStep = 2;

                while (true) {
                    board[person1.getY() - 1][person1.getX() - 1] = person1.getImage();
                    outputBoard(board, person1.getLive());

                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;" +
                            "\nКоординаты персонажа - (x: " + person1.getX() + ", y: " + person1.getY() + "))");
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    System.out.println(x + ", " + y);

                    // проверка
                    if (person1.isMoveCorrect(x,y)) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[person1.getY() - 1][person1.getX() - 1] = "  ";
                            person1.move(x,y);
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + person1.getX() + ", " + person1.getY() +
                                    "\nХод номер: " + step);
                        }else if (board[y - 1][x - 1].equals(castle)) {
                            System.out.println("Вы прошли игру!");
                            break;
                        } else {
                            System.out.println("Решите задачу.");
                            for (Monster monster : arrMonster) {
                                if (monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person1.getY() - 1][person1.getX() - 1] = "  ";
                                        person1.move(x, y);

                                    } else {
                                        person1.downLive();
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Координаты не изменены");
                    }


                    if (person1.getLive() <= 0) {
                        break;
                    }
                }

                System.out.println("Закончились жизни. Итог: ...");
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены неккоректно");
        }
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);


        System.out.println("Количество жизней:\t" + live + "\n");
    }




}
