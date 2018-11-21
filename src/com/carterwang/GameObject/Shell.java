package com.carterwang.GameObject;

import com.carterwang.CONSTANT.CONSTANT;

import java.awt.*;

public class Shell extends GameObject{
    boolean space = false;
    private boolean live = true;

    public Shell(){
        super();
    }

    public Shell(double x, double y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void drawSelf(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.PINK);

        g.fillOval((int)x,(int)y,width,height);
        y -= speed;

        g.setColor(c);

    }

    public void drawEnemyShell(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);

        g.fillOval((int)x,(int)y,width,height);
        y += speed;

        g.setColor(c);
    }


    public void handleMove(Plane plane) {
        width = CONSTANT.shellwidth;
        height = CONSTANT.shellheight;
        x = plane.x + plane.width/2 - width/2;
        y = plane.y-height;
        speed = 10;

    }

    public void outOfBounds() {
        if(y > CONSTANT.height)
            live = false;
    }
}
