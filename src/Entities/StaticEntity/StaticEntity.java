package Entities.StaticEntity;

import java.awt.*;

import Entities.Entity;
import Main.GamePanel;

public abstract class StaticEntity extends Entity {
    public StaticEntity(int x, int y, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.screenX = x * gamePanel.tileSize;
        this.screenY = y * gamePanel.tileSize;
        solidArea = new Rectangle(0,0, 32, 32);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);
}