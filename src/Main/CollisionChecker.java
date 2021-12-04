package Main;

import Entities.MoveEntity.MoveEntity;

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

    public static int checkItem(MoveEntity moveEntity, boolean player, GamePanel gamePanel) {
        int index = 999;
        for (int i=0; i<gamePanel.item.length; i++) {
            if (gamePanel.item[i]!=null) {
                //Get entity's solid area position
                moveEntity.getSolidArea().x = moveEntity.getScreenX() + moveEntity.getSolidArea().x;
                moveEntity.getSolidArea().y = moveEntity.getScreenY() + moveEntity.getSolidArea().y;
                //Get the object's solid area position
                gamePanel.item[i].solidArea.x = gamePanel.item[i].worldX + gamePanel.item[i].solidArea.x;
                gamePanel.item[i].solidArea.y = gamePanel.item[i].worldY + gamePanel.item[i].solidArea.y;

                switch (moveEntity.getDirection()) {
                    case "up":
                        moveEntity.getSolidArea().y -= moveEntity.getSpeed();
                        if (moveEntity.getSolidArea().intersects(gamePanel.item[i].solidArea)) {
                            if (gamePanel.item[i].collision) {
                                moveEntity.setCollision(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        moveEntity.getSolidArea().y += moveEntity.getSpeed();
                        if (moveEntity.getSolidArea().intersects(gamePanel.item[i].solidArea)) {
                            if (gamePanel.item[i].collision) {
                                moveEntity.setCollision(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        moveEntity.getSolidArea().x -= moveEntity.getSpeed();
                        if (moveEntity.getSolidArea().intersects(gamePanel.item[i].solidArea)) {
                            if (gamePanel.item[i].collision) {
                                moveEntity.setCollision(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        moveEntity.getSolidArea().x += moveEntity.getSpeed();
                        if (moveEntity.getSolidArea().intersects(gamePanel.item[i].solidArea)) {
                            if (gamePanel.item[i].collision) {
                                moveEntity.setCollision(true);
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                moveEntity.getSolidArea().x = moveEntity.solidAreaDefaultX;
                moveEntity.getSolidArea().y = moveEntity.solidAreaDefaultY;
                gamePanel.item[i].solidArea.x = gamePanel.item[i].solidAreaDefaultX;
                gamePanel.item[i].solidArea.y = gamePanel.item[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
