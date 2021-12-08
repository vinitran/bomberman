package Entities.StaticEntity.Item;

import Entities.StaticEntity.StaticEntity;
import Main.GamePanel;

public abstract class Item extends StaticEntity {
    protected boolean appear = false;

    public Item(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    @Override
    public void update() {
        int tile = gamePanel.tileManager.mapTile[screenX / gamePanel.tileSize][screenY / gamePanel.tileSize];
        if (tile == 3) {
            appear = true;
        } else if (tile == 0) {
            removed = true;
        }
    }
}