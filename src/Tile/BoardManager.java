package Tile;

import Main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Entities.MoveEntity.enemy.*;
import Entities.StaticEntity.*;
import Entities.StaticEntity.Item.*;
import Entities.MoveEntity.*;

public class BoardManager {
    GamePanel gamePanel;
    public boolean[] tiles;
    public String[][] map;
    public int[][] mapTile;
    public List<MoveEntity> moveEntities = new ArrayList<>();
    public List<StaticEntity> staticEntities = new ArrayList<>();
    public List<Bomb> bombs = new LinkedList<>();
    public Player player;

    public BoardManager(GamePanel gp) {
        this.gamePanel = gp;
        map = new String[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        mapTile = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        tiles = new boolean[5];
        getTile();
    }

    public void startGame() {
        moveEntities.clear();
        staticEntities.clear();
        bombs.clear();
        loadLevel();
    }

    public void getTile() {
        tiles[0] = false; // Grass tile
        tiles[1] = true; // Wall tile
        tiles[2] = true; // Brick tile
        tiles[3] = false; // item
        tiles[4] = true; // Brick tile have item
    }

    public void loadMap(String filePath) {
        loadFile(filePath);
        for (int i = 0; i < gamePanel.maxWorldRow; i++) {
            for (int j = 0; j < gamePanel.maxWorldCol; j++) {
                mapTile[i][j] = 0;
                if (!map[i][j].equals("#")) {
                    staticEntities.add(new Grass(j, i, gamePanel));
                }
                switch (map[i][j]) {
                    case "p": {
                        player = new Player(j, i, gamePanel);
                        moveEntities.add(player);
                    }
                        break;
                    case "1": {
                        moveEntities.add(new Balloom(j, i, gamePanel));
                    }
                        break;
                    case "2": {
                        moveEntities.add(new Oneal(j, i, gamePanel));
                    }
                        break;
                    case "3": {
                        moveEntities.add(new Doll(j, i, gamePanel));
                    }
                        break;
                    case "4": {
                        moveEntities.add(new Minvo(j, i, gamePanel));
                    }
                        break;
                    case "5": {
                        moveEntities.add(new Kondoria(j, i, gamePanel));
                    }
                        break;
                    case "x": {
                        staticEntities.add(new Portal(j, i, gamePanel));
                        staticEntities.add(new Brick(j, i, gamePanel));
                        mapTile[i][j] = 4;
                    }
                        break;
                    case "b": {
                        staticEntities.add(new BombItem(j, i, gamePanel));
                        staticEntities.add(new Brick(j, i, gamePanel));
                        mapTile[i][j] = 4;
                    }
                        break;
                    case "f": {
                        staticEntities.add(new FlameItem(j, i, gamePanel));
                        staticEntities.add(new Brick(j, i, gamePanel));
                        mapTile[i][j] = 4;
                    }
                        break;
                    case "s": {
                        staticEntities.add(new SpeedItem(j, i, gamePanel));
                        staticEntities.add(new Brick(j, i, gamePanel));
                        mapTile[i][j] = 4;
                    }
                        break;
                    case "t": {
                        staticEntities.add(new FlashItem(j, i, gamePanel));
                        staticEntities.add(new Brick(j, i, gamePanel));
                        mapTile[i][j] = 4;
                    }
                        break;
                    case "#": {
                        staticEntities.add(new Wall(j, i, gamePanel));
                        mapTile[i][j] = 1;
                    }
                        break;
                    case "*": {
                        staticEntities.add(new Brick(j, i, gamePanel));
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

    // update tile
    public void update() {
        if (gamePanel.getKeyHandler().spacePressed && gamePanel.nBombs > 0) {
            Bomb newBomb = player.makeBomb();
            if (newBomb != null) {
                bombs.add(newBomb);
                gamePanel.nBombs--;
            }
        }
        updateBomb();
        updateStaticEntities();
        updateMoveEntity();

    }

    // update or delete moveEntity
    public void updateMoveEntity() {
        for (int i = 0; i < moveEntities.size(); i++) {
            if (moveEntities.get(i).isRemoved()) {
                moveEntities.remove(i);
            } else {
                moveEntities.get(i).update();
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

    // update or delete brick
    public void updateStaticEntities() {
        for (int i = 0; i < staticEntities.size(); i++) {
            if (staticEntities.get(i).isRemoved()) {
                staticEntities.remove(i);
            } else {
                staticEntities.get(i).update();
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (StaticEntity staticEntity : staticEntities) {
            staticEntity.draw(g2);
        }

        for (Bomb bomb : bombs) {
            bomb.draw(g2);
        }

        for (MoveEntity value : moveEntities) {
            value.draw(g2);
        }
    }

    public void addMoveEntities(MoveEntity moveEntity) {
        moveEntities.add(moveEntity);
    }

    public void loadLevel() {
        String filePath = "levels/level" + gamePanel.getLevel() + ".txt";
        loadMap(filePath);
    }

}
