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
        int scrX = worldX - gamePanel.tileManager.player.getScreenX() + gamePanel.tileManager.player.px;
        int scrY = worldY - gamePanel.tileManager.player.getScreenY() + gamePanel.tileManager.player.py;
                //px->srcX
        //py->scrY
        //ScreenX->worldX
        //ScreenY->worldY

        //stop the camera at the edge
        if (gamePanel.tileManager.player.px > gamePanel.tileManager.player.getScreenX()) {
            scrX = worldX;
        }
        if (gamePanel.tileManager.player.py > gamePanel.tileManager.player.getScreenY()) {
            scrY = worldY;
        }
        int rightOffset = gamePanel.screenWidth - gamePanel.tileManager.player.px;
        if (rightOffset > gamePanel.worldWidth - gamePanel.tileManager.player.getScreenX()) {
            scrX = gamePanel.screenWidth - (gamePanel.worldWidth - worldX);
        }
        int bottomOffset = gamePanel.screenHeight - gamePanel.tileManager.player.py;
        if (bottomOffset > gamePanel.worldHeight - gamePanel.tileManager.player.getScreenY()) {
            scrY = gamePanel.screenHeight - (gamePanel.worldHeight - worldY);
        }

        g2.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
        if (gamePanel.tileManager.player.px  > gamePanel.tileManager.player.getScreenX() || gamePanel.tileManager.player.py > gamePanel.tileManager.player.getScreenY() || rightOffset > gamePanel.worldWidth - gamePanel.tileManager.player.getScreenX() || bottomOffset > gamePanel.worldHeight - gamePanel.tileManager.player.getScreenY()) {
            g2.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

}
