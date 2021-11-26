package Main;

import Entities.MoveEntity.MoveEntity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gp) {
        this.gamePanel = gp;
    }

    public void checkTile(MoveEntity moveEntity) {
        int entityLeftWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x;
        int entityRightWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x + moveEntity.getSolidArea().width;
        int entityTopWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y;
        int entityBottomWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y + moveEntity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;
        switch (moveEntity.getDirection()) {
            case "up": {
                entityTopRow = (entityTopWorldY - moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    moveEntity.setCollisionOn(true);
                }
            }
                break;
            case "down": {
                entityBottomRow = (entityBottomWorldY + moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    moveEntity.setCollisionOn(true);
                }
            }
                break;
            case "left": {
                entityLeftCol = (entityLeftWorldX - moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    moveEntity.setCollisionOn(true);
                }
            }
                break;
            case "right": {
                entityRightCol = (entityRightWorldX + moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    moveEntity.setCollisionOn(true);
                }
            }
        }
    }
}
