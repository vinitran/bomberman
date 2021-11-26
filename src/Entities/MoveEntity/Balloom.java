package Entities.MoveEntity;

import Images.Image;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Balloom extends MoveEntity {
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
    }
    public void setDefaultValues(String movement) {
        speed = 2;
        direction = movement;
        lengthCanMove = 3 * gamePanel.tileSize;
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
                case 1 -> Image.balloom_left;
                case 2 -> Image.balloom_left_1;
                case 3 -> Image.balloom_left_2;
                default -> image;
            };
        }
        if (Objects.equals(direction, "right") || Objects.equals(direction, "down")) {
            image = switch (spriteNum) {
                case 1 -> Image.balloom_right;
                case 2 -> Image.balloom_right_1;
                case 3 -> Image.balloom_right_2;
                default -> image;
            };
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
