package Entities.StaticEntity.Item;

import java.awt.Graphics2D;

import Entities.MoveEntity.MoveEntity;
import Entities.MoveEntity.Player;
import Main.CollisionChecker;
import Main.GamePanel;

public class BombItem extends Item {
    public BombItem(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {
        super.update();
        if (appear && !removed) {
            for (MoveEntity moveEntity : gamePanel.tileManager.MoveEntities) {
                if (CollisionChecker.CheckEntity(moveEntity, this)) {
                    if (moveEntity instanceof Player) {
                        gamePanel.nBombs++;
                    }
                    removed = true;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        super.draw(g2);
    }

    @Override
    public void setImage(Graphics2D g2) {
        // TODO Auto-generated method stub
        
    }
}