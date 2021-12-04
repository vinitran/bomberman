package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, flash;
    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gamePanel.gameState == gamePanel.playState) {
                gamePanel.gameState = gamePanel.pauseState;
            } else if (gamePanel.gameState == gamePanel.pauseState) {
                gamePanel.gameState = gamePanel.playState;
            }
        }
        if (code == KeyEvent.VK_F) {
            if (gamePanel.tileManager.player.flash) {
                switch (gamePanel.tileManager.player.getDirection()) {
                    case "right":
                        this.gamePanel.tileManager.player
                                .setScreenX(this.gamePanel.tileManager.player.getScreenX() + gamePanel.tileSize * 2);
                        gamePanel.tileManager.player.flash = false;
                        break;
                    case "left":
                        this.gamePanel.tileManager.player
                                .setScreenX(this.gamePanel.tileManager.player.getScreenX() - gamePanel.tileSize * 2);
                        gamePanel.tileManager.player.flash = false;
                        break;
                    case "up":
                        this.gamePanel.tileManager.player
                                .setScreenY(this.gamePanel.tileManager.player.getScreenY() - gamePanel.tileSize * 2);
                        gamePanel.tileManager.player.flash = false;
                        break;
                    case "down":
                        this.gamePanel.tileManager.player
                                .setScreenY(this.gamePanel.tileManager.player.getScreenY() + gamePanel.tileSize * 2);
                        gamePanel.tileManager.player.flash = false;
                        break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
}
