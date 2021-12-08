package Entities.StaticEntity.Item;

import Main.GamePanel;
import Image.Image;

import java.awt.*;


public class SpeedItem extends Item {
    public SpeedItem(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        this.name = "Ghost";
    }

    @Override
    public void update() {
        super.update();
        if (useItem && removed) {
            gamePanel.tileManager.player.setSpeed(3);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public void setImage(Graphics2D g2) {
        this.image = Image.Ghost;
    }
}