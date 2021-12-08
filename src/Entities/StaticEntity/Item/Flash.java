package Entities.StaticEntity.Item;

import Entities.StaticEntity.StaticEntity;
import Main.GamePanel;
import Image.Image;

import java.awt.*;

public class Flash extends StaticEntity {
    public Flash(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        this.name = "Flash";
    }

    @Override
    public void update() {

    }

    public void setImage(Graphics2D g2) {
        this.image = Image.Flash;
    }

    @Override
    public void draw(Graphics2D g2) {
        setImage(g2);
        int scrX = screenX - gamePanel.tileManager.player.getScreenX() + gamePanel.tileManager.player.px;
        int scrY = screenY - gamePanel.tileManager.player.getScreenY() + gamePanel.tileManager.player.py;

        if (gamePanel.tileManager.player.px > gamePanel.tileManager.player.getScreenX()) {
            scrX = screenX;
        }
        if (gamePanel.tileManager.player.py > gamePanel.tileManager.player.getScreenY()) {
            scrY = screenY;
        }

        int rightOffset = gamePanel.screenWidth - gamePanel.tileManager.player.px;
        if (rightOffset > gamePanel.worldWidth - gamePanel.tileManager.player.getScreenX()) {
            scrX = gamePanel.screenWidth - (gamePanel.worldWidth - screenX);
        }
        int bottomOffset = gamePanel.screenHeight - gamePanel.tileManager.player.py;
        if (bottomOffset > gamePanel.worldHeight - gamePanel.tileManager.player.getScreenY()) {
            scrY = gamePanel.screenHeight - (gamePanel.worldHeight - screenY);
        }

        g2.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
        if (gamePanel.tileManager.player.px  > gamePanel.tileManager.player.getScreenX() || gamePanel.tileManager.player.py > gamePanel.tileManager.player.getScreenY() || rightOffset > gamePanel.worldWidth - gamePanel.tileManager.player.getScreenX() || bottomOffset > gamePanel.worldHeight - gamePanel.tileManager.player.getScreenY()) {
            g2.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
