package Entities.MoveEntity.enemy;

import java.awt.Graphics2D;

import Main.GamePanel;
import java.awt.*;
import Image.Image;

public class Oneal extends Enemy {

    public Oneal(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        speed = 2;
        super.setRectangle();
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
                            image = Image.oneal_right;
                            break;
                        case 2:
                            image = Image.oneal_right1;
                            break;
                        case 3:
                            image = Image.oneal_right2;
                            break;
                    }
                    break;
                case "down":
                    switch (spriteNum) {
                        case 1:
                            image = Image.oneal_left;
                            break;
                        case 2:
                            image = Image.oneal_left1;
                            break;
                        case 3:
                            image = Image.oneal_left2;
                            break;
                    }
                    break;
                case "left":
                    switch (spriteNum) {
                        case 1:
                            image = Image.oneal_left;
                            break;
                        case 2:
                            image = Image.oneal_left1;
                            break;
                        case 3:
                            image = Image.oneal_left2;
                            break;
                    }
                    break;
                case "right":
                    switch (spriteNum) {
                        case 1:
                            image = Image.oneal_right;
                            break;
                        case 2:
                            image = Image.oneal_right1;
                            break;
                        case 3:
                            image = Image.oneal_right2;
                            break;
                    }
                    break;
                case "dead":
                    image = Image.oneal_dead;
                    break;
            }
        }
    }
}