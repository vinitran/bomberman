package Entities.StaticEntity;

import java.awt.Graphics2D;

import Main.GamePanel;
import Image.Image;

public class Brick extends StaticEntity {
    public Brick(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {
        if (removed) {
            direction = "exploded";
        } else {
            direction = "normal";
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (removed) {
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
                    image = Image.brick_exploded;
                    break;
                case 3:
                    image = Image.brick_exploded;
                    break;
            }
        } else {
            image = Image.brick;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
