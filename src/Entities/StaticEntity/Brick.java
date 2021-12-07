package Entities.StaticEntity;

import java.awt.Graphics2D;

import Main.GamePanel;
import Image.Image;

public class Brick extends StaticEntity {
    int x, y;
    public Brick(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if (gamePanel.tileManager.mapTile[y][x] != 2) {
            timeToRemove--;
            if (timeToRemove == 0) {
                removed = true;
            }
        }
    }

    @Override
    public void setImage(Graphics2D g2) {
        if (timeToRemove < 30) {
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
            switch (spriteNum) {
                case 1:
                    image = Image.brick_exploded;
                    break;
                case 2:
                    image = Image.brick_exploded1;
                    break;
                case 3:
                    image = Image.brick_exploded2;
                    break;
            }
        } else {
            image = Image.brick;
        }
        //g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
