package Main;

import Entities.Entity;
import Entities.MoveEntity.MoveEntity;
import Entities.StaticEntity.Bomb;
import Entities.StaticEntity.StaticEntity;

import java.awt.*;

public class CollisionChecker {

    public static boolean CheckEntity(MoveEntity moveEntity, Entity entity) {
        if (!moveEntity.isAlive()) {
            return false;
        }

        if (entity instanceof MoveEntity) {
            if (!((MoveEntity) entity).isAlive()) {
                return false;
            }
        }

        int entityLeftWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x;
        int entityRightWorldX = moveEntity.getScreenX() + moveEntity.getSolidArea().x + moveEntity.getSolidArea().width;
        int entityTopWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y;
        int entityBottomWorldY = moveEntity.getScreenY() + moveEntity.getSolidArea().y
                + moveEntity.getSolidArea().height;

        Rectangle rectStaticEntity = new Rectangle();
        rectStaticEntity.x = entity.getScreenX() + entity.getSolidArea().x;
        rectStaticEntity.y = entity.getScreenY() + entity.getSolidArea().y;
        rectStaticEntity.width = entity.getSolidArea().width;
        rectStaticEntity.height = entity.getSolidArea().height;
        if (entity instanceof Bomb) {
            if (!((Bomb) entity).isExploded()) {
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

    public static boolean checkTile(MoveEntity moveEntity, GamePanel gamePanel, int type) {
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
                if (type == 0) {
                    entityTopRow = (entityTopWorldY - moveEntity.getSpeed()) / gamePanel.tileSize;
                } else {
                    entityTopRow -= type;
                }
                if (entityTopRow < 0) {
                    return true;
                }
                tileNum1 = gamePanel.BoardManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.BoardManager.mapTile[entityTopRow][entityRightCol];
                if (gamePanel.BoardManager.tiles[tileNum1].collision
                        || gamePanel.BoardManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "down": {
                if (type == 0) {
                    entityBottomRow = (entityBottomWorldY + moveEntity.getSpeed()) / gamePanel.tileSize;
                } else {
                    entityBottomRow += type;
                }
                if (entityBottomRow >= gamePanel.BoardManager.mapTile.length) {
                    return true;
                }
                tileNum1 = gamePanel.BoardManager.mapTile[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.BoardManager.mapTile[entityBottomRow][entityRightCol];
                if (gamePanel.BoardManager.tiles[tileNum1].collision
                        || gamePanel.BoardManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "left": {
                if (type == 0) {
                    entityLeftCol = (entityLeftWorldX - moveEntity.getSpeed()) / gamePanel.tileSize;
                } else {
                    entityLeftCol -= type;
                }
                if (entityLeftCol < 0) {
                    return true;
                }
                tileNum1 = gamePanel.BoardManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.BoardManager.mapTile[entityBottomRow][entityLeftCol];
                if (gamePanel.BoardManager.tiles[tileNum1].collision
                        || gamePanel.BoardManager.tiles[tileNum2].collision) {
                    return true;
                }
            }
                break;
            case "right": {
                if (type == 0) {
                    entityRightCol = (entityRightWorldX + moveEntity.getSpeed()) / gamePanel.tileSize;
                } else {
                    entityRightCol += type;
                }
                if (entityRightCol >= gamePanel.BoardManager.mapTile[entityBottomRow].length) {
                    return true;
                }
                if (entityRightCol >= gamePanel.BoardManager.mapTile[entityTopRow].length) {
                    return true;
                }
                tileNum1 = gamePanel.BoardManager.mapTile[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.BoardManager.mapTile[entityBottomRow][entityRightCol];
                if (gamePanel.BoardManager.tiles[tileNum1].collision
                        || gamePanel.BoardManager.tiles[tileNum2].collision) {
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

    public static boolean checkFlame(int x, int y, GamePanel gamePanel) {
        if (x < gamePanel.maxWorldCol && y < gamePanel.maxWorldRow && x >= 0 && y >= 0) {
            int tile = gamePanel.BoardManager.mapTile[y][x];
            // remove brick
            if (tile == 0) {
                return false;
            } else if (tile == 2) {
                gamePanel.BoardManager.mapTile[y][x] = 0;
            } else if (tile == 3) {
                gamePanel.BoardManager.mapTile[y][x] = 0;
                return false;
            } else if (tile == 4) {
                gamePanel.BoardManager.mapTile[y][x] = 3;
            }
        }
        return true;
    }
}
