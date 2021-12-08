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
        if (!alive) {
            if (!direction.equals("dead")) {
                direction = "dead";
                spriteCounter = 0;
                spriteNum = 1;
                timeToRemove = 40;
            }
            timeToRemove--;
        } else {
            move();
        }
        if (timeToRemove == 0) {
            removed = true;
        }
    }

    public void move() {
        if (!AI1.BasicAI(this, gamePanel) || CollisionBomb) {
            if (CollisionChecker.checkTile(this, gamePanel, 0) || CollisionBomb) {
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

        if (!CollisionChecker.checkTile(this, gamePanel, 0)) {
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
    }
}