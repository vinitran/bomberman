package Entities.MoveEntity.enemy;

import java.awt.Graphics2D;

import Main.GamePanel;
import java.awt.*;
import Image.Image;

public class Balloom extends Enemy {
    public Balloom(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        speed = 1;
        solidArea = new Rectangle(speed, speed, 16 * gamePanel.scale - speed, 16 * gamePanel.scale - speed);
        direction = "right";
        collision = true;
    }

    @Override
    public void draw(Graphics2D g2) {
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        image = null;
        switch (direction) {
            case "up":
                switch (spriteNum) {
                    case 1:
                        image = Image.balloom_right;
                        break;
                    case 2:
                        image = Image.balloom_right1;
                        break;
                    case 3:
                        image = Image.balloom_right2;
                        break;
                }
                break;
            case "down":
                switch (spriteNum) {
                    case 1:
                        image = Image.balloom_left;
                        break;
                    case 2:
                        image = Image.balloom_left1;
                        break;
                    case 3:
                        image = Image.balloom_left2;
                        break;
                }
                break;
            case "left":
                switch (spriteNum) {
                    case 1:
                        image = Image.balloom_left;
                        break;
                    case 2:
                        image = Image.balloom_left1;
                        break;
                    case 3:
                        image = Image.balloom_left2;
                        break;
                }
                break;
            case "right":
                switch (spriteNum) {
                    case 1:
                        image = Image.balloom_right;
                        break;
                    case 2:
                        image = Image.balloom_right1;
                        break;
                    case 3:
                        image = Image.balloom_right2;
                        break;
                }
                break;
            case "dead":
                switch (spriteNum) {
                    case 1:
                        image = Image.balloom_dead;
                        break;
                    case 2:
                        image = Image.mob_dead1;
                        break;
                    case 3: {
                        if (spriteCounter == 9) {
                            image = Image.mob_dead3;
                            spriteCounter = 8;
                        } else {
                        image = Image.mob_dead2;
                        }
                    }
                        break;
                }
        }
            int x = screenX;
            int y = screenY;
            if (gamePanel.tileManager.player.px > screenX) {
                x = screenX;
            }
            if (gamePanel.tileManager.player.py > screenY) {
                y = screenY;
            }
            int rightOffset = gamePanel.screenWidth - gamePanel.tileManager.player.px;
            if (rightOffset > gamePanel.worldWidth - getScreenX()) {
                x = gamePanel.screenWidth - (gamePanel.worldWidth - screenX);
            }
            int bottomOffset = gamePanel.screenHeight - gamePanel.tileManager.player.py;
            if (bottomOffset > gamePanel.worldHeight - getScreenY()) {
                y = gamePanel.screenHeight - (gamePanel.worldHeight - screenY);
            }

            g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}