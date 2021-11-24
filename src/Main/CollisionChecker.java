package Main;

import Entities.Player;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public boolean isCollisionTop(Player player, int screenX, int screenY, int screenWidth, int screenHeight) {
        return isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x, player.screenY + player.solidArea.y)
                && isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x + player.solidArea.width, player.screenY + player.solidArea.y);
    }
    public boolean isCollisionLeft(Player player, int screenX, int screenY, int screenWidth, int screenHeight) {
        return isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x, player.screenY + player.solidArea.y)
                && isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x, player.screenY + player.solidArea.y);
    }
    public boolean isCollisionRight(Player player, int screenX, int screenY, int screenWidth, int screenHeight) {
        return isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x + player.solidArea.width, player.screenY + player.solidArea.y)
                && isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x, player.screenY + player.solidArea.y + player.solidArea.height);
    }
    public boolean isCollisionBottom(Player player, int screenX, int screenY, int screenWidth, int screenHeight) {
        return isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x + player.solidArea.width, player.screenY + player.solidArea.y)
                && isPointInRect(screenX, screenY, screenWidth, screenHeight, player.screenX + player.solidArea.x, player.screenY + player.solidArea.y + player.solidArea.height);
    }
    boolean isPointInRect(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
        return pointX > rectX && pointX < rectX + rectWidth
                && pointY > rectY && pointY < rectY + rectHeight;
    }
}
