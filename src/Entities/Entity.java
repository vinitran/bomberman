package Entities;

import java.awt.*;

public abstract class Entity {
    public int worldX, worldY;
    public int screenX;
    public int screenY;
    public int spriteNum = 1;
    public Rectangle solidArea;
    
    public abstract void update();
}
