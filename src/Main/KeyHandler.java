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
                gamePanel.menu.direction = "StartGame";
            }
            // } else if (gamePanel.gameState == gamePanel.pauseState) {
            // gamePanel.gameState = gamePanel.playState;
            // }
        }
        if (code == KeyEvent.VK_F) {
            if (gamePanel.flash > 0 && !CollisionChecker.checkTile(gamePanel.boardManager.player, gamePanel, 2)) {
                gamePanel.flash--;
                switch (gamePanel.boardManager.player.getDirection()) {
                    case "right":
                        this.gamePanel.boardManager.player
                                .setScreenX(this.gamePanel.boardManager.player.getScreenX() + gamePanel.tileSize * 2);
                        break;
                    case "left":
                        this.gamePanel.boardManager.player
                                .setScreenX(this.gamePanel.boardManager.player.getScreenX() - gamePanel.tileSize * 2);
                        break;
                    case "up":
                        this.gamePanel.boardManager.player
                                .setScreenY(this.gamePanel.boardManager.player.getScreenY() - gamePanel.tileSize * 2);
                        break;
                    case "down":
                        this.gamePanel.boardManager.player
                                .setScreenY(this.gamePanel.boardManager.player.getScreenY() + gamePanel.tileSize * 2);
                        break;
                    default:
                        gamePanel.flash++;
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
