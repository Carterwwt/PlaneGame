package com.carterwang.GamePanel;

import com.carterwang.CONSTANT.CONSTANT;
import com.carterwang.GameClient.GameClient;
import sun.security.pkcs11.wrapper.CK_NOTIFY;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.System.exit;

public class StartPanel extends JPanel {

    public StartPanel() {
        setLayout(null);
        this.setBackground(Color.gray);
    }


    private static ImageIcon backgroundImage;
    private static ImageIcon exitImage;
    private JLabel playLabel;
    private JLabel exitLabel;
    private JLabel backgroundLabel;
    private JLabel textLabel;

    static {
        try {
            backgroundImage = new ImageIcon("StartPanel/background2.png");
            exitImage = new ImageIcon("StartPanel/EXIT.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw();
    }

    private void draw() {
        drawText();
        drawLabel();
        repaint();
    }

    public void drawLabel() {
        exitLabel = new JLabel(exitImage);
        int exitX = CONSTANT.width/2 - exitImage.getIconWidth()/2 + 5;
        int exitY = CONSTANT.height/2 + exitImage.getIconHeight()/2;
        exitLabel.setBounds(exitX,exitY,exitImage.getIconWidth(),exitImage.getIconHeight());
        exitLabel.setVisible(true);
        exitLabel.setOpaque(false);
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GameClient.gameStart = !GameClient.gameStart;
            }
        });
        add(exitLabel,0);

        playLabel = new JLabel();
    }

    public void drawText() {
        textLabel = new JLabel("Plane War");
        textLabel.setVisible(true);
        textLabel.setBounds(CONSTANT.width/2 - 140,100,600,150);
        textLabel.setFont(new Font("Courier", Font.ITALIC,50));
        textLabel.setForeground(Color.WHITE);
        add(textLabel,0);
    }
}
