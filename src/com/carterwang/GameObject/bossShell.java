package com.carterwang.GameObject;

import com.carterwang.CONSTANT.CONSTANT;

import java.awt.*;

public class bossShell extends Shell {

    private double degree;

    public bossShell() {
    }

    public bossShell(double x, double y, int width, int height, int speed) {
        super(x, y, width, height, speed);
        this.degree = (Math.random() * Math.PI * 2);
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public void AddXY() {
        double tmpx = x + speed * Math.cos(degree);
        double tmpy = y + speed * Math.sin(degree);
        this.x = tmpx;
        this.y = tmpy;
        //炮弹触壁反弹
        if (x < 0 || x > (CONSTANT.width - width)) {
            degree = Math.PI - degree;
        }
        if (y < 25) {
            degree = Math.PI * 2 - degree;
        }
    }

    @Override
    public void drawSelf(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.orange);

        g.fillOval((int)x,(int)y,width,height);

        g.setColor(c);
        AddXY();
    }
}
