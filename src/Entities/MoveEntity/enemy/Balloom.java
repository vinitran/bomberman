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
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}