package Tile;

import Main.CollisionChecker;
import Main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class TileManager extends Tile {
    GamePanel gamePanel;
    Tile brick, brick_exploded, grass, wall, portal;
    public char[][] mapTile;
    public boolean canMovePlayerUp;
    public boolean canMovePlayerDown;
    public boolean canMovePlayerLeft;
    public boolean canMovePlayerRight;
    CollisionChecker cChecker;
    public TileManager(GamePanel gp) {
        this.gamePanel = gp;
        cChecker = new CollisionChecker(gp);
        mapTile = new char[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("levels/level1.txt");
        canMovePlayerUp = true;
        canMovePlayerDown = true;
        canMovePlayerLeft = true;
        canMovePlayerRight = true;
    }
    public void getTileImage() {
        try {
            brick = new Tile();
            brick_exploded = new Tile();
            grass = new Tile();
            wall = new Tile();
            portal = new Tile();
            brick.image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/brick.png")));
            brick_exploded.image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/brick_exploded.png")));
            grass.image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/grass.png")));
            wall.image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/wall.png")));
            portal.image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/portal.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));
            int col = 0;
            int row = 0;
            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();
                while(col < gamePanel.maxWorldCol) {
                    mapTile[col][row] = line.charAt(col);
                    col++;
                }
                if(col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        boolean isCollisionTop = false;
        boolean isCollisionBottom = false;
        boolean isCollisionLeft = false;
        boolean isCollisionRight = false;
        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX + gamePanel.player.worldX;
            int screenY = worldY + gamePanel.player.worldY;

             switch (mapTile[worldCol][worldRow]) {
                 case '*':
                     g2.drawImage(brick.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                     if (cChecker.isCollisionTop(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionTop = true;
                     }
                     if (cChecker.isCollisionBottom(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionBottom = true;
                     }
                     if (cChecker.isCollisionLeft(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionLeft = true;
                     }
                     if (cChecker.isCollisionRight(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionRight = true;
                     }
                     break;
                 case '#':
                     g2.drawImage(wall.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                     if (cChecker.isCollisionTop(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionTop = true;
                     }
                     if (cChecker.isCollisionBottom(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionBottom = true;
                     }
                     if (cChecker.isCollisionLeft(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionLeft = true;
                     }
                     if (cChecker.isCollisionRight(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionRight = true;
                     }
                     break;
                 case 'x':
                     g2.drawImage(portal.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                     if (cChecker.isCollisionTop(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionTop = true;
                     }
                     if (cChecker.isCollisionBottom(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionBottom = true;
                     }
                     if (cChecker.isCollisionLeft(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionLeft = true;
                     }
                     if (cChecker.isCollisionRight(gamePanel.player, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize)) {
                         isCollisionRight = true;
                     }
                     break;
                 default:
                     g2.drawImage(grass.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                     break;
            }
            worldCol++;
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }
        if (isCollisionTop) {
            canMovePlayerUp = false;
        }
        if (!isCollisionTop) {
            canMovePlayerUp = true;
        }
        if (isCollisionBottom) {
            canMovePlayerDown = false;
        }
        if (!isCollisionBottom) {
            canMovePlayerDown = true;
        }
        if (isCollisionLeft) {
            canMovePlayerLeft = false;
        }
        if (!isCollisionLeft) {
            canMovePlayerLeft = true;
        }
        if (isCollisionRight) {
            canMovePlayerRight = false;
        }
        if (!isCollisionRight) {
            canMovePlayerRight = true;
        }
        System.out.println("Right " + canMovePlayerRight);
        System.out.println("Left " + canMovePlayerLeft);
        System.out.println("Up " + canMovePlayerUp);
        System.out.println("Down " + canMovePlayerDown);
    }
}
