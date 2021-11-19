package Entities;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.*;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gamePanel = gp;
        this.keyHandler = keyH;
        screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2;
        screenY = gamePanel.screenHeight/2 - gamePanel.tileSize/2;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gamePanel.tileSize;
        worldY = gamePanel.tileSize;
        speed = 4;
        direction = "stand";
    }
    public void getPlayerImage() {
        try {
            up = read(getClass().getResourceAsStream("images/player_up.png"));
            up1 = read(getClass().getResourceAsStream("images/player_up_1.png"));
            up2 = read(getClass().getResourceAsStream("images/player_up_2.png"));
            down = read(getClass().getResourceAsStream("images/player_down.png"));
            down1 = read(getClass().getResourceAsStream("images/player_down_1.png"));
            down2 = read(getClass().getResourceAsStream("images/player_down_2.png"));
            right = read(getClass().getResourceAsStream("images/player_right.png"));
            right1 = read(getClass().getResourceAsStream("images/player_right_1.png"));
            right2 = read(getClass().getResourceAsStream("images/player_right_2.png"));
            left = read(getClass().getResourceAsStream("images/player_left.png"));
            left1 = read(getClass().getResourceAsStream("images/player_left_1.png"));
            left2 = read(getClass().getResourceAsStream("images/player_left_2.png"));
            stand = read(getClass().getResourceAsStream("images/player_stand.png"));
            stand1 = read(getClass().getResourceAsStream("images/player_stand_1.png"));
            stand2 = read(getClass().getResourceAsStream("images/player_stand_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyHandler.upPressed) {
            direction = "up";
            worldY -= speed;
        }
        else if(keyHandler.downPressed) {
            direction = "down";
            worldY += speed;
        }
        else if(keyHandler.leftPressed) {
            direction = "left";
            worldX -= speed;
        }
        else if(keyHandler.rightPressed) {
            direction = "right";
            worldX += speed;
        }
        else {
            direction = "stand";
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 3;
            }
            else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up;
                }
                else if (spriteNum == 2) {
                    image = up1;
                }
                else if (spriteNum == 3) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down;
                }
                else if (spriteNum == 2) {
                    image = down1;
                }
                else if (spriteNum == 3) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left;
                }
                else if (spriteNum == 2) {
                    image = left1;
                }
                else if (spriteNum == 3) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right;
                }
                else if (spriteNum == 2) {
                    image = right1;
                }
                else if (spriteNum == 3) {
                    image = right2;
                }
                break;
            case "stand":
                if (spriteNum == 1) {
                    image = stand;
                }
                else if (spriteNum == 2) {
                    image = stand1;
                }
                else if (spriteNum == 3) {
                    image = stand2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
