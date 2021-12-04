package Main;

import Sound.Sound;
import Tile.TileManager;

import Item.item;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16;
    public final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 31;
    public final int maxWorldRow = 13;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    Thread gameThread;
    private int FPS = 60;
    private KeyHandler keyHandler;
    public TileManager tileManager;
    private int bombRadius; // bán kính bom

    public item item[] = new item[10];
    public UI ui = new UI(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public GamePanel() {
        keyHandler = new KeyHandler(this);
        tileManager = new TileManager(this, keyHandler);
        this.setPreferredSize(new Dimension(worldWidth, worldHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        Sound.sound_Bomberman.play();
        Sound.sound_loop.play();
        Sound.sound_loop.loop();
    }

    public void setUpGame() {
        assetSetter.setObject();
        gameState = playState;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();

            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            tileManager.update();
        }
        if (gameState == pauseState) {
            //nothing happend
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public void setBombRadius(int bombRadius) {
        this.bombRadius = bombRadius;
    }
}
