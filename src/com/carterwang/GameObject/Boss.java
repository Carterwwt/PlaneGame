package com.carterwang.GameObject;


import com.carterwang.CONSTANT.CONSTANT;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Boss extends GameObject{
    private boolean live = true;
    private int bossIndex = 0;
    private int hp = 300;

    private ArrayList<bossShell> bossShells = new ArrayList<>();

    public Boss(double x, double y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public Boss(BufferedImage img, double x, double y, int width, int height, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        createShell();
    }

    public Boss(){
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getHp() {
        return hp;
    }

    public void setHp() {
        hp -= 5;
    }

    public ArrayList<bossShell> getBossShells() {
        return bossShells;
    }

    public void setBossShells(ArrayList<bossShell> bossShells) {
        this.bossShells = bossShells;
    }

    public void createShell() {
        for(int i=0;i<30;i++) {
            bossShell shell = new bossShell(x + img.getWidth() / 2, y + img.getHeight(), 20, 20, 2);
            bossShells.add(shell);
        }
    }

    public void addShells() {
        for(int i=0;i<30;i++) {
            bossShell shell  = new bossShell(x + img.getWidth() / 2, y + img.getHeight(), 20, 20, 2);
            bossShells.add(shell);
        }
    }

    public void drawBoss(Graphics g, Plane plane) {
        g.drawImage(img,(int)x,(int)y,null);
        drawHP(g);
        drawShell(g);
        bossIndex++;
        if(bossIndex % 6 == 0) {
            y += speed;
            if (x > plane.x) {
                x -= 1;
            } else if (x < plane.x) {
                x += 1;
            }
        }
    }

    public void drawHP(Graphics g) {
        Color c = g.getColor();
        Font f = g.getFont();

        g.setColor(Color.black);
        Font font = new Font("Courier",Font.BOLD,19);
        g.setFont(font);
        g.drawString("HP:" + hp,(int)(x+img.getWidth()/2 - 30),(int)(y+img.getHeight() + 15));

        g.setColor(c);
        g.setFont(f);
    }

    public void drawShell(Graphics g) {

        int count =0;

        for(int i=0;i<bossShells.size();i++) {
            bossShell shell = bossShells.get(i);
            shell.outOfBounds();
            if(shell.isLive()) {
                shell.drawSelf(g);
                count++;
            }
        }
        if(count < 10)
            addShells();
    }

    public boolean outOfBounds() {
        if(y > CONSTANT.height)
            return true;
        else
            return false;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle((int)getX(),(int)getY(),width,height - 50);
    }
}