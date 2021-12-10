package Entities.MoveEntity.enemy;

import java.awt.Graphics2D;

import Entities.MoveEntity.MoveEntity;
import Main.GamePanel;
import java.awt.*;
import Image.Image;

public class Minvo extends Enemy {
    public Minvo(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        speed = 2;
        super.setRectangle();
    }

    @Override
    public void update() {
        super.update();
        if (!alive && timeToRemove == 0) {
            int x = (screenX + solidArea.x + solidArea.width / 2) / gamePanel.tileSize;
            int y = (screenY + solidArea.y + solidArea.height / 2) / gamePanel.tileSize;
            MoveEntity moveEntity = new Balloom(x, y, gamePanel);
            gamePanel.BoardManager.addMoveEntities(moveEntity);
        }
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
                            image = Image.minvo_right;
                            break;
                        case 2:
                            image = Image.minvo_right1;
                            break;
                        case 3:
                            image = Image.minvo_right2;
                            break;
                    }
                    break;
                case "down":
                    switch (spriteNum) {
                        case 1:
                            image = Image.minvo_left;
                            break;
                        case 2:
                            image = Image.minvo_left1;
                            break;
                        case 3:
                            image = Image.minvo_left2;
                            break;
                    }
                    break;
                case "left":
                    switch (spriteNum) {
                        case 1:
                            image = Image.minvo_left;
                            break;
                        case 2:
                            image = Image.minvo_left1;
                            break;
                        case 3:
                            image = Image.minvo_left2;
                            break;
                    }
                    break;
                case "right":
                    switch (spriteNum) {
                        case 1:
                            image = Image.minvo_right;
                            break;
                        case 2:
                            image = Image.minvo_right1;
                            break;
                        case 3:
                            image = Image.minvo_right2;
                            break;
                    }
                    break;
                case "dead":
                    image = Image.minvo_dead;
                    break;
            }
        }
    }
}