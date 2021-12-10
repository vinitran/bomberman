package Entities.MoveEntity.enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Entities.MoveEntity.MoveEntity;
import Entities.MoveEntity.enemy.ai.AI1;
import Image.Image;
import Main.CollisionChecker;
import Main.GamePanel;

public abstract class Enemy extends MoveEntity {

    public Enemy(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        direction = "right";
        collision = true;
        timeToRemove = 60;
    }

    public void setRectangle() {
        solidArea = new Rectangle(speed, speed, 16 * gamePanel.scale - speed, 16 * gamePanel.scale - speed);
    }

    @Override
    public void update() {
        super.update();
        if (!alive) {
            if (!direction.equals("dead")) {
                direction = "dead";
            }
            timeToRemove--;
        } else {
            move();
        }
        if (timeToRemove == 0) {
            removed = true;
        }
    }

    public void move() {
        if (!AI1.BasicAI(this, gamePanel) || CollisionBomb) {
            if (CollisionChecker.checkTile(this, gamePanel, 0) || CollisionBomb) {
                switch (direction) {
                    case "up":
                        direction = "down";
                        break;
                    case "down":
                        direction = "up";
                        break;
                    case "left":
                        direction = "right";
                        break;
                    case "right":
                        direction = "left";
                        break;
                }
            }
        }

        if (!CollisionChecker.checkTile(this, gamePanel, 0)) {
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

    @Override
    public void setImage(Graphics2D g2) {
        if (timeToRemove == 30) {
            spriteNum = 1;
            spriteCounter = 0;
        }
        switch(spriteNum) {
            case 1: 
                image = Image.mob_dead1;
                break;
            case 2:
                image = Image.mob_dead2;
                break;
            case 3:
                image = Image.mob_dead3;
                break;
        }
    }

    public void drawDeadEnemy() {

    }
}