package ru.innovathioncampus.vsu26.degtarev.First_Jetson_game;

import java.util.Scanner;

public class BigMonster extends Monster {
    public String getImage() {
        return "\uD83D\uDC79";
    }
    @Override
    public boolean taskMonster(int difficultGame){
        System.out.println("Решите задачу:");
        int x = r.nextInt(15 + difficultGame);
        int y = r.nextInt(10);
        int z = r.nextInt(20);
        int trueAnswer = x * y - z;
        System.out.println("Реши пример: " + x + " * " + y + " - " + z + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        }
        System.out.println("Ты проиграл эту битву!");
        return false;
    }
    BigMonster(int sizeBoard) {
        super(sizeBoard);
    }

}
