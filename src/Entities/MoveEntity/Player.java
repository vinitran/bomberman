package Entities.MoveEntity;

import Entities.StaticEntity.Bomb;
import Image.Image;
import Main.CollisionChecker;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;

public class Player extends MoveEntity {
    private KeyHandler keyHandler;
    private int timeSetBombs = 0;
    public boolean flash = false;
    public int px;
    public int py;

    public Player(int x, int y, GamePanel gp) {
        super(x, y, gp);
        this.keyHandler = gamePanel.getKeyHandler();
        solidArea = new Rectangle(2 * gamePanel.scale, 4 * gamePanel.scale, 8 * gamePanel.scale, 9 * gamePanel.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        px = gamePanel.screenWidth / 2;
        py = gamePanel.screenHeight / 2;
        setDefaultValues();
    }

    public void setDefaultValues() {
        gamePanel.bombRadius = 1;
        gamePanel.nBombs = 1;
        speed = 2;
        direction = "stand";
    }

    @Override
    public void update() {
        checkCollision();
        if (!alive) {
            if (!direction.equals("dead")) {
                direction = "dead";
                spriteNum = 1;
                spriteCounter = 0;
                timeToRemove = 30;
            }
            timeToRemove--;
        } else {
            if (timeSetBombs < -50)
                timeSetBombs = 0;
            else
                timeSetBombs--;
            move();
        }
        if (timeToRemove == 0) {
            removed = true;
        }
    }

    public void checkCollision() {
        for (MoveEntity value : gamePanel.tileManager.MoveEntities) {
            if (!(value instanceof Player)) {
                if (CollisionChecker.CheckEntity(this, value)) {
                    alive = false;
                    break;
                }
            }
        }

    }

    public Bomb makeBomb() {
        if (timeSetBombs < 0) {
            int xBomb = (screenX + solidArea.x + solidArea.width / 2) / gamePanel.tileSize;
            int yBomb = (screenY + solidArea.y + solidArea.height / 2) / gamePanel.tileSize;
            for (Bomb bomb : gamePanel.tileManager.bombs) {
                if (bomb.getScreenX() == xBomb * gamePanel.tileSize) {
                    if (bomb.getScreenY() == yBomb * gamePanel.tileSize) {
                        return null;
                    }
                }
            }
            if (gamePanel.tileManager.mapTile[yBomb][xBomb] == 3) {
                return null;
            }
            if (timeToRemove < 30) {
                return null;
            }
            timeSetBombs = 30;
            return new Bomb(xBomb, yBomb, gamePanel);
        }
        return null;
    }

    private void move() {
        if (keyHandler.upPressed) {
            direction = "up";
        } else if (keyHandler.downPressed) {
            direction = "down";
        } else if (keyHandler.leftPressed) {
            direction = "left";
        } else if (keyHandler.rightPressed) {
            direction = "right";
        } else {
            direction = "stand";
        }

        // Check Tile Manager
        if (!CollisionChecker.checkTile(this, gamePanel, 0) && !CollisionBomb) {
            if (!CollisionChecker.checkTile(this, gamePanel, 1)) {
                switch (direction) {
                    case "up":
                        screenX -= ((screenX + solidArea.x) % gamePanel.tileSize - 8);
                        break;
                    case "down":
                        screenX -= ((screenX + solidArea.x) % gamePanel.tileSize - 8);
                        break;
                    case "left":
                        screenY -= ((screenY + solidArea.y) % gamePanel.tileSize - 6);
                        break;
                    case "right":
                        screenY -= ((screenY + solidArea.y) % gamePanel.tileSize - 6);
                        break;
                }
            }
            switch (direction) {
                case "up":
                    screenY -= speed;
                    break;
                case "down":
                    screenY += speed;
                    break;
                case "left":
                    screenX -= speed;
                    break;
                case "right":
                    screenX += speed;
                    break;
            }
        }
    }

    // public void pickUpItem(int index) {
    //     if (index != 999) {
    //         String itemName = gamePanel.item[index].getName();
    //         switch (itemName) {
    //             case "Ghost":
    //                 this.speed += 4;
    //                 gamePanel.ui.ShowMessage("Ghost");
    //                 gamePanel.item[index] = null;
    //                 break;
    //             case "Flash":
    //                 flash = true;
    //                 gamePanel.ui.ShowMessage("Flash");
    //                 gamePanel.item[index] = null;
    //                 break;
    //             case "Portal":
    //                 gamePanel.ui.gameFinished = true;
    //                 break;
    //         }
    //     }

    // }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2); 
    }

    @Override
    public void setImage(Graphics2D g2) {
        image = null;
        if (!removed) {
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            switch (direction) {
                case "up":
                    switch (spriteNum) {
                        case 1:
                            image = Image.player_up;
                            break;
                        case 2:
                            image = Image.player_up1;
                            break;
                        case 3:
                            image = Image.player_up2;
                            break;
                    }
                    break;
                case "down":
                    switch (spriteNum) {
                        case 1:
                            image = Image.player_down;
                            break;
                        case 2:
                            image = Image.player_down1;
                            break;
                        case 3:
                            image = Image.player_down2;
                            break;
                    }
                    break;
                case "left":
                    switch (spriteNum) {
                        case 1:
                            image = Image.player_left;
                            break;
                        case 2:
                            image = Image.player_left1;
                            break;
                        case 3:
                            image = Image.player_left2;
                            break;
                    }
                    break;
                case "right":
                    switch (spriteNum) {
                        case 1:
                            image = Image.player_right;
                            break;
                        case 2:
                            image = Image.player_right1;
                            break;
                        case 3:
                            image = Image.player_right2;
                            break;
                    }
                    break;
                case "stand":
                    switch (spriteNum) {
                        case 1:
                            image = Image.player_stand;
                            break;
                        case 2:
                            image = Image.player_stand1;
                            break;
                        case 3:
                            image = Image.player_stand2;
                            break;
                    }
                    break;
                case "dead":
                    switch (spriteNum) {
                        case 1:
                            image = Image.player_dead1;
                            break;
                        case 2:
                            image = Image.player_dead2;
                            break;
                        case 3:
                            image = Image.player_dead3;
                            break;
                    }
                    break;
            }
        }

    }
}
