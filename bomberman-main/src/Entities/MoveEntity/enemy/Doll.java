package Entities.MoveEntity.enemy;

import java.awt.Graphics2D;

import Main.GamePanel;
import Image.Image;

public class Doll extends Enemy {
    private int live;
    private int timeDead;

    public Doll(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        speed = 3;
        live = 2;
        timeDead = 60;
        super.setRectangle();
    }

    @Override
    public void update() {
        if (!alive && live >= 1 && timeDead > 0) {
            if (live > 1) {
                live--;
            }
            timeDead--;
            super.move();
            if (timeDead == 0) {
                alive = true;
            }
        } else {
            super.update();
        }
    }

    @Override
    public void setImage(Graphics2D g2) {
        if (live == 1) {
            if (timeDead > 0 && timeDead % 8 < 4) {
                image = null;
                return;
            }
        }

        if (timeToRemove <= 30) {
            super.setImage(g2);
        } else {
            switch (direction) {
                case "up":
                    switch (spriteNum) {
                        case 1:
                            image = Image.doll_right;
                            break;
                        case 2:
                            image = Image.doll_right1;
                            break;
                        case 3:
                            image = Image.doll_right2;
                            break;
                    }
                    break;
                case "down":
                    switch (spriteNum) {
                        case 1:
                            image = Image.doll_left;
                            break;
                        case 2:
                            image = Image.doll_left1;
                            break;
                        case 3:
                            image = Image.doll_left2;
                            break;
                    }
                    break;
                case "left":
                    switch (spriteNum) {
                        case 1:
                            image = Image.doll_left;
                            break;
                        case 2:
                            image = Image.doll_left1;
                            break;
                        case 3:
                            image = Image.doll_left2;
                            break;
                    }
                    break;
                case "right":
                    switch (spriteNum) {
                        case 1:
                            image = Image.doll_right;
                            break;
                        case 2:
                            image = Image.doll_right1;
                            break;
                        case 3:
                            image = Image.doll_right2;
                            break;
                    }
                    break;
                case "dead":
                    image = Image.doll_dead;
                    break;
            }
        }
    }
}