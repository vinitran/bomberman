package Main;

import Item.Flash;
import Item.Portal;
import Item.SpeedUp;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.item[0] = new SpeedUp();
        gamePanel.item[0].worldX = 4*gamePanel.tileSize;
        gamePanel.item[0].worldY = 1*gamePanel.tileSize;

        gamePanel.item[1] = new Flash();
        gamePanel.item[1].worldX = 1*gamePanel.tileSize;
        gamePanel.item[1].worldY = 5*gamePanel.tileSize;

        gamePanel.item[2] = new Portal();
        gamePanel.item[2].worldX = 11*gamePanel.tileSize;
        gamePanel.item[2].worldY = 5*gamePanel.tileSize;
    }
}
