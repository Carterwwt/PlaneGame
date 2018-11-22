package com.carterwang.GameClient;

import com.carterwang.CONSTANT.CONSTANT;
import com.carterwang.GameObject.Plane;
import com.carterwang.GamePanel.GamePanel;
import com.carterwang.GamePanel.StartPanel;

import javax.swing.*;

public class GameClient extends JFrame {

    public static boolean gameStart = false;


    public static void main(String[] args) {

        GameClient gameClient = new GameClient();

        //StartPanel startPanel = new StartPanel();
        GamePanel gamePanel = new GamePanel();

        //gameClient.add(startPanel);
        gameClient.add(gamePanel);
        gameClient.setTitle("Created by CarterWang && Zexin Wu && Jintao Zhou");
        gameClient.setSize(CONSTANT.width,CONSTANT.height);
        gameClient.setLocation(300,25);
        gameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameClient.setVisible(true);
        gameClient.setAlwaysOnTop(true);

/*
        while(!gameStart) {
            startPanel.repaint();
        }
*/
        //startPanel.setVisible(false);
        gamePanel.gameStart();

    }


}
