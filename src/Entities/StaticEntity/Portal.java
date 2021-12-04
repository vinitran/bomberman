package Entities.StaticEntity;

import java.awt.Graphics2D;
import Image.Image;
import Main.GamePanel;


public class Portal extends StaticEntity {
    public Portal(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {        
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(Image.portal, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}