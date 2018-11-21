package com.carterwang.GameObject;

import com.carterwang.CONSTANT.CONSTANT;

import java.awt.*;

public class Aid extends GameObject {
    private double degree;
    private boolean picked = false;
    Image aidIMG;

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public double getDegree() {
        return this.degree;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public void AddXY() {
        double tmpx = x + speed * Math.cos(degree);
        double tmpy = y + speed * Math.sin(degree);
        this.x = tmpx;
        this.y = tmpy;
        //炮弹触壁反弹
        if (x < 0 || x > (CONSTANT.width - this.width)) {
            degree = Math.PI - degree;
        }
        if (y < 25 || y > (CONSTANT.height - this.height)) {
            degree = Math.PI * 2 - degree;
        }
    }


    public Aid(Image aidIMG) {
        this.x = Math.random() * (CONSTANT.width-this.width*2);
        this.y = CONSTANT.height/2;
        this.width = aidIMG.getWidth(null);
        this.height = aidIMG.getHeight(null);
        this.speed = 2;
        this.aidIMG = aidIMG;

        this.setDegree(Math.random() * Math.PI * 2);
    }

    public void draw(Graphics g) {
        Color c = g.getColor();

        g.setColor(Color.PINK);
        g.drawImage(aidIMG,(int)x,(int)y,null);

        g.setColor(c);//画笔颜色还原
        //坐标沿degree增加
        this.AddXY();
    }
}
