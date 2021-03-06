package Entities.StaticEntity.Item;

import Entities.MoveEntity.MoveEntity;
import Entities.MoveEntity.Player;
import Entities.StaticEntity.StaticEntity;
import Main.CollisionChecker;
import Main.GamePanel;
import Sound.Sound;

public abstract class Item extends StaticEntity {
    protected boolean appear = false;
    protected boolean useItem = false;
    public Item(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        appear = false;
        useItem = false;
    }
    @Override
    public void update() {
        int x = screenY / gamePanel.tileSize;
        int y = screenX / gamePanel.tileSize;
        int tile = gamePanel.boardManager.mapTile[x][y];
        if (tile == 3) {
            appear = true;
        } else if (tile == 0) {
            removed = true;
        }
        if (appear && !removed) {
            for (MoveEntity moveEntity : gamePanel.boardManager.moveEntities) {
                if (CollisionChecker.CheckEntity(moveEntity, this)) {
                    if (moveEntity instanceof Player) {
                        gamePanel.sound.item.play();
                        useItem = true;
                    }
                    gamePanel.boardManager.mapTile[x][y] = 0;
                    removed = true;
                    break;
                }
            }
        }
    }
}
