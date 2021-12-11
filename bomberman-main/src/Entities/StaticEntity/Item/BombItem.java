package Entities.StaticEntity.Item;

import java.awt.Graphics2D;

import Image.Image;
import Main.GamePanel;

public class BombItem extends Item {
    public BombItem(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {
        super.update();
        if (useItem && removed) {
            gamePanel.nBombs++;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public void setImage(Graphics2D g2) {
        image = Image.bombItem;
        
    }
}