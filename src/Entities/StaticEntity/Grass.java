package Entities.StaticEntity;

import java.awt.Graphics2D;
import Image.Image;
import Main.GamePanel;

public class Grass extends StaticEntity {
    public Grass(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {        
    }

    @Override
    public void setImage(Graphics2D g2) {
        image = Image.grass;
    }
}
