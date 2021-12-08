package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public abstract class Entity {
    protected String name;
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
    public abstract void setImage(Graphics2D g2);

    public  void draw(Graphics2D g2) {
        setImage(g2);
        int scrX = screenX - gamePanel.tileManager.player.getScreenX() + gamePanel.tileManager.player.px;
        int scrY = screenY - gamePanel.tileManager.player.getScreenY() + gamePanel.tileManager.player.py;
                //px->srcX
        //py->scrY
        //ScreenX->worldX
        //ScreenY->worldY

        //stop the camera at the edge
        if (gamePanel.tileManager.player.px > gamePanel.tileManager.player.getScreenX()) {
            scrX = screenX;
        }
        if (gamePanel.tileManager.player.py > gamePanel.tileManager.player.getScreenY()) {
            scrY = screenY;
        }
        int rightOffset = gamePanel.screenWidth - gamePanel.tileManager.player.px;
        if (rightOffset > gamePanel.worldWidth - gamePanel.tileManager.player.getScreenX()) {
            scrX = gamePanel.screenWidth - (gamePanel.worldWidth - screenX);
        }
        int bottomOffset = gamePanel.screenHeight - gamePanel.tileManager.player.py;
        if (bottomOffset > gamePanel.worldHeight - gamePanel.tileManager.player.getScreenY()) {
            scrY = gamePanel.screenHeight - (gamePanel.worldHeight - screenY);
        }
        g2.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

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

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public String getName() {
        return name;
    }
}
