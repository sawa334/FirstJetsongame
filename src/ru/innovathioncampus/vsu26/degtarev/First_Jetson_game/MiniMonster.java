package ru.innovathioncampus.vsu26.degtarev.First_Jetson_game;

import java.util.Scanner;

public class MiniMonster extends Monster {
    public String getImage() {
        return "\uD83D\uDC7E";
    }
    @Override
    public boolean taskMonster(int difficultGame){
        int k = r.nextInt(12) + 1;
        if(r.nextBoolean()){
            k = -k;
        }
        int b = r.nextInt( 10 + difficultGame);
        int x = r.nextInt(20);
        if(r.nextBoolean()){
            x = -x;
        }
        int c = k*x+b;



        System.out.println("Решите задачу:");
        System.out.println("Реши уравнение: " + k + " * x + " + b + " = " +c);
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (x == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        }
        System.out.println("Ты проиграл эту битву!");
        return false;
    }
    MiniMonster(int sizeBoard) {
        super(sizeBoard);
    }

}
