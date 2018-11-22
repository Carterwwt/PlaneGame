package com.carterwang.GamePanel;

import com.carterwang.CONSTANT.CONSTANT;
import com.carterwang.GameObject.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {

    private static BufferedImage backgroundImage;
    private static BufferedImage planeImage;
    private static BufferedImage enemyImage;
    private static BufferedImage aidImage;
    private static Plane plane;
    private Timer timer;
    private Boss enemyBoss;
    private Enemy specialEnemy;

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Shell> selfShells = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private ArrayList<Aid> aids = new ArrayList<Aid>();

    private static boolean isBoss = false;
    private static boolean isCreated = false;
    private int successTime = 0;

    private int shellWidth = CONSTANT.shellwidth;
    private int shellHeight = CONSTANT.shellheight;

    public GamePanel() {
        //setPlane();
    }

    public static BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public static BufferedImage getPlaneImage() {
        return planeImage;
    }

    public static Plane getPlane() {
        return plane;
    }

    static {
        try {
            backgroundImage = ImageIO.read(GamePanel.class.getResource("images/background2.png"));
            planeImage = ImageIO.read(GamePanel.class.getResource("images/plane.png"));
            enemyImage = ImageIO.read(GamePanel.class.getResource("images/enemy1.png"));
            aidImage = ImageIO.read(GamePanel.class.getResource("images/aid.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, null);
        if(plane.isLive()) {
            hitDetection(g);
            drawPlane(g);
            drawEnemy(g);
            drawAid(g);
            if(enemyBoss != null)
                if(enemyBoss.isLive()) {
                    if(!enemyBoss.outOfBounds())
                        enemyBoss.drawBoss(g, plane);
                    else
                        exitGame(g);
                }
                else
                    showSuccess(g);
        } else {
            exitGame(g);
        }
    }

    private void drawEnemy(Graphics g) {
        for(int i=0;i<enemies.size();i++) {
            Enemy enemy = enemies.get(i);
            if(enemy.isLive())
                enemy.drawEnemy(g, plane);

                for (int j = 0; j < enemy.getEnemyshells().size(); j++) {
                    Shell shell = enemy.getEnemyshells().get(j);
                    if (shell.isLive())
                        shell.drawEnemyShell(g);
                }
        }
    }

    private void drawPlane(Graphics g) {
            plane.drawPlane(g);
            plane.drawINFO(g);
            for (int i = 0; i < selfShells.size(); i++) {
                Shell shell = selfShells.get(i);
                if(shell.isLive())
                    shell.drawSelf(g);
            }
    }

    private void drawAid(Graphics g) {
        for(int i=0;i<aids.size();i++) {
            Aid aid = aids.get(i);
            if(!aid.isPicked())
                aid.draw(g);
        }
    }

    private void hitDetection(Graphics g) {

        selfShellHitEnemy();
        selfPlaneHitEnemy();
        selfPlaneHitAid();
        if(isBoss && enemyBoss.isLive()) {
            selfShellHitBoss();
            selfPlaneHitBossShell();
            selfPlaneHitBoss();
        }
        playExplosion(g);

    }

    private void selfShellHitEnemy() {
        for (Shell shell : selfShells) {
            Rectangle shellRect = shell.getRect();
            for (Enemy enemy : enemies) {
                if (enemy.isLive()&&shell.isLive()) {
                    Rectangle enemyRect = enemy.getRect();
                    if (enemyRect.intersects(shellRect)) {
                        Explosion explosion = new Explosion();
                        enemy.setLive(false);
                        shell.setLive(false);
                        plane.addScore();
                        explosion.x = enemy.getX();
                        explosion.y = enemy.getY();
                        explosions.add(explosion);
                    }
                }
            }
        } // 判断友军炮弹是否打中敌机
    }

    private void selfPlaneHitEnemy() {

        for (int i=0;i<enemies.size();i++) {
            Enemy enemy = enemies.get(i);

            Rectangle planeRect = plane.getRect();

            if (enemy.isLive()) {
                Rectangle enemyRect = enemy.getRect();
                if (enemyRect.intersects(planeRect)) {
                    enemy.setLive(false);
                    plane.addScore();
                    if(enemy.isSpecialEnemy()) {
                        plane.setHP(plane.getHP() - 50);
                    }
                    else
                        plane.setHP(plane.getHP() - 20);
                    if (plane.getHP() <= 0)
                        plane.setLive(false);
                    Explosion explosion = new Explosion();
                    explosion.x = enemy.getX();
                    explosion.y = enemy.getY();
                    explosions.add(explosion);
                }
            } // 判断敌机是否和自身碰撞

            for (int j=0;j<enemy.getEnemyshells().size();j++) {
                Shell shell = enemy.getEnemyshells().get(j);
                if(shell.isLive()) {
                    Rectangle enemyshellRect = shell.getRect();
                    if (enemyshellRect.intersects(planeRect)) {
                        plane.setHP(plane.getHP() - 10);
                        shell.setLive(false);
                        if (plane.getHP() <= 0)
                            plane.setLive(false);
                    }
                }
            }//判断敌机炮弹是否和自身碰撞
        }
    }

    private void selfPlaneHitAid() {
        Rectangle planeRect = plane.getRect();
        if(aids.size() == 1) {
            Aid aid = aids.get(0);
            Rectangle aidRect = aid.getRect();
            if (aidRect.intersects(planeRect)) {
                plane.setHP(plane.getHP() + 50);
                aid.setPicked(true);
                aids.remove(aid);
            }//刷新血包
        }
    }

    private void selfShellHitBoss() {
        Rectangle bossRect = enemyBoss.getRect();
        for (int i = 0; i < selfShells.size(); i++) {
            Shell shell = selfShells.get(i);
            if (shell.isLive()) {
                Rectangle shellRect = shell.getRect();
                if (bossRect.intersects(shellRect)) {
                    enemyBoss.setHp();
                    Explosion explosion = new Explosion();
                    explosion.x = shell.getX();
                    explosion.y = shell.getY();
                    explosions.add(explosion);
                    shell.setLive(false);
                    if (enemyBoss.getHp() <= 0) {
                        enemyBoss.setLive(false);
                    }
                }
            }
        }
    }

    private void selfPlaneHitBoss() {
        Rectangle bossRect = enemyBoss.getRect();
        Rectangle planeRect = plane.getRect();
        if (bossRect.intersects(planeRect))
            plane.setLive(false);
    }

    private void selfPlaneHitBossShell() {
        Rectangle planeRect = plane.getRect();
        ArrayList<bossShell> bossShells = enemyBoss.getBossShells();
        for(int i=0;i<bossShells.size();i++) {
            bossShell bossShell = bossShells.get(i);
            if(bossShell.isLive()) {
                Rectangle bossShellRect = bossShell.getRect();
                if(bossShellRect.intersects(planeRect)) {
                    bossShell.setLive(false);
                    plane.setHP(plane.getHP() - 10);
                    if(plane.getHP() <= 0)
                        plane.setLive(false);
                }
            }
        }
    }

    private void playExplosion(Graphics g) {
        for (Explosion explosion : explosions) {
            explosion.EnemyExplosion(g);
        } // 播放爆炸动画

        if(explosions.size()>100)
            explosions.clear();
    }

    public void gameStart() {

        setPlane();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                enemyFactory();
                enemyShellFactory();
                aidFactory();
                updateGame();
                repaint();
            }
        },10,10);

    }

    private void setPlane() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                plane.addDirection(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE :
                        Shell shell = new Shell();
                        shell.handleMove(plane);
                        selfShells.add(shell);
                        if(selfShells.size() == 15)
                            selfShells.remove(0) ;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                plane.minusDirection(e);
            }
        };

        this.addKeyListener(keyAdapter);
        this.setFocusable(true);

        int x = CONSTANT.width/2 - planeImage.getWidth(null)/2;
        int y = CONSTANT.height - planeImage.getHeight() - 20;
        int width = planeImage.getWidth();
        int height = planeImage.getHeight();
        plane = new Plane(planeImage,x,y,width,height,3);
    }

    private void updateGame() {
        try {
            if (plane.getScore() < 100 && plane.getScore() != 0 && plane.getScore() % 10 == 0) {
                int index = plane.getScore() / 10 + 1;
                enemyImage = ImageIO.read(GamePanel.class.getResource("images/enemy" + index + ".png"));
                if(!isCreated) {
                    shellWidth += 2;
                    shellHeight += 2;
                    createSpecialEnemy();
                }
            } else if (plane.getScore() == 100 && !isBoss) {
                isBoss = true;
                enemyImage = ImageIO.read(GamePanel.class.getResource("images/enemyBoss.png"));
                handleBoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0;i<enemies.size();i++) {
            Enemy enemy = enemies.get(i);
            enemy.outOfBounds();
            if(enemy.isSpecialEnemy() && !enemy.isLive() && plane.getScore() % 10 != 0)
                isCreated = false;
        }
    }

    private void createSpecialEnemy() {
        isCreated = true;
        Enemy enemy = new Enemy(enemyImage,plane.getX() - 30,0,enemyImage.getWidth(),enemyImage.getHeight(),8);
        enemy.setSpecialEnemy(true);
        enemies.add(enemy);
    }

    private void handleBoss() {
        enemyBoss = new Boss(enemyImage,CONSTANT.width/2,0,enemyImage.getWidth(),enemyImage.getHeight(),1);
        enemies.clear();
    }

    private void showSuccess(Graphics g) {
        successTime++;
        if(successTime <= 400) {
            Color c = g.getColor();
            Font f = g.getFont();

            g.setColor(Color.pink);
            Font font = new Font("Courier", Font.BOLD, 30);
            g.setFont(font);
            g.drawString("牛逼啊！！！!", CONSTANT.width / 2 - 72, CONSTANT.height / 2);
            g.drawString("666666666!!!!!!!", CONSTANT.width / 2 - 120, CONSTANT.height / 2 + 30);

            g.setColor(c);
            g.setFont(f);
        }
    }

    private void exitGame(Graphics g) {
        Color c = g.getColor();
        Font f = g.getFont();

        g.setColor(Color.pink);
        Font font = new Font("Courier",Font.BOLD,30);
        g.setFont(font);
        g.drawString("Game Over!",CONSTANT.width/2-72,CONSTANT.height/2);
        g.drawString("Score:" + plane.getScore(),CONSTANT.width/2-72,CONSTANT.height/2+30);

        g.setColor(c);
        g.setFont(f);
    }

    int enemyIndex = 0;
    int enemySpeed = 1;
    int enemyShellIndex = 0;
    int enemyShellSpeed = 2;

    private void enemyFactory() {
        enemyIndex++;
        if(!isBoss) {
            if (enemyIndex % 80 == 0) {
                Enemy enemy = new Enemy(enemyImage, Enemy.randomPosition(), 0, enemyImage.getWidth(), enemyImage.getHeight(), enemySpeed);
                enemies.add(enemy);
            }
        }
    }

    private void enemyShellFactory() {
        enemyShellIndex++;
        if(enemyShellIndex % 100 == 0) {
            for(int i=0;i<enemies.size();i++) {
                Enemy enemy = enemies.get(i);
                if(enemy.isLive()) {
                    Shell shell = new Shell((int) enemy.getX() + enemyImage.getWidth() / 2, (int) enemy.getY() + enemyImage.getHeight(), shellWidth, shellHeight, enemyShellSpeed);
                    enemy.getEnemyshells().add(shell);
                }
            }
        }
    }

    private void aidFactory() {
        if(plane.getHP()<=50 && aids.size() == 0) {
            Aid aid = new Aid(aidImage);
            aids.add(aid);
        }
    }

}
