package Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Image.Image;
import Sound.Sound;

public class Menu implements MouseListener {
    GamePanel gamePanel;
    private final MouseHandler mouseHandler;
    private int index = 0;
    public boolean playSound;
    public boolean playSoundGameoverVictory;
    public String direction;
    private int indexGameover = 0;
    private boolean restart = false;

    public Menu(GamePanel gamePanel, MouseHandler mouseHandler) {
        this.gamePanel = gamePanel;
        this.mouseHandler = mouseHandler;
        direction = "StartGame";
        playSound = true;
        playSoundGameoverVictory = true;
    }

    public void update() {
        if (gamePanel.isGameOver() && direction != "StartGame") {
            direction = "Gameover";
            restart = true;
            if (playSoundGameoverVictory) {
                gamePanel.sound.stopAllSound();
                gamePanel.menu.playSound = true;
            }
            playSoundGameoverVictory = false;
        }
        if (gamePanel.isWinGame() && direction != "StartGame") {
            direction = "Victory";
            restart = true;
            if (playSoundGameoverVictory) {
                gamePanel.sound.stopAllSound();
                gamePanel.menu.playSound = true;
            }
            playSoundGameoverVictory = false;

        }
    }

    public void draw(Graphics2D g2) {
        switch (direction) {
            case "StartGame":
                if (playSound) {
                    gamePanel.sound.titleScreen.play();
                    gamePanel.sound.titleScreen.loop();
                }
                playSound = false;
                g2.drawImage(Image.backGroundGame, 0, 0, 768, 384, null);
                if (mouseHandler.pressed) {
                    if ("Start".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.start_game, 600, 280, 144, 24, null);
                        g2.drawImage(Image.howToPlay, 580, 310, 192, 33, null);
                        index = 1;
                    } else if ("HowToPlay".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.start_game, 580, 280, 192, 30, null);
                        g2.drawImage(Image.howToPlay, 600, 310, 144, 24, null);
                        index = 2;
                    }
                } else {
                    switch (index) {
                        case 1: {
                            gamePanel.sound.stopAllSound();
                            gamePanel.menu.playSound = true;
                            gamePanel.gameState = gamePanel.playState;
                            direction = "Background";
                            if (restart) {
                                gamePanel.setLevel(0);
                                gamePanel.nextLevel();
                                restart = false;
                            }
                        }
                            break;
                        case 2:
                            gamePanel.sound.stopAllSound();
                            gamePanel.menu.playSound = true;
                            direction = "HowToPlay";
                            break;
                    }
                    g2.drawImage(Image.start_game, 580, 280, 192, 30, null);
                    g2.drawImage(Image.howToPlay, 580, 310, 192, 33, null);
                    index = 0;
                }
                break;
            case "Coutinue":
                if (playSound) {
                    gamePanel.sound.titleScreen.play();
                    gamePanel.sound.titleScreen.loop();
                }
                playSound = false;
                g2.drawImage(Image.backGroundGame, 0, 0, 768, 384, null);
                if (mouseHandler.pressed) {
                    if ("Start".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.start_game, 600, 250, 144, 24, null);
                        g2.drawImage(Image.continueGame, 580, 280, 192, 30, null);
                        g2.drawImage(Image.howToPlay, 580, 310, 192, 33, null);
                        index = 1;
                    } else if ("Coutinue".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.start_game, 580, 250, 192, 32, null);
                        g2.drawImage(Image.continueGame, 600, 280, 144, 24, null);
                        g2.drawImage(Image.howToPlay, 580, 310, 192, 33, null);
                        index = 2;

                    } else if ("HowToPlay".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.start_game, 580, 250, 192, 32, null);
                        g2.drawImage(Image.continueGame, 580, 280, 192, 30, null);
                        g2.drawImage(Image.howToPlay, 600, 310, 144, 24, null);
                        index = 3;
                    }
                } else {
                    switch (index) {
                        case 1:
                            gamePanel.gameState = gamePanel.playState;
                            break;
                        case 2:
                            gamePanel.gameState = gamePanel.playState;
                            break;
                        case 3:
                            gamePanel.sound.stopAllSound();
                            gamePanel.menu.playSound = true;
                            direction = "HowToPlay";
                            break;
                    }
                    g2.drawImage(Image.start_game, 580, 250, 192, 32, null);
                    g2.drawImage(Image.continueGame, 580, 280, 192, 30, null);
                    g2.drawImage(Image.howToPlay, 580, 310, 192, 33, null);
                    index = 0;
                }
                break;
            case "HowToPlay":
                if (playSound) {
                    gamePanel.sound.titleScreen.play();
                    gamePanel.sound.titleScreen.loop();
                }
                playSound = false;
                g2.drawImage(Image.howToPlayPanel, 0, 0, 768, 384, null);
                if (mouseHandler.pressed) {
                    if ("Back".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.back, 620, 310, 107, 26, null);
                        index = 1;
                    }
                } else {
                    switch (index) {
                        case 1:
                            if (gamePanel.gameState != gamePanel.playState) {
                                gamePanel.sound.stopAllSound();
                                gamePanel.menu.playSound = true;
                                direction = "StartGame";
                            }
                            break;
                    }
                    g2.drawImage(Image.back, 600, 310, 143, 34, null);
                    index = 0;
                }
                break;
            case "Gameover":
                if (playSound) {
                    gamePanel.sound.gameOver.play();
                    gamePanel.sound.gameOver.loop();
                }
                playSound = false;
                g2.drawImage(Image.getBackGroundGameover, 0, 0, 768, 384, null);
                g2.drawImage(Image.gameover, 220, 150, 353, 57, null);
                if (mouseHandler.pressed) {
                    if ("Back".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.back, 337, 270, 129, 31, null);
                        index = 1;
                    }
                } else {
                    switch (index) {
                        case 1:
                            if (gamePanel.gameState != gamePanel.playState) {
                                gamePanel.sound.stopAllSound();
                                gamePanel.menu.playSound = true;
                                direction = "StartGame";
                                gamePanel.setGameOver(false);
                            }
                            break;
                    }
                    g2.drawImage(Image.back, 320, 270, 172, 41, null);
                    index = 0;
                }
                break;
            case "Victory":
                if (playSound) {
                    gamePanel.sound.victory.play();
                    gamePanel.sound.victory.loop();
                }
                playSound = false;
                g2.drawImage(Image.victory, 0, 0, 768, 384, null);
                if (mouseHandler.pressed) {
                    if ("Back".equals(mouseHandler.direction)) {
                        g2.drawImage(Image.back, 337, 270, 129, 31, null);
                        index = 1;
                    }
                } else {
                    switch (index) {
                        case 1:
                            if (gamePanel.gameState != gamePanel.playState) {
                                gamePanel.sound.stopAllSound();
                                gamePanel.menu.playSound = true;
                                direction = "StartGame";
                                gamePanel.setWinGame(false);
                            }
                            break;
                    }
                    g2.drawImage(Image.back, 320, 270, 172, 41, null);
                    index = 0;
                }
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}