package Tile;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Entities.MoveEntity.enemy.Balloom;
import Entities.MoveEntity.enemy.Enemy;
import Entities.MoveEntity.enemy.Oneal;
import Entities.StaticEntity.StaticEntity;
import Entities.MoveEntity.Player;

import static javax.imageio.ImageIO.read;

public class TileManager extends Tile {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTile;
    public List<Enemy> enemy = new ArrayList<>();
    public List<StaticEntity> sEntities = new ArrayList<>();
    public Player player;

    public TileManager(GamePanel gp, KeyHandler keyHandler) {
        this.gamePanel = gp;
        mapTile = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        tiles = new Tile[10];
        getTileImage();
        loadMap("levels/level1.txt");
        player = new Player(1, 1, gp, keyHandler);
        enemy.add(new Balloom(20, 1, gp));
        enemy.add(new Oneal(20, 1, gp));
    }
    public void getTileImage() {
        try {
            tiles[0] = new Tile();// Grass tile
            tiles[0].image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/grass.png")));
            tiles[1] = new Tile();// Wall tile
            tiles[1].image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/wall.png")));
            tiles[1].collision = true;
            tiles[2] = new Tile();// Brick tile
            tiles[2].image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/brick.png")));
            tiles[2].collision = true;
            tiles[3] = new Tile();// Portal tile
            tiles[3].image = read(Objects.requireNonNull(getClass().getResourceAsStream("images_tile/portal.png")));
            tiles[3].collision = true;
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
                    String[] numbers = line.split("");
                    int num = Integer.parseInt(numbers[col]);
                    mapTile[col][row] = num;
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

    // public void loadFile(String filePath) {
    //     try {
    //         InputStream is = getClass().getResourceAsStream(filePath);
    //         assert is != null;
    //         BufferedReader br = new BufferedReader((new InputStreamReader(is)));
    //         for (int i = 0; i < gamePanel.maxWorldRow; i++) {
    //             String line = br.readLine();
    //             mapTile[i] = line.split("");
    //         }
    //         br.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public void update() {
        player.update();
        for (Enemy value : enemy) {
            value.update();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX;
            int screenY = worldY;
            int tileNum = mapTile[worldCol][worldRow];
            g2.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            worldCol++;
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        checkRemoved();
        player.draw(g2);
        for (Enemy value : enemy) {
            value.draw(g2);
        }
    }

    public void checkRemoved() {
        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i).isRemoved()) {
                enemy.remove(i);
            }
        }
        if(player.isRemoved()) {

        }
    }
}
