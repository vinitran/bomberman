package Main;

import Sound.Sound;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Handler;

public class MouseHandler implements MouseListener {
    GamePanel gamePanel;
    public String direction;
    public boolean pressed;
    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        double xPos = e.getX();
        double yPos = e.getY();
        switch (gamePanel.menu.direction) {
            case "StartGame":
                if (xPos > 580 && xPos < (580 + 192)) {
                    if (yPos > 280 && yPos < 310) {
                        direction = "Start";
                    } else if (yPos > 310 && yPos < 340) {
                        direction = "HowToPlay";
                    }
                    pressed = true;
                } else {
                    direction = "Background";
                    pressed = false;
                }
                break;
            case "Gameover":
                if (xPos > 320 && xPos < (320 + 172)
                    && yPos > 270 && yPos < (270 + 41)) {
                direction = "Back";
                pressed = true;
                } else {
                direction = "Background";
                pressed = false;
                }
                break;
            case "Victory":
                if (xPos > 320 && xPos < (320 + 172)
                    && yPos > 270 && yPos < (270 + 41)) {
                direction = "Back";
                pressed = true;
                } else {
                direction = "Background";
                pressed = false;
                }
                break;    
            case "Coutinue":
                if (xPos > 580 && xPos < (580 + 192)) {
                    if (yPos > 250 && yPos < 280) {
                        direction = "Start";
                    } else if (yPos > 280 && yPos < 310) {
                        direction = "Coutinue";
                    } else if (yPos > 310 && yPos < 340) {
                        direction = "HowToPlay";
                    }
                    pressed = true;
                } else {
                    direction = "Background";
                    pressed = false;
                }
                break;
            case "HowToPlay":
                if (xPos > 600 && xPos < (600 + 143)
                        && yPos > 310 && yPos < 310+34) {
                    direction = "Back";
                    pressed = true;
                } else {
                    direction = "Background";
                    pressed = false;
                }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double xPos = e.getX();
        double yPos = e.getY();
        switch (gamePanel.menu.direction) {
            case "Coutinue":
                if (xPos > 580 && xPos < (580 + 192)) {
                    if (yPos > 250 && yPos < 280) {
                        gamePanel.sound.click.play();
                        direction = "Start";
                    } else if (yPos > 280 && yPos < 310) {
                        gamePanel.sound.click.play();
                        direction = "Coutinue";
                    } else if (yPos > 310 && yPos < 340) {
                        gamePanel.sound.click.play();
                        direction = "HowToPlay";
                    }
                    pressed = false ;
                } else {
                    direction = "background";
                    pressed = false;
                }
                break;
            case "HowToPlay":
                if (xPos > 600 && xPos < (600 + 143)
                        && yPos > 310 && yPos < 310+34) {
                    gamePanel.sound.click.play();
                    direction = "Back";
                    pressed = false;
                } else {
                    direction = "Background";
                    pressed = false;
                }
                break;
            case "Gameover":
                if (xPos > 320 && xPos < (320 + 172)
                        && yPos > 270 && yPos < (270 + 41)) {
                    gamePanel.sound.click.play();
                    direction = "Back";
                    pressed = false;
                } else {
                    direction = "Background";
                    pressed = false;
                }
                break;
            case "Victory":
                if (xPos > 320 && xPos < (320 + 172)
                        && yPos > 270 && yPos < (270 + 41)) {
                    gamePanel.sound.click.play();
                    direction = "Back";
                    pressed = false;
                } else {
                    direction = "Background";
                    pressed = false;
                }
                break;
            case "StartGame":
                if (xPos > 580 && xPos < (580 + 192)) {
                    if (yPos > 280 && yPos < 310) {
                        gamePanel.sound.click.play();
                        direction = "Start";
                    } else if (yPos > 310 && yPos < 340) {
                        gamePanel.sound.click.play();
                        direction = "HowToPlay";
                    }
                    pressed = false;
                } else {
                    direction = "Background";
                    pressed = false;
                }
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
