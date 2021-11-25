package Entities;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gamePanel = gp;
        this.keyHandler = keyH;
        this.screenX = gamePanel.tileSize;
        this.screenY = gamePanel.tileSize;
        solidArea = new Rectangle(3 * gamePanel.scale, 6 * gamePanel.scale, 6 * gamePanel.scale, 9 * gamePanel.scale);
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = 0;
        worldY = 0;
        speed = 2;
        direction = "stand";

    }
    public void getPlayerImage() {
        try {
            up = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_up.png")));
            up1 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_up_1.png")));
            up2 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_up_2.png")));
            down = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_down.png")));
            down1 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_down_1.png")));
            down2 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_down_2.png")));
            right = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_right.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_right_1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_right_2.png")));
            left = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_left.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_left_1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_left_2.png")));
            stand = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_stand.png")));
            stand1 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_stand_1.png")));
            stand2 = read(Objects.requireNonNull(getClass().getResourceAsStream("images/player_stand_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                switch (spriteNum) {
                    case 1 -> image = up;
                    case 2 -> image = up1;
                    case 3 -> image = up2;
                }
                break;
            case "down":
                image = switch (spriteNum) {
                    case 1 -> down;
                    case 2 -> down1;
                    case 3 -> down2;
                    default -> image;
                };
                break;
            case "left":
                image = switch (spriteNum) {
                    case 1 -> left;
                    case 2 -> left1;
                    case 3 -> left2;
                    default -> image;
                };
                break;
            case "right":
                image = switch (spriteNum) {
                    case 1 -> right;
                    case 2 -> right1;
                    case 3 -> right2;
                    default -> image;
                };
                break;
            case "stand":
                image = switch (spriteNum) {
                    case 1 -> stand;
                    case 2 -> stand1;
                    case 3 -> stand2;
                    default -> image;
                };
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
