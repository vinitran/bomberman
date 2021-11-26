package Entities;

import java.awt.*;

public abstract class Entity {
    protected int worldX, worldY;
    protected int screenX;
    protected int screenY;
    protected int spriteNum = 1;
    protected Rectangle solidArea;
    
    public abstract void update();

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }
}
