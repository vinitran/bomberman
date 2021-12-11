package Entities.MoveEntity;

import Entities.Entity;
import Main.GamePanel;

public abstract class MoveEntity extends Entity {
    protected int speed;
    protected String direction; 
    protected boolean alive = true;
    protected boolean CollisionBomb = false;
    
    public MoveEntity(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        maxSpriteCounter = 10;
    }

    @Override
    public void update() {
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
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setCollisionBomb(boolean collisionBomb) {
        CollisionBomb = collisionBomb;
    }

    public boolean isCollisionBomb() {
        return CollisionBomb;
    }
}