package Entities.MoveEntity;

import java.awt.*;

import Entities.Entity;

public abstract class MoveEntity extends Entity {
    protected int speed;
    protected int spriteCounter = 0;
    protected String direction; 
    protected boolean collisionOn = false;
    
    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);
}