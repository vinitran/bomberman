package Entities.StaticEntity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Entities.MoveEntity.MoveEntity;
import Entities.MoveEntity.Player;
import Entities.MoveEntity.enemy.Enemy;
import Image.Image;
import Main.CollisionChecker;
import Main.GamePanel;

public class Portal extends StaticEntity {
    private boolean appear = false;
    private boolean nextLevel = false;

    public Portal(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        solidArea = new Rectangle(7 * gamePanel.scale, 7 * gamePanel.scale, 2 * gamePanel.scale, 2 * gamePanel.scale);
    }

    @Override
    public void update() {
        int x = screenY / gamePanel.tileSize;
        int y = screenX / gamePanel.tileSize;
        int tile = gamePanel.BoardManager.mapTile[x][y];
        if (tile == 3) {
            appear = true;
        } else if (tile == 0) {
            gamePanel.BoardManager.mapTile[x][y] = 3;
        }
        nextLevel = true;
        for (MoveEntity moveEntity : gamePanel.BoardManager.MoveEntities) {
            if (moveEntity instanceof Enemy) {
                nextLevel = false;
                break;
            }
        }
        if (appear && nextLevel) {
            for (MoveEntity moveEntity : gamePanel.BoardManager.MoveEntities) {
                if (moveEntity instanceof Player) {
                    if (CollisionChecker.CheckEntity(moveEntity, this)) {
                        gamePanel.ui.gameFinished = true; // end (or next level)
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    @Override
    public void setImage(Graphics2D g2) {
        image = Image.portal;
    }
}
