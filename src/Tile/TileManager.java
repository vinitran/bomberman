package Tile;

import Main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.awt.image.BufferedImage;


import Entities.MoveEntity.enemy.Balloom;
import Entities.MoveEntity.enemy.Oneal;
import Entities.StaticEntity.Bomb;
import Entities.StaticEntity.Brick;
import Entities.StaticEntity.StaticEntity;
import Entities.MoveEntity.MoveEntity;
import Entities.MoveEntity.Player;

import static javax.imageio.ImageIO.read;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public String[][] map;
    public int[][] mapTile;
    public List<MoveEntity> MoveEntities = new ArrayList<>();
    public List<StaticEntity> bricks = new ArrayList<>();
    public List<Bomb> bombs = new LinkedList<>();
    public Player player;
    private BufferedImage image;

    public TileManager(GamePanel gp) {
        this.gamePanel = gp;
        map = new String[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        mapTile = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        tiles = new Tile[10];
        getTileImage();
        loadMap("levels/level.txt");
        // player = new Player(1, 1, gamePanel);
        // MoveEntities.add(player);
        //MoveEntities.add(new Balloom(5, 1, gamePanel));
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
        loadFile(filePath);
        for (int i = 0; i < gamePanel.maxWorldRow; i++) {
            for (int j = 0; j < gamePanel.maxWorldCol; j++) {
                mapTile[i][j] = 0;
                switch (map[i][j]) {
                    case "p": {
                        player = new Player(j, i, gamePanel);
                        MoveEntities.add(player);
                    }
                        break;
                    case "1": {
                        MoveEntities.add(new Balloom(j, i, gamePanel));
                    }
                        break;
                    case "2": {
                        MoveEntities.add(new Oneal(j, i, gamePanel));
                    }
                        break;
                    case "#": {
                        mapTile[i][j] = 1;
                    }
                        break;
                    case "*": {
                        bricks.add(new Brick(j, i, gamePanel));
                        mapTile[i][j] = 2;
                    }
                        break;
                }
            }
        }
    }

    public void loadFile(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));
            for (int i = 0; i < gamePanel.maxWorldRow; i++) {
                String line = br.readLine();
                map[i] = line.split("");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //update tile
    public void update() {
        if (gamePanel.getKeyHandler().spacePressed && gamePanel.nBombs > 0) {
            Bomb newBomb = player.makeBomb();
            if (newBomb != null) {
                bombs.add(newBomb);
                gamePanel.nBombs--;
            }
        }
        updateBomb();
        updateBrick();
        updateMoveEntity();

    }

    // update or delete moveEntity
    public void updateMoveEntity() {
        for (int i = 0; i < MoveEntities.size(); i++) {
            if (MoveEntities.get(i).isRemoved()) {
                MoveEntities.remove(i);
            } else {
                MoveEntities.get(i).update();
            }
        }
    }

    // update or delete brick
    public void updateBrick() {
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).isRemoved()) {
                bricks.remove(i);
            } else {
                bricks.get(i).update();
            }
        }
    }

    // update or delete bomb
    public void updateBomb() {
        for (int i = 0; i < bombs.size(); i++) {
            if (!bombs.get(i).isRemoved()) {
                bombs.get(i).update();
            } else {
                bombs.remove(i);
                gamePanel.nBombs++;
            }
        }
    }

    public void draw(Graphics2D g2) {
        // int worldCol = 0;
        // int worldRow = 0;
        // while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
        //     int worldX = worldCol * gamePanel.tileSize;
        //     int worldY = worldRow * gamePanel.tileSize;
        //     int screenX = worldX; // + gamePanel.player.worldX;
        //     int screenY = worldY; // + gamePanel.player.worldY;
        //     int tileNum = mapTile[worldRow][worldCol];
        //     g2.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        //     worldCol++;
        //     if (worldCol == gamePanel.maxWorldCol) {
        //         worldCol = 0;
        //         worldRow++;
        //     }
        // }
        for (int maxCol = 0; maxCol<gamePanel.maxWorldCol; maxCol++) {
            for (int maxRow = 0; maxRow<gamePanel.maxWorldRow; maxRow++) {
                int screenX = maxCol * gamePanel.tileSize;
                int screenY = maxRow * gamePanel.tileSize;
                int px = screenX - player.getScreenX() + player.px;
                int py = screenY - player.getScreenY() + player.py;

                //stop the camera at the edge
                if (player.px > player.getScreenX()) {
                    px = screenX;
                }
                if (player.py > player.getScreenY()) {
                    py = screenY;
                }
                int rightOffset = gamePanel.screenWidth - player.px;
                if (rightOffset > gamePanel.worldWidth - player.getScreenX()) {
                    px = gamePanel.screenWidth - (gamePanel.worldWidth - screenX);
                }
                int bottomOffset = gamePanel.screenHeight - player.py;
                if (bottomOffset > gamePanel.worldHeight - player.getScreenY()) {
                    py = gamePanel.screenHeight - (gamePanel.worldHeight - screenY);
                }
                int tile = mapTile[maxRow][maxCol];
                if (tile == 1) {
                    image = tiles[tile].image;
                } else {
                    image = tiles[0].image;
                }
                g2.drawImage(image, px, py, gamePanel.tileSize, gamePanel.tileSize, null);
                if (player.px  > player.getScreenX() || player.py > player.getScreenY() || rightOffset > gamePanel.worldWidth - player.getScreenX() || bottomOffset > gamePanel.worldHeight - player.getScreenY()) {
                    g2.drawImage(image, px, py, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }

        // for (int i=0; i<gamePanel.item.length; i++) {
        // if (gamePanel.item[i]!=null) {
        // gamePanel.item[i].draw(g2, gamePanel);
        // }
        // }
        for (Bomb bomb : bombs) {
            bomb.draw(g2);
        }
        // draw brick
        for (StaticEntity brick : bricks) {
            brick.draw(g2);
        }
        for (MoveEntity value : MoveEntities) {
            value.draw(g2);
        }
    }

}
