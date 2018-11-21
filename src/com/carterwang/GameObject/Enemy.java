package com.carterwang.GameObject;


import com.carterwang.CONSTANT.CONSTANT;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject{
    private boolean live = true;
    private boolean specialEnemy = false;

    private ArrayList<Shell> enemyshells = new ArrayList<>();

    public Enemy(double x, double y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public Enemy(BufferedImage img, double x, double y, int width, int height, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public Enemy(){
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isSpecialEnemy() {
        return specialEnemy;
    }

    public void setSpecialEnemy(boolean specialEnemy) {
        this.specialEnemy = specialEnemy;
    }

    public ArrayList<Shell> getEnemyshells() {
        return enemyshells;
    }

    public void setEnemyshells(ArrayList<Shell> enemyshells) {
        this.enemyshells = enemyshells;
    }

    public void drawEnemy(Graphics g, Plane plane) {
        g.drawImage(img,(int)x,(int)y,null);
        y += speed;
        if(x > plane.x) {
            x -= 1;
        } else if (x < plane.x) {
            x += 1;
        }
    }

    public static double randomPosition() {
        double x = Math.random()*(CONSTANT.width-CONSTANT.planewidth);
        return x;
    }

    public void outOfBounds() {
        if(y > CONSTANT.height)
            live = false;
    }
}
