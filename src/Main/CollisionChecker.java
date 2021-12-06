package Main;

import Entities.MoveEntity.MoveEntity;
import Entities.StaticEntity.StaticEntity;
import Entities.StaticEntity.Bomb;

import java.awt.*;

public class CollisionChecker {

    public static boolean CheckEntity(MoveEntity moveEntity1, MoveEntity moveEntity2) {
        if (!moveEntity1.isAlive() || !moveEntity2.isAlive()) {
            return false;
        }
        int entityLeftWorldX = moveEntity1.getScreenX() + moveEntity1.getSolidArea().x;
        int entityRightWorldX = moveEntity1.getScreenX() + moveEntity1.getSolidArea().x
                + moveEntity1.getSolidArea().width;
        int entityTopWorldY = moveEntity1.getScreenY() + moveEntity1.getSolidArea().y;
        int entityBottomWorldY = moveEntity1.getScreenY() + moveEntity1.getSolidArea().y
                + moveEntity1.getSolidArea().height;

        Rectangle rectEntyity2 = new Rectangle();
        rectEntyity2.x = moveEntity2.getScreenX() + moveEntity2.getSolidArea().x;
        rectEntyity2.y = moveEntity2.getScreenY() + moveEntity2.getSolidArea().y;
        rectEntyity2.width = moveEntity2.getSolidArea().width;
        rectEntyity2.height = moveEntity2.getSolidArea().height;

        return isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectEntyity2)
                || isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectEntyity2)
                || isPointInsideRect(entityRightWorldX, entityTopWorldY, rectEntyity2)
                || isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectEntyity2);
    }

    public static boolean CheckStaticEntity(MoveEntity moveEntity, StaticEntity staticEntity) {
        int entityLeftWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x;
        int entityRightWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x + moveEntity.getSolidArea().width;
        int entityTopWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y;
        int entityBottomWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y
                + moveEntity.getSolidArea().height;

        Rectangle rectStaticEntity = new Rectangle();
        rectStaticEntity.x = staticEntity.getScreenX() + staticEntity.getSolidArea().x;
        rectStaticEntity.y = staticEntity.getScreenY() + staticEntity.getSolidArea().y;
        rectStaticEntity.width = staticEntity.getSolidArea().width;
        rectStaticEntity.height = staticEntity.getSolidArea().height;
        if (staticEntity instanceof Bomb) {
            if (!((Bomb) staticEntity).isExploded()) {
                if (isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectStaticEntity)
                        && isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectStaticEntity)
                        && isPointInsideRect(entityRightWorldX, entityTopWorldY, rectStaticEntity)
                        && isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectStaticEntity)) {
                    return false;
                } else {
                    switch (moveEntity.getDirection()) {
                        case "up": {
                            return isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectStaticEntity)
                                    || isPointInsideRect(entityRightWorldX, entityTopWorldY, rectStaticEntity);
                        }
                        case "down": {
                            return isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectStaticEntity)
                                    || isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectStaticEntity);
                        }
                        case "left": {
                            return isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectStaticEntity)
                                    || isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectStaticEntity);
                        }
                        case "right": {
                            return isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectStaticEntity)
                                    || isPointInsideRect(entityRightWorldX, entityTopWorldY, rectStaticEntity);
                        }
                    }
                    return false;
                }
            }
        }
        return isPointInsideRect(entityLeftWorldX, entityTopWorldY, rectStaticEntity)
                || isPointInsideRect(entityLeftWorldX, entityBottomWorldY, rectStaticEntity)
                || isPointInsideRect(entityRightWorldX, entityTopWorldY, rectStaticEntity)
                || isPointInsideRect(entityRightWorldX, entityBottomWorldY, rectStaticEntity);

    }

    public static boolean checkTile(MoveEntity moveEntity, GamePanel gamePanel) {
        int entityLeftWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x;
        int entityRightWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x + moveEntity.getSolidArea().width;
        int entityTopWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y;
        int entityBottomWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y
                + moveEntity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;
        switch (moveEntity.getDirection()) {
            case "up": {
                entityTopRow = (entityTopWorldY - moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityTopRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "down": {
                entityBottomRow = (entityBottomWorldY + moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "left": {
                entityLeftCol = (entityLeftWorldX - moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityLeftCol];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "right": {
                entityRightCol = (entityRightWorldX + moveEntity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityRightCol];
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
        int entityBottomWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y
                + moveEntity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;
        switch (moveEntity.getDirection()) {
            case "up": {
                entityTopRow -= 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityTopRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "down": {
                entityBottomRow += 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "left": {
                entityLeftCol -= 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityLeftCol];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "right": {
                entityRightCol += 1;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityRightCol];
                if (gamePanel.tileManager.tiles[tileNum1].collision
                        || gamePanel.tileManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPointInsideRect(int pointX, int pointY, Rectangle rect) {
        if (pointX > rect.x && pointX < rect.x + rect.width && pointY > rect.y && pointY < rect.y + rect.height) {
            return true;
        }
        return false;
    }

    public static int checkItem(MoveEntity moveEntity, boolean player, GamePanel gamePanel) {
        int index = 999;
        for (int i = 0; i < gamePanel.item.length; i++) {
            if (gamePanel.item[i] != null) {
                // Get entity's solid area position
                moveEntity.getSolidArea().x = moveEntity.getScreenX() + moveEntity.getSolidArea().x;
                moveEntity.getSolidArea().y = moveEntity.getScreenY() + moveEntity.getSolidArea().y;
                // Get the object's solid area position
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

    public static boolean checkFlame(int x, int y, GamePanel gamePanel) {
        if (x < gamePanel.maxWorldCol && y < gamePanel.maxWorldRow && x >= 0 && y >= 0) {
            int tile = gamePanel.tileManager.mapTile[y][x];
            // remove brick
            if (tile == 0) {
                return false;
            } else if (tile == 2) {
                gamePanel.tileManager.mapTile[y][x] = 0;
            }
        }
        return true;
    }
}
