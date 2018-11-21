package com.carterwang.GameObject;

import com.carterwang.CONSTANT.CONSTANT;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Plane extends GameObject{

    int HP = 100;
    int score =0;

    boolean left,right,up,down;
    private boolean live = true;

    /**
     * Constructor
     */
    public Plane(BufferedImage img, double x, double y, int width, int height, int speed) {
        super(img, x, y, width, height, speed);
    }

    public Plane(){
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    /**
     * Method
     */
    public void drawPlane (Graphics g){
        g.drawImage(img,(int)x,(int)y,null);
        if(left && (x-speed) > 0){
            x -= speed;
        }
        if(right && (x+speed+CONSTANT.planewidth < CONSTANT.width) ){
            x += speed;
        }
        if(up && (y-speed) > 0){
            y -= speed;
        }
        if(down && (y+speed+CONSTANT.planeheight) < CONSTANT.height){
            y += speed;
        }
    }

    public void drawINFO(Graphics g) {

        Color c = g.getColor();
        Font f = g.getFont();

        g.setColor(Color.white);
        Font font = new Font("Courier",Font.BOLD,19);
        g.setFont(font);
        g.drawString("Score:" + score,10,CONSTANT.height-30);
        g.drawString("HP:" + HP,CONSTANT.width-75,CONSTANT.height-30);

        g.setColor(c);
        g.setFont(f);
    }

    public void addDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    public void minusDirection(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

    public void addScore() {
        score++;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle((int)x+15,(int)y+10,width-30,height);
    }

}
