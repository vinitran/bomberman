package Entities.MoveEntity;

import java.awt.*;

import Entities.Entity;
import Main.GamePanel;

public abstract class MoveEntity extends Entity {
    protected int speed;
    protected String direction; 
    protected boolean alive = true;
    protected boolean CollisionBomb = false;
    
    public MoveEntity(int x, int y, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.screenX = x * gamePanel.tileSize;
        this.screenY = y * gamePanel.tileSize;
    }
    
    public abstract void update();
    public abstract void draw(Graphics2D g2);

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