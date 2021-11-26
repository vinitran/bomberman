package Entities;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Balloom extends Entity {
    GamePanel gamePanel;
    int lengthCanMove;
    //PositionX and positionY are the first position of object
    int positionX;
    int positionY;
    public Balloom(GamePanel gp, int positionX, int positionY, String movement) {
        this.gamePanel = gp;
        this.positionX = positionX;
        this.positionY = positionY;
        this.screenX = positionX;
        this.screenY = positionY;
        solidArea = new Rectangle(3 * gamePanel.scale, 6 * gamePanel.scale, 6 * gamePanel.scale, 9 * gamePanel.scale);
        setDefaultValues(movement);
        getBalloomImage();
    }
    public void setDefaultValues(String movement) {
        speed = 2;
        direction = movement;
        lengthCanMove = 3 * gamePanel.tileSize;
    }
    public void getBalloomImage() {
        try {
            right = read(Objects.requireNonNull(getClass().getResourceAsStream("balloom/balloom_right.png")));
            right1 = read(Objects.requireNonNull(getClass().getResourceAsStream("balloom/balloom_right_1.png")));
            right2 = read(Objects.requireNonNull(getClass().getResourceAsStream("balloom/balloom_right_2.png")));
            left = read(Objects.requireNonNull(getClass().getResourceAsStream("balloom/balloom_left.png")));
            left1 = read(Objects.requireNonNull(getClass().getResourceAsStream("balloom/balloom_left_1.png")));
            left2 = read(Objects.requireNonNull(getClass().getResourceAsStream("balloom/balloom_left_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        switch(direction) {
            case "left":
                screenX -= speed;
                break;
            case "right":
                screenX += speed;
                break;
            case "up":
                screenY -= speed;
                break;
            case "down":
                screenY += speed;
                break;
        }
        if (screenX == positionX - lengthCanMove) {
            direction = "right";
        }
        if (screenX == positionX + lengthCanMove) {
            direction = "left";
        }
        if (screenY == positionY - lengthCanMove) {
            direction = "down";
        }
        if (screenY == positionY + lengthCanMove) {
            direction = "up";
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
        if (Objects.equals(direction, "left") || Objects.equals(direction, "up")) {
            image = switch (spriteNum) {
                case 1 -> left;
                case 2 -> left1;
                case 3 -> left2;
                default -> image;
            };
        }
        if (Objects.equals(direction, "right") || Objects.equals(direction, "down")) {
            image = switch (spriteNum) {
                case 1 -> right;
                case 2 -> right1;
                case 3 -> right2;
                default -> image;
            };
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
