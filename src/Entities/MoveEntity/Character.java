package Entities.MoveEntity;

import Main.GamePanel;

import java.awt.*;

public class Character {
    public Player player;
    public Balloom[] ballooms = new Balloom[5];
    public GamePanel gp;
    public Character(GamePanel gp) {
        this.gp = gp;
        player = new Player(gp);
        ballooms[0] = new Balloom(gp,4 * gp.tileSize,11 * gp.tileSize, "left");
        ballooms[1] = new Balloom(gp,21 * gp.tileSize,4 * gp.tileSize, "down");
        ballooms[2] = new Balloom(gp,5 * gp.tileSize,7 * gp.tileSize, "up");
        ballooms[3] = new Balloom(gp,26 * gp.tileSize,9 * gp.tileSize, "left");
        ballooms[4] = new Balloom(gp,26 * gp.tileSize,11 * gp.tileSize, "right");
    }
    public void update() {
        player.update();
        for (int i = 0; i < 5; i++) {
            ballooms[i].update();
        }
    }
    public void draw(Graphics2D g) {
        player.draw(g);
        for (int i = 0; i < 5; i++) {
            ballooms[i].draw(g);
        }

    }
}
