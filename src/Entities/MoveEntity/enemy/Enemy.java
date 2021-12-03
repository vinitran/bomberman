package Entities.MoveEntity.enemy;

import Entities.MoveEntity.MoveEntity;
import Entities.MoveEntity.enemy.ai.AI1;
import Main.CollisionChecker;
import Main.GamePanel;

public abstract class Enemy extends MoveEntity {

    public Enemy(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {

        if(CollisionChecker.CheckEntity(gamePanel.tileManager.player,this)) {
            alive = false;
        }
        if (!alive) {
            direction = "dead";
            timeToRemove--;
        } else {
            move();
        }
        if(timeToRemove == 0) {
            removed = true;
        }
    }

    public void move() {
        if (!AI1.BasicAI(this, gamePanel)) {
            if (CollisionChecker.checkTile(this, gamePanel)) {
                switch (direction) {
                    case "up":
                        direction = "down";
                        break;
                    case "down":
                        direction = "up";
                        break;
                    case "left":
                        direction = "right";
                        break;
                    case "right":
                        direction = "left";
                        break;
                }
            }
        }

        if (!CollisionChecker.checkTile(this, gamePanel)) {
            switch (direction) {
                case "up":
                    screenY -= speed;
                    break;
                case "down":
                    screenY += speed;
                    break;
                case "left":
                    screenX -= speed;
                    break;
                case "right":
                    screenX += speed;
                    break;
            }
        }

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
    }
}