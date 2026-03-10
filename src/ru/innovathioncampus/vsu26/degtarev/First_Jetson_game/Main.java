package ru.innovathioncampus.vsu26.degtarev.First_Jetson_game;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //String person = "\uD83E\uDDD9\u200D";
        //int personLive = 3;

        String monster = "\uD83E\uDDDF\u200D";
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


        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Random r = new Random();
        for (int i = 0; i <= countMonster; i++) {
            board[r.nextInt(sizeBoard - 1)][r.nextInt(sizeBoard)] = monster;
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
                    board[person1.y - 1][person1.x - 1] = person1.image;
                    for (String[] raw : board) {
                        System.out.println(wall);
                        for (String col : raw) {
                            System.out.print(leftBlock + col + " ");
                        }
                        System.out.println(rightBlock);
                    }
                    System.out.println(wall);


                    System.out.println("Количество жизней:\t" + person1.live + "\n");

                    System.out.println("Введите куда будет ходить персонаж(ход возможен только по вертикали и горизонтали на одну клетку;" +
                            "\nКоординаты персонажа - (x: " + person1.x + ", y: " + person1.y + "))");
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    System.out.println(x + ", " + y);

                    // проверка
                    if (person1.isMoveCorrect(x,y)) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[person1.y - 1][person1.x - 1] = "  ";
                            person1.move(x,y);
                            step++;
                            System.out.println("Ход корректный; Новые координаты: " + person1.x + ", " + person1.y +
                                    "\nХод номер: " + step);
                        }else if (board[y - 1][x - 1].equals(castle)) {
                            System.out.println("Вы прошли игру!");
                            break;
                        } else {
                            System.out.println("Решите задачу.");
                            if (taskMonster(difficultGame)) {
                                board[person1.y - 1][person1.x - 1] = " ";
                                person1.move(x,y);
                            } else {
                               person1.otnimatZhizn();
                            }
                        }
                    } else {
                        System.out.println("Координаты не изменены");
                    }

                    if (person1.live == 0) {
                        Random p = new Random();
                        int t = p.nextInt(400);
                        int i = p.nextInt(400);
                        int trueAnswer = x + y;
                        System.out.println("Реши пример: " + t + " + " + i + " = ?");;
                        int ans = sc.nextInt();
                        if (trueAnswer == ans) {
                            System.out.println("Верно! Ты победил монстра");
                            person1.live++;
                        }
                        System.out.println("Ты проиграл эту битву!");
                        person1.otnimatZhizn();
                    }
                    if (person1.live <= 0) {
                        break;
                    }
                }

                System.out.println("Закончились жизни. Итог: ...");
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены неккоректно");
        }
    }
     static boolean taskMonster(int key){
        if (key == 1) {
            Random r = new Random();
            int x = r.nextInt(100);
            int y = r.nextInt(100);
            int trueAnswer = x + y;
            System.out.println("Реши пример: " + x + " + " + y + " = ?");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            if (trueAnswer == ans) {
                System.out.println("Верно! Ты победил монстра");
                return true;
            }
            System.out.println("Ты проиграл эту битву!");
            return false;

        } else {
            /////////
        }
         return false;
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
