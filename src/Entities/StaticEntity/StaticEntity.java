package Entities.StaticEntity;

import java.awt.*;
import java.awt.Graphics2D;

import Entities.Entity;
import Main.GamePanel;

public abstract class StaticEntity extends Entity {
    public StaticEntity(int x, int y, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.screenX = x * gamePanel.tileSize;
        this.screenY = y * gamePanel.tileSize; 
        solidArea = new Rectangle(0 * gamePanel.scale, 0 * gamePanel.scale, 16 * gamePanel.scale, 16 * gamePanel.scale);
    }

    public abstract void update();
    public abstract void setImage(Graphics2D g2);

    @Override
    public void draw(Graphics2D g2) {
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
        if (gamePanel.tileManager.player.px  > gamePanel.tileManager.player.getScreenX() || gamePanel.tileManager.player.py > gamePanel.tileManager.player.getScreenY() || rightOffset > gamePanel.worldWidth - gamePanel.tileManager.player.getScreenX() || bottomOffset > gamePanel.worldHeight - gamePanel.tileManager.player.getScreenY()) {
            g2.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}