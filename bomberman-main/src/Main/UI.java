package Main;

import java.awt.*;

public class UI {
    GamePanel gamePanel;
    Font aria;
    public boolean messageOn = false;
    public String Message = "";
    int messageTime = 0;
    public boolean gameFinished = false;
    private boolean nextLevel = false;
    private int timeNextLevel = 80;
    private String text = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        aria = new Font("Arial", Font.PLAIN, 30);
    }

    public void ShowMessage(String text) {
        Message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (nextLevel) {
            if (timeNextLevel > 0) {
                g2.setFont(aria);
                g2.setColor(Color.YELLOW);
                int textLength;
                text = "Level " + gamePanel.getLevel();
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                int x = gamePanel.screenWidth / 2 - textLength / 2;
                int y = gamePanel.screenHeight / 2 - gamePanel.tileSize * 3;
                g2.drawString(text, x, y);
                timeNextLevel--;
            } else {
                timeNextLevel = 80;
                nextLevel = false;
            }
        }
        if (gameFinished) {
            g2.setFont(aria);
            g2.setColor(Color.DARK_GRAY);
            String text;
            int textLength;
            text = "YOU HAVE PASSED";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gamePanel.screenWidth / 2 - textLength / 2;
            int y = gamePanel.screenHeight / 2 - gamePanel.tileSize * 3;
            g2.drawString(text, x, y);
            System.out.println("Game over");
            gamePanel.gameThread = null;
        }
        // else {
        // g2.setFont(aria);
        // g2.setColor(Color.DARK_GRAY);
        // g2.drawString("LIFE", 24, 24);
        // if (messageOn) {
        // g2.setFont(g2.getFont().deriveFont(30F));
        // g2.drawString(Message, gamePanel.tileSize*25, gamePanel.tileSize*1);
        // messageTime++;
        // if (messageTime>120) {
        // messageTime = 0;
        // messageOn = false;
        // }
        // }
        // }


    }
    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }
}
