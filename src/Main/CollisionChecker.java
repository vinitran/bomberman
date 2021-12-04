package Main;

import Entities.MoveEntity.MoveEntity;
import Entities.StaticEntity.StaticEntity;

import java.awt.*;

public class CollisionChecker {

    public static boolean CheckEntity(MoveEntity moveEntity1, MoveEntity moveEntity2) {
        int entityLeftWorldX = moveEntity1.getScreenX() + moveEntity1.getSolidArea().x;
        int entityRightWorldX = moveEntity1.getScreenX() + moveEntity1.getSolidArea().x + moveEntity1.getSolidArea().width;
        int entityTopWorldY = moveEntity1.getScreenY() + moveEntity1.getSolidArea().y;
        int entityBottomWorldY = moveEntity1.getScreenY() + moveEntity1.getSolidArea().y + moveEntity1.getSolidArea().height;

        Rectangle rectEntyity2 = new Rectangle();
        rectEntyity2.x = moveEntity2.getScreenX() + moveEntity2.getSolidArea().x;
        rectEntyity2.y = moveEntity2.getScreenY() + moveEntity2.getSolidArea().y;
        rectEntyity2.width = moveEntity2.getSolidArea().width;
        rectEntyity2.height = moveEntity2.getSolidArea().height;

        return isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectEntyity2) ||
                isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectEntyity2) ||
                isPointInsideRect(entityRightWorldX, entityTopWorldY, rectEntyity2) ||
                isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectEntyity2);
    }

    public static boolean CheckStaticEntity(MoveEntity player, StaticEntity item) {
        int entityLeftWorldX = player.getScreenX() + player.getSolidArea().x;
        int entityRightWorldX = player.getScreenX() + player.getSolidArea().x + player.getSolidArea().width;
        int entityTopWorldY = player.getScreenY() + player.getSolidArea().y;
        int entityBottomWorldY = player.getScreenY() + player.getSolidArea().y + player.getSolidArea().height;

        Rectangle rectItem = new Rectangle();
        rectItem.x = item.getScreenX() + item.getSolidArea().x;
        rectItem.y = item.getScreenY() + item.getSolidArea().y;
        rectItem.width = item.getSolidArea().width;
        rectItem.height = item.getSolidArea().height;

        if (isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectItem) &&
                isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectItem) &&
                isPointInsideRect(entityRightWorldX, entityTopWorldY, rectItem) &&
                isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectItem)) {
            return false;
        } else {
            switch (player.getDirection()) {
                case "up": {
                    return isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectItem) ||
                            isPointInsideRect(entityRightWorldX, entityTopWorldY, rectItem);
                }
                case "down": {
                    return isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectItem) ||
                            isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectItem);
                }
                case "left": {
                    return isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectItem) ||
                            isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectItem);
                }
                case "right": {
                    return isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectItem) ||
                            isPointInsideRect(entityRightWorldX, entityTopWorldY, rectItem);
                }
            }
            return false;
        }

    }
    public static boolean checkTile(MoveEntity moveEntity, GamePanel gamePanel) {
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
                    return true;
                }
            }
                break;
            case "down": {
                entityBottomRow = (entityBottomWorldY + moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "left": {
                entityLeftCol = (entityLeftWorldX - moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "right": {
                entityRightCol = (entityRightWorldX + moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(MoveEntity moveEntity, GamePanel gamePanel) {
        // if (moveEntity.getScreenX() % )
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
                entityTopRow -= 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "down": {
                entityBottomRow += 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "left": {
                entityLeftCol -= 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "right": {
                entityRightCol += 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPointInsideRect(int pointX, int pointY, Rectangle rect) {
        if(pointX > rect.x && pointX < rect.x + rect.width &&
                pointY > rect.y && pointY < rect.y + rect.height) {
            return true;
        }
        return false;
    }

}
