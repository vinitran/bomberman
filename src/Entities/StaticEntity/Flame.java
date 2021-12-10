package Entities.StaticEntity;

import java.awt.Graphics2D;

import Entities.MoveEntity.MoveEntity;
import Image.Image;
import Main.CollisionChecker;
import Main.GamePanel;

public class Flame extends StaticEntity {
    private int direction;
    private boolean checkLast; // xem lửa ở giữa hay cuối để nạp ảnh

    public Flame(int x, int y, GamePanel gamePanel, int direction, boolean checkLast) {
        super(x, y, gamePanel);
        this.direction = direction;
        this.checkLast = checkLast;
    }

    @Override
    public void update() {
        for (MoveEntity moveEntity : gamePanel.BoardManager.moveEntities) {
            if (CollisionChecker.CheckEntity(moveEntity, this)) {
                moveEntity.setAlive(false);
            }
        }
    }

    @Override
    public void setImage(Graphics2D g2) {
        image = null;
        spriteCounter++;
        if (spriteCounter > maxSpriteCounter) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if (checkLast) {
            switch (direction) {
                case 0:
                    switch (spriteNum) {
                        case 1:
                            image = Image.explosion_top;
                            break;
                        case 2:
                            image = Image.explosion_top1;
                            break;
                        case 3:
                            image = Image.explosion_top2;
                            break;
                    }
                    break;
                case 1:
                    switch (spriteNum) {
                        case 1:
                            image = Image.explosion_down;
                            break;
                        case 2:
                            image = Image.explosion_down1;
                            break;
                        case 3:
                            image = Image.explosion_down2;
                            break;
                    }
                    break;
                case 2:
                    switch (spriteNum) {
                        case 1:
                            image = Image.explosion_left;
                            break;
                        case 2:
                            image = Image.explosion_left1;
                            break;
                        case 3:
                            image = Image.explosion_left2;
                            break;
                    }
                    break;
                case 3:
                    switch (spriteNum) {
                        case 1:
                            image = Image.explosion_right;
                            break;
                        case 2:
                            image = Image.explosion_right1;
                            break;
                        case 3:
                            image = Image.explosion_right2;
                            break;
                    }
                    break;
            }
        } else {
            if (direction <= 1) {
                switch (spriteNum) {
                    case 1:
                        image = Image.explosion_vertical;
                        break;
                    case 2:
                        image = Image.explosion_vertical1;
                        break;
                    case 3:
                        image = Image.explosion_vertical2;
                        break;
                }
            } else {
                switch (spriteNum) {
                    case 1:
                        image = Image.explosion_horizontal;
                        break;
                    case 2:
                        image = Image.explosion_horizontal1;
                        break;
                    case 3:
                        image = Image.explosion_horizontal2;
                        break;
                }
            }
        }
    }
}