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
    protected BufferedImage image = null;
    protected int spriteNum = 1;
    protected int spriteCounter = 0;
    protected int maxSpriteCounter = 10;
    protected boolean collision = false;
    protected boolean removed = false;
    protected int timeToRemove = 30;

    public Entity(int x, int y, GamePanel gamePanel) {
        this.screenX = x * gamePanel.tileSize;
        this.screenY = y * gamePanel.tileSize; 
        this.gamePanel = gamePanel;
    }
    
    public abstract void update();
    public abstract void setImage(Graphics2D g2);

    public  void draw(Graphics2D g2) {
        setImage(g2);
        int scrX = screenX - gamePanel.boardManager.player.getScreenX() + gamePanel.boardManager.player.px;
        int scrY = screenY - gamePanel.boardManager.player.getScreenY() + gamePanel.boardManager.player.py;
        //px->srcX
        //py->scrY
        //ScreenX->worldX
        //ScreenY->worldY

        //stop the camera at the edge
        if (gamePanel.boardManager.player.px > gamePanel.boardManager.player.getScreenX()) {
            scrX = screenX;
        }
        if (gamePanel.boardManager.player.py > gamePanel.boardManager.player.getScreenY()) {
            scrY = screenY;
        }
        int rightOffset = gamePanel.screenWidth - gamePanel.boardManager.player.px;
        if (rightOffset > gamePanel.worldWidth - gamePanel.boardManager.player.getScreenX()) {
            scrX = gamePanel.screenWidth - (gamePanel.worldWidth - screenX);
        }
        int bottomOffset = gamePanel.screenHeight - gamePanel.boardManager.player.py;
        if (bottomOffset > gamePanel.worldHeight - gamePanel.boardManager.player.getScreenY()) {
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
}
