package Entities.MoveEntity.enemy;

import Entities.MoveEntity.MoveEntity;
import Entities.StaticEntity.Bomb;
import Entities.StaticEntity.StaticEntity;
import Main.CollisionChecker;
import Main.GamePanel;
import Image.Image;

import java.awt.*;

import static Image.Image.bomb;

public class Kondoria extends Enemy {
    private int count;

    public Kondoria(int x, int y, GamePanel gamePanel, int count) {
        super(x, y, gamePanel);
        speed = 2;
        super.setRectangle();
        this.count = count;
    }

    @Override
    public void update() {
        CollisionBomb = false;
        super.update();
        if (!alive && timeToRemove == 0) {
            if (count > 0) {
                int x = (screenX + solidArea.x + solidArea.width / 2) / gamePanel.tileSize;
                int y = (screenY + solidArea.y + solidArea.height / 2) / gamePanel.tileSize;
                MoveEntity moveEntity = new Kondoria(x, y, gamePanel, 0);
                gamePanel.boardManager.addMoveEntities(moveEntity);
                moveEntity = new Kondoria(x, y, gamePanel, 0);
                gamePanel.boardManager.addMoveEntities(moveEntity);
                count--;
            }
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
                            image = Image.kondoria_right1;
                            break;
                        case 2:
                            image = Image.kondoria_right2;
                            break;
                        case 3:
                            image = Image.kondoria_right3;
                            break;
                    }
                    break;
                case "down":
                    switch (spriteNum) {
                        case 1:
                            image = Image.kondoria_left1;
                            break;
                        case 2:
                            image = Image.kondoria_left2;
                            break;
                        case 3:
                            image = Image.kondoria_left3;
                            break;
                    }
                    break;
                case "left":
                    switch (spriteNum) {
                        case 1:
                            image = Image.kondoria_left1;
                            break;
                        case 2:
                            image = Image.kondoria_left2;
                            break;
                        case 3:
                            image = Image.kondoria_left3;
                            break;
                    }
                    break;
                case "right":
                    switch (spriteNum) {
                        case 1:
                            image = Image.kondoria_right1;
                            break;
                        case 2:
                            image = Image.kondoria_right2;
                            break;
                        case 3:
                            image = Image.kondoria_right3;
                            break;
                    }
                    break;
                case "dead":
                    image = Image.kondoria_dead;
                    break;
            }
        }
    }
}
