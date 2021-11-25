package Tile;

import Main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static javax.imageio.ImageIO.read;

public class TileManager extends Tile {
    GamePanel gamePanel;
    Tile brick, brick_exploded, grass, wall, portal;
    char[][] mapTile;
    public TileManager(GamePanel gp) {
        this.gamePanel = gp;
        mapTile = new char[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("levels/level1.txt");
    }

    public void getTileImage() {
        try {
            brick = new Tile();
            brick_exploded = new Tile();
            grass = new Tile();
            wall = new Tile();
            portal = new Tile();
            brick.image = read(getClass().getResourceAsStream("images_tile/brick.png"));
            brick_exploded.image = read(getClass().getResourceAsStream("images_tile/brick_exploded.png"));
            grass.image = read(getClass().getResourceAsStream("images_tile/grass.png"));
            wall.image = read(getClass().getResourceAsStream("images_tile/wall.png"));
            portal.image = read(getClass().getResourceAsStream("images_tile/portal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
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

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
            switch (mapTile[worldCol][worldRow]) {
                case '*':
                    g2.drawImage(brick.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                    break;
                case '#':
                    g2.drawImage(wall.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                    break;
                case 'x':
                    g2.drawImage(portal.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
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
    }
}
