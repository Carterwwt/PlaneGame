package com.carterwang.CONSTANT;

import com.carterwang.GameClient.GameClient;
import com.carterwang.GamePanel.GamePanel;

public class CONSTANT {

    public static final int width = 700;
    public static final int height = 750;
    public static final int planewidth = GamePanel.getPlaneImage().getWidth(null);
    public static final int planeheight = GamePanel.getPlaneImage().getHeight(null);
    public static final int planex = width/2 - planewidth/2;
    public static final int planey = height - planeheight;
    public static final int shellwidth = 15;
    public static final int shellheight = 15;
    public static final int shellMAX = 30;
    public static final int enemyMAX = 20;
}
