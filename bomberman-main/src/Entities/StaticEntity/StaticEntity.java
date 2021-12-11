package Entities.StaticEntity;

import java.awt.*;

import Entities.Entity;
import Main.GamePanel;

public abstract class StaticEntity extends Entity {
    public StaticEntity(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        solidArea = new Rectangle(0, 0, 16 * gamePanel.scale, 16 * gamePanel.scale);
        timeToRemove = 30;
        maxSpriteCounter = 10;
    }
}