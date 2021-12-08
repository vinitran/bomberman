package Entities.StaticEntity.Item;

import Main.GamePanel;
import Image.Image;

import java.awt.*;

public class Flash extends Item {
    public Flash(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        this.name = "Flash";
    }

    @Override
    public void update() {
        super.update();
        if (useItem && removed) {
            gamePanel.tileManager.player.flash = true;
        }
    }

    public void setImage(Graphics2D g2) {
        this.image = Image.Flash;
    }
}
