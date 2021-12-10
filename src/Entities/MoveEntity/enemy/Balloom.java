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
    }

    @Override
    public void setImage(Graphics2D g2) {
        image = null;
        if (timeToRemove <= 30) {
            super.setImage(g2);
        } else {
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
                    image = Image.balloom_dead;
                    break;
            }
        }
    }
}