package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public abstract class Entity {
    protected int worldX, worldY;
    protected int screenX;
    protected int screenY;
    protected Rectangle solidArea;
    protected GamePanel gamePanel;
    protected String direction; 
    public int solidAreaDefaultX, solidAreaDefaultY;
    protected BufferedImage image = null;
    protected int spriteNum = 1;
    protected int spriteCounter = 0;
    protected boolean collision = false;
    protected boolean removed = false;
    protected int timeToRemove = 30;
    
    public abstract void update();

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }
}
