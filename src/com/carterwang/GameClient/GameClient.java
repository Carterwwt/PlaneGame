package com.carterwang.GameClient;

import com.carterwang.CONSTANT.CONSTANT;
import com.carterwang.GameObject.Plane;
import com.carterwang.GamePanel.GamePanel;

import javax.swing.*;

public class GameClient extends JFrame {


    public static void main(String[] args) {

        GameClient gameClient = new GameClient();

        GamePanel gamePanel = new GamePanel();
        gameClient.add(gamePanel);

        gameClient.setTitle("Created by CarterWang");
        gameClient.setSize(CONSTANT.width,CONSTANT.height);
        gameClient.setLocation(300,25);
        gameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameClient.setVisible(true);
        gameClient.setAlwaysOnTop(true);

        gamePanel.gameStart();

    }


}
