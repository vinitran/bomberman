package Entities.StaticEntity;

import java.awt.Graphics2D;
import Image.Image;
import Main.GamePanel;

public class Wall extends StaticEntity {
    public Wall(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {        
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(Image.wall, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}