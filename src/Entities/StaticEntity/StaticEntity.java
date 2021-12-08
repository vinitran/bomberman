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
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }
}