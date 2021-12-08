package Entities.StaticEntity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import Entities.MoveEntity.MoveEntity;
import Image.Image;
import Sound.Sound;
import Main.CollisionChecker;
import Main.GamePanel;

public class Bomb extends StaticEntity {
    private int timeExplode = 120;
    private boolean exploded = false;
    List<Flame> flames = new ArrayList<>();

    public Bomb(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {
        collisionMob();
        if (timeExplode > 0) {
            timeExplode--;
        } else {
            if (!exploded) {
                Sound.sound_Bom.play();
                exploded = true;
                spriteCounter = 0;
                spriteNum = 1;
                creatFrame();
            } else {
                updateFrame();
            }
            if (timeToRemove > 0) {
                timeToRemove--;
            } else {
                removed = true;
            }
        }
    }

    private void collisionMob() {
        for (MoveEntity moveEntity : gamePanel.tileManager.MoveEntities) {
            if (CollisionChecker.CheckEntity(moveEntity, this)) {
                if (this.isExploded()) {
                    moveEntity.setAlive(false);
                }
                moveEntity.setCollisionBomb(true);
                break;
            } else {
                moveEntity.setCollisionBomb(false);
            }
        }
    }

    @Override
    public void setImage(Graphics2D g2) {
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if (!exploded) {
            switch (spriteNum) {
                case 1:
                    image = Image.bomb;
                    break;
                case 2:
                    image = Image.bomb1;
                    break;
                case 3:
                    image = Image.bomb2;
                    break;
            }
        } else {
            switch (spriteNum) {
                case 1:
                    image = Image.bomb_exploded;
                    break;
                case 2:
                    image = Image.bomb_exploded1;
                    break;
                case 3:
                    image = Image.bomb_exploded2;
                    break;
            }
            for (Flame flame : flames) {
                flame.draw(g2);
            }
        }
        //g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void creatFrame() {
        int xt, yt, x, y;
        xt = screenX / gamePanel.tileSize;
        yt = screenY / gamePanel.tileSize;
        for (int i = 0; i < 4; i++) {
            x = xt;
            y = yt;
            for (int j = 0; j < gamePanel.bombRadius; j++) {
                switch (i) {
                    case 0:
                        y--;
                        break;
                    case 1:
                        y++;
                        break;
                    case 2:
                        x--;
                        break;
                    case 3:
                        x++;
                        break;
                }
                if (!CollisionChecker.checkFlame(x, y, gamePanel)) {
                    if (j == gamePanel.bombRadius - 1) {
                        flames.add(new Flame(x, y, gamePanel, i, true));
                    } else {
                        flames.add(new Flame(x, y, gamePanel, i, false));
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void updateFrame() {
        for (Flame flame : flames) {
            flame.update();
        }
    }

    public boolean isExploded() {
        return exploded;
    }
}