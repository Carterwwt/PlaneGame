package com.carterwang.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    BufferedImage img;
    double x,y;
    int width,height;
    int speed;

    /**
     * Constructor
     */
    public GameObject(){
    }

    public GameObject(BufferedImage img, double x, double y, int speed){
        this.img = img;
        this.x = x;
        this.y = y;
        this. speed = speed;
    }

    public GameObject(double x, double y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public GameObject(BufferedImage img, double x, double y, int width, int height, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getRect() {
        return new Rectangle((int)x,(int)y,width,height);
    }

}

