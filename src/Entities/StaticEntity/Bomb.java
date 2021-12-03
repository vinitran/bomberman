package Entities.StaticEntity;

import java.awt.Graphics2D;

import Entities.Entity;
import Image.Image;
import Main.GamePanel;

public class Bomb extends StaticEntity {
    private int timeExplode = 120;
    private boolean exploded = false;

    public Bomb(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {

        if (timeExplode > 0) {
            timeExplode--;
        } else {
            if (!exploded) {
                exploded = true;
                spriteCounter = 0;
                spriteNum  = 1;
            }
            if (timeToRemove > 0) {
                timeToRemove--;
            } else {
                removed = true;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
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
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}