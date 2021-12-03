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
    private List<Enemy> enemy = new ArrayList<>();
    private List<StaticEntity> sEntities = new ArrayList<>();
    public Player player;

    public TileManager(GamePanel gp, KeyHandler keyHandler) {
        this.gamePanel = gp;
        mapTile = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        tiles = new Tile[10];
        getTileImage();
        loadMap("levels/level1.txt");
        player = new Player(1, 1, gp, keyHandler);
        enemy.add(new Balloom(1, 1, gp));
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
        enemy.get(0).update();
        enemy.get(1).update();
    }

    public void draw(Graphics2D g2) {
        for (int maxCol = 0; maxCol<gamePanel.maxWorldCol; maxCol++) {
            for (int maxRow = 0; maxRow<gamePanel.maxWorldRow; maxRow++) {
                int screenX = maxCol * gamePanel.tileSize;
                int screenY = maxRow * gamePanel.tileSize;
                g2.drawImage(tiles[mapTile[maxCol][maxRow]].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
        for (int i=0; i<gamePanel.item.length; i++) {
            if (gamePanel.item[i]!=null) {
                gamePanel.item[i].draw(g2, gamePanel);
            }
        }
        player.draw(g2);
        enemy.get(0).draw(g2);
        enemy.get(1).draw(g2);
        
    }
}
