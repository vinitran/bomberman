package Entities.StaticEntity;

import java.awt.*;

import Entities.Entity;
import Main.GamePanel;

public abstract class StaticEntity extends Entity {
    public StaticEntity(int x, int y, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.screenX = x * gamePanel.tileSize;
        this.screenY = y * gamePanel.tileSize; 
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);
}