package com.carterwang.GamePanel;

import com.carterwang.GamePanel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Explosion {
    double x,y;
    int count =1;

    public void EnemyExplosion(Graphics g) {

        if(count <= 30) {
            try {
                BufferedImage IMG = ImageIO.read(GamePanel.class.getResource("images/explosion/_" + count + ".png"));
                g.drawImage(IMG, (int) x, (int) y, null);
                count++;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
