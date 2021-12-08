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
        int scrX = screenX - gamePanel.BoardManager.player.getScreenX() + gamePanel.BoardManager.player.px;
        int scrY = screenY - gamePanel.BoardManager.player.getScreenY() + gamePanel.BoardManager.player.py;
                //px->srcX
        //py->scrY
        //ScreenX->worldX
        //ScreenY->worldY

        //stop the camera at the edge
        if (gamePanel.BoardManager.player.px > gamePanel.BoardManager.player.getScreenX()) {
            scrX = screenX;
        }
        if (gamePanel.BoardManager.player.py > gamePanel.BoardManager.player.getScreenY()) {
            scrY = screenY;
        }
        int rightOffset = gamePanel.screenWidth - gamePanel.BoardManager.player.px;
        if (rightOffset > gamePanel.worldWidth - gamePanel.BoardManager.player.getScreenX()) {
            scrX = gamePanel.screenWidth - (gamePanel.worldWidth - screenX);
        }
        int bottomOffset = gamePanel.screenHeight - gamePanel.BoardManager.player.py;
        if (bottomOffset > gamePanel.worldHeight - gamePanel.BoardManager.player.getScreenY()) {
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
