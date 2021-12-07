package Main;

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
            direction = "background";
            pressed = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double xPos = e.getX();
        double yPos = e.getY();
        if (xPos > 580 && xPos < (580 + 192)) {
            if (yPos > 250 && yPos < 280) {
                direction = "Start";
            } else if (yPos > 280 && yPos < 310) {
                direction = "Coutinue";
            } else if (yPos > 310 && yPos < 340) {
                direction = "HowToPlay";
            }
            pressed = false ;
        } else {
            direction = "background";
            pressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
