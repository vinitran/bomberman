package Entities.MoveEntity;

import Main.CollisionChecker;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.util.ArrayList;

import Entities.StaticEntity.Bomb;
import Image.Image;
import Sound.Sound;

public class Player extends MoveEntity {
    private KeyHandler keyHandler;
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private int nBombs;
    private int timeSetBombs = 0;
    public boolean flash = false;
    public boolean gameFinished = false;

    public Player(int x, int y, GamePanel gp, KeyHandler keyH) {
        super(x, y, gp);
        this.keyHandler = keyH;
        solidArea = new Rectangle(2 * gamePanel.scale, 4 * gamePanel.scale, 8 * gamePanel.scale, 9 * gamePanel.scale);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
    }

    public void setDefaultValues() {
        // worldX = 0;
        // worldY = 0;
        gamePanel.setBombRadius(2);
        nBombs = 2;
        speed = 2;
        direction = "stand";
    }

    @Override
    public void update() {
        if (!alive) {
            direction = "dead";
            timeToRemove--;
        } else {
            if (timeSetBombs < -50) timeSetBombs = 0; else timeSetBombs--;
            move();
        }
        if(timeToRemove == 0) {
            removed = true;
        }
        if(CollisionChecker.CheckEntity(this, gamePanel.tileManager.enemy.get(0))) {
            alive = false;
        }
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
        } else if (keyHandler.spacePressed && nBombs > 0) {
            if (timeSetBombs < 0) {
                Sound.sound_makeBom.play();
                int xBomb = (screenX + solidArea.x + solidArea.width / 2) / gamePanel.tileSize;
                int yBomb = (screenY + solidArea.y + solidArea.height / 2) / gamePanel.tileSize;
                bombs.add(new Bomb(xBomb, yBomb, gamePanel));
                nBombs--;
                timeSetBombs = 30;
            }
        } else {
            direction = "stand";
        }
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isRemoved()) {
                bombs.get(i).update();
            } else {
                bombs.remove(i);
                nBombs++;
            }
        }
        int indexCheckC = CollisionChecker.checkItem(this, true, gamePanel);
        pickUpItem(indexCheckC);
        // Check Tile Manager
        if (!CollisionChecker.checkTile(this, gamePanel) && !CollisionChecker.CheckEntity(this, gamePanel.tileManager.enemy.get(0))) {
            if (!CollisionChecker.check(this, gamePanel)) {
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
    }
    public void pickUpItem(int index) {
        if (index!=999) {
            String itemName = gamePanel.item[index].name;
            switch(itemName) {
                case "Ghost":
                    this.speed += 4;
                    gamePanel.ui.ShowMessage("Ghost");
                    gamePanel.item[index] = null;
                    break;
                case "flash":
                    flash = true;
                    gamePanel.ui.ShowMessage("Flash");
                    gamePanel.item[index] = null;
                    break;
                case "Portal":
                    gameFinished = true;
                    break;
            }
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        image = null;
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
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        for (Bomb bomb : bombs) {
            bomb.draw(g2);
        }
    }
}
