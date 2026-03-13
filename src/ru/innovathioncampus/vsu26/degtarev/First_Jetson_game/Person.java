package ru.innovathioncampus.vsu26.degtarev.First_Jetson_game;

import java.util.Random;

public class Person {
    private int x, y; // координаты персонажа
    private String image = "\\uD83E\\uDDD9\\u200D"; // вид персонажа на поле
    private int live = 3; // количество жизней
    public boolean isMoveCorrect(int x, int y) {
        return this.x == x && Math.abs(this.y - y) == 1 || this.y == y && Math.abs(this.x - x) == 1;
    }
    void move(int newX, int newY) {
        x = newX;
        y = newY;
    }
    void otnimatZhizn() {
         live --;
    }
    Person(int size) {
        y = size;
        Random r = new Random();
        int n = r.nextInt(size);
        x = n == 0 ? 1 : n;
    }
    Person(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Person() {
        x = 1;
        y = 1;
    }
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getLive() {
        return live;
    }
    public String getImage(){
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void downLive() {
        if (live < 0)
            live = 0;
        else
            live--;
    }
}
