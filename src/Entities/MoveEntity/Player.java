package Entities.MoveEntity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import Image.Image;

public class Player extends MoveEntity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gamePanel = gp;
        this.keyHandler = keyH;
        this.screenX = gamePanel.tileSize;
        this.screenY = gamePanel.tileSize;
        solidArea = new Rectangle(3 * gamePanel.scale, 6 * gamePanel.scale, 6 * gamePanel.scale, 9 * gamePanel.scale);
        setDefaultValues();
    }
    public void setDefaultValues() {
        // worldX = 0;
        // worldY = 0;
        speed = 2;
        direction = "stand";

    }

    @Override
    public void update() {
        if(keyHandler.upPressed) {
            direction = "up";
        }
        else if(keyHandler.downPressed) {
            direction = "down";
        }
        else if(keyHandler.leftPressed) {
            direction = "left";
        }
        else if(keyHandler.rightPressed) {
            direction = "right";
        }
        else {
            direction = "stand";
        }
        //Check Tile Manager
        collisionOn = false;
        gamePanel.cChecker.checkTile(this);
        //If collision is false, player can move
        if (!collisionOn) {
            switch(direction) {
                case "up":
                    screenY -= speed;
                    break;
                case "down":
                    screenY += speed;
                    break;
                case "left":
                    screenX -= speed;
                    break;
                case "right":
                    screenX += speed;
                    break;
            }
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
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                switch (spriteNum) {
                    case 1:
                        image = Image.player_up;
                        break;
                    case 2:
                        image = Image.player_up1;
                        break;
                    case 3:
                        image = Image.player_up2;
                        break;
                }
                break;
            case "down":
                switch (spriteNum) {
                    case 1:
                        image = Image.player_down;
                        break;
                    case 2:
                        image = Image.player_down1;
                        break;
                    case 3:
                        image = Image.player_down2;
                        break;
                };
                break;
            case "left":
                switch (spriteNum) {
                    case 1:
                        image = Image.player_left;
                        break;
                    case 2:
                        image = Image.player_left1;
                        break;
                    case 3: 
                        image = Image.player_left2;
                        break;
                };
                break;
            case "right":
                switch (spriteNum) {
                    case 1:
                        image = Image.player_right;
                        break;
                    case 2:
                        image = Image.player_right1;
                        break;
                    case 3:
                        image = Image.player_right2;
                        break;
                };
                break;
            case "stand":
                switch (spriteNum) {
                    case 1:
                        image = Image.player_stand;
                        break;
                    case 2:
                        image = Image.player_stand1;
                        break;
                    case 3: 
                        image = Image.player_stand2;
                        break;
                };
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
