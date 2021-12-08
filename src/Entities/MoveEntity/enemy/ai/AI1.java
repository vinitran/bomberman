package Entities.MoveEntity.enemy.ai;

import java.util.Random;

import Entities.MoveEntity.enemy.Enemy;
import Main.CollisionChecker;
import Main.GamePanel;

public class AI1 {

    public static boolean BasicAI(Enemy enemy, GamePanel gamePanel) {
        Random random = new Random();
        int temp = random.nextInt() % 3;
        String direct = enemy.getDirection();

        if (enemy.getDirection().equals("up") || enemy.getDirection().equals("down")) {
            if (temp == 0) {
                enemy.setDirection("left");
            } else if (temp == 1) {
                enemy.setDirection("right");
            }
            if (CollisionChecker.checkTile(enemy, gamePanel, 0)) {
                if (enemy.getDirection().equals("left")) {
                    enemy.setDirection("right");
                } else {
                    enemy.setDirection("left");
                }
                if (!CollisionChecker.checkTile(enemy, gamePanel, 0)) {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (temp == 0) {
                enemy.setDirection("up");
            } else if (temp == 1) {
                enemy.setDirection("down");
            }
            if (CollisionChecker.checkTile(enemy, gamePanel, 0)) {
                if (enemy.getDirection().equals("up")) {
                    enemy.setDirection("down");
                } else {
                    enemy.setDirection("up");
                }
                if (!CollisionChecker.checkTile(enemy, gamePanel, 0)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        enemy.setDirection(direct);
        return false;
    }
}