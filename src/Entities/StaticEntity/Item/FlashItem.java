package Entities.StaticEntity.Item;

import Main.GamePanel;
import Image.Image;

import java.awt.*;

public class FlashItem extends Item {
    public FlashItem(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {
        super.update();
        if (useItem && removed) {
            gamePanel.flash = 3;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    public void setImage(Graphics2D g2) {
        this.image = Image.flashItem;
    }
}
