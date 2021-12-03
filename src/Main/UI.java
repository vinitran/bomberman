package Main;

import java.awt.*;

public class UI {
    GamePanel gamePanel;
    Font aria;
    public boolean messageOn = false;
    public String Message = "";
    int messageTime = 0;
    public boolean gameFinished = false;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        aria = new Font("Arial", Font.PLAIN, 30);
    }

    public void ShowMessage(String text) {
        Message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(aria);
            g2.setColor(Color.DARK_GRAY);
            String text;
            int textLength;
            text = "YOU HAVE PASSED";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x  = gamePanel.worldWidth/2 - textLength/2;
            int y = gamePanel.worldHeight/2 - gamePanel.tileSize*3;
            g2.drawString(text, x, y);
            gamePanel.gameThread = null;
        }
        else {
            g2.setFont(aria);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString("LIFE", 24, 24);
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(Message, gamePanel.tileSize*25, gamePanel.tileSize*1);
                messageTime++;
                if (messageTime>120) {
                    messageTime = 0;
                    messageOn = false;
                }
            }
        }

    }
}
