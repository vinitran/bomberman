package Item;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class item {
    public String name;
    public BufferedImage image;
    public int worldX, worldY;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        for (int maxCol = 0; maxCol<gamePanel.maxWorldCol; maxCol++) {
            for (int maxRow = 0; maxRow<gamePanel.maxWorldRow; maxRow++) {
                int screenX = maxCol * gamePanel.tileSize;
                int screenY = maxRow * gamePanel.tileSize;
                g2.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
    }

}
