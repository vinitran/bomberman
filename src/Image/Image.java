package Image;

import java.awt.image.BufferedImage;

public class Image {
    public static final BufferedImage player_up = (new LoadImage("images/player_up.png")).getImage();
    public static final BufferedImage player_up1 = (new LoadImage("images/player_up_1.png")).getImage();
    public static final BufferedImage player_up2 = (new LoadImage("images/player_up_2.png")).getImage();

    public static final BufferedImage player_down = (new LoadImage("images/player_down.png")).getImage(); 
    public static final BufferedImage player_down1 = (new LoadImage("images/player_down_1.png")).getImage(); 
    public static final BufferedImage player_down2 = (new LoadImage("images/player_down_2.png")).getImage();

    public static final BufferedImage player_right = (new LoadImage("images/player_right.png")).getImage(); 
    public static final BufferedImage player_right1 = (new LoadImage("images/player_right_1.png")).getImage(); 
    public static final BufferedImage player_right2 = (new LoadImage("images/player_right_2.png")).getImage();

    public static final BufferedImage player_left = (new LoadImage("images/player_left.png")).getImage(); 
    public static final BufferedImage player_left1 = (new LoadImage("images/player_left_1.png")).getImage(); 
    public static final BufferedImage player_left2 = (new LoadImage("images/player_left_2.png")).getImage();

    public static final BufferedImage player_stand = (new LoadImage("images/player_stand.png")).getImage(); 
    public static final BufferedImage player_stand1 = (new LoadImage("images/player_stand_1.png")).getImage(); 
    public static final BufferedImage player_stand2 = (new LoadImage("images/player_stand_2.png")).getImage();
}