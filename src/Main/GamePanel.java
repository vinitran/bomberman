package Main;

import Tile.BoardManager;
import Sound.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16;
    public final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldRow = 13;
    public final int maxWorldCol = 31;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    Thread gameThread;
    private int FPS = 60;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private int level = 0;
    private int levelMax = 1;
    private boolean gameOver = false;
    private boolean winGame = false;
    public BoardManager boardManager;
    public Sound sound;
    public int bombRadius = 1; // bán kính bom
    public int nBombs = 1;
    public int flash = 0;
    public Menu menu;

    public UI ui = new UI(this);

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {
        keyHandler = new KeyHandler(this);
        mouseHandler = new MouseHandler(this);
        boardManager = new BoardManager(this);
        sound = new Sound();
        menu = new Menu(this, mouseHandler);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseHandler);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        nextLevel();
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame() {
        gameState = pauseState;
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
        menu.update();
        if (gameState == playState) {
            boardManager.update();
        }
        if (gameState == pauseState) {
            // nothing happend
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gameState == playState) {
            if(menu.playSound) {
                sound.stageTheme.play();
                sound.stageTheme.loop();
            }
            menu.playSound = false;
            boardManager.draw(g2);
            ui.draw(g2);
        }
        if (gameState == pauseState) {
            menu.draw(g2);
        }
        g2.dispose();
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public void setBombRadius(int bombRadius) {
        this.bombRadius = bombRadius;
    }

    public void nextLevel() {
        level++;
        if (level > levelMax) {
            winGame = true;
            gameState = pauseState;
            return;
        }
        if (level != 1) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boardManager.startGame();
        ui.setNextLevel(true);
        bombRadius = 1;
        nBombs = 1;
        flash = 0;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setWinGame(boolean winGame) {
        this.winGame = winGame;
    }

    public boolean isWinGame() {
        return winGame;
    }
}
