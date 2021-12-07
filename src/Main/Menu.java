package Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Image.Image;
public class Menu implements MouseListener{
    GamePanel gamePanel;
    private MouseHandler mouseHandler;
    private double scale;
    private int index = 0;
    public Menu(GamePanel gamePanel, MouseHandler mouseHandler) {
        this.gamePanel = gamePanel;
        this.mouseHandler = mouseHandler;
        scale = 1;
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(Image.backGroundGame,0,0,768,384,null);
        if (mouseHandler.pressed) {
            if ("Start".equals(mouseHandler.direction)) {
                g2.drawImage(Image.start_game,600,250,144,24,null);
                g2.drawImage(Image.continueGame,580,280,192,30,null);
                g2.drawImage(Image.howToPlay,580,310,192,33,null);
                index = 1;
            } else if ("Coutinue".equals(mouseHandler.direction)) {
                g2.drawImage(Image.start_game,580,250,192,32,null);
                g2.drawImage(Image.continueGame,600,280,144,24,null);
                g2.drawImage(Image.howToPlay,580,310,192,33,null);
                index = 2;

            } else if ("HowToPlay".equals(mouseHandler.direction)) {
                g2.drawImage(Image.start_game,580,250,192,32,null);
                g2.drawImage(Image.continueGame,580,280,192,30,null);
                g2.drawImage(Image.howToPlay,600,310,144,24,null);
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

            }
            g2.drawImage(Image.start_game,580,250,192,32,null);
            g2.drawImage(Image.continueGame,580,280,192,30,null);
            g2.drawImage(Image.howToPlay,580,310,192,33,null);
            index = 0;

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
