package Image;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

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
    public static final BufferedImage player_dead1 = (new LoadImage("images/player_dead_1.png")).getImage();
    public static final BufferedImage player_dead2 = (new LoadImage("images/player_dead_2.png")).getImage();
    public static final BufferedImage player_dead3 = (new LoadImage("images/player_dead_3.png")).getImage();

    public static final BufferedImage balloom_left = (new LoadImage("images/balloom_left.png")).getImage(); 
    public static final BufferedImage balloom_left1 = (new LoadImage("images/balloom_left_1.png")).getImage(); 
    public static final BufferedImage balloom_left2 = (new LoadImage("images/balloom_left_2.png")).getImage();
    public static final BufferedImage balloom_right = (new LoadImage("images/balloom_right.png")).getImage(); 
    public static final BufferedImage balloom_right1 = (new LoadImage("images/balloom_right_1.png")).getImage(); 
    public static final BufferedImage balloom_right2 = (new LoadImage("images/balloom_right_2.png")).getImage();
    public static final BufferedImage balloom_dead = (new LoadImage("images/balloom_dead.png")).getImage();
    
    public static final BufferedImage oneal_left = (new LoadImage("images/oneal_left1.png")).getImage();
    public static final BufferedImage oneal_left1 = (new LoadImage("images/oneal_left2.png")).getImage();
    public static final BufferedImage oneal_left2 = (new LoadImage("images/oneal_left3.png")).getImage();
    public static final BufferedImage oneal_right = (new LoadImage("images/oneal_right1.png")).getImage();
    public static final BufferedImage oneal_right1 = (new LoadImage("images/oneal_right2.png")).getImage();
    public static final BufferedImage oneal_right2 = (new LoadImage("images/oneal_right3.png")).getImage();
    public static final BufferedImage oneal_dead = (new LoadImage("images/oneal_dead.png")).getImage();
    
    public static final BufferedImage doll_left = (new LoadImage("images/doll_left1.png")).getImage();
    public static final BufferedImage doll_left1 = (new LoadImage("images/doll_left2.png")).getImage();
    public static final BufferedImage doll_left2 = (new LoadImage("images/doll_left3.png")).getImage();
    public static final BufferedImage doll_right = (new LoadImage("images/doll_right1.png")).getImage();
    public static final BufferedImage doll_right1 = (new LoadImage("images/doll_right2.png")).getImage();
    public static final BufferedImage doll_right2 = (new LoadImage("images/doll_right3.png")).getImage();
    public static final BufferedImage doll_dead = (new LoadImage("images/doll_dead.png")).getImage();
    
    public static final BufferedImage minvo_left = (new LoadImage("images/minvo_left1.png")).getImage();
    public static final BufferedImage minvo_left1 = (new LoadImage("images/minvo_left2.png")).getImage();
    public static final BufferedImage minvo_left2 = (new LoadImage("images/minvo_left3.png")).getImage();
    public static final BufferedImage minvo_right = (new LoadImage("images/minvo_right1.png")).getImage();
    public static final BufferedImage minvo_right1 = (new LoadImage("images/minvo_right2.png")).getImage();
    public static final BufferedImage minvo_right2 = (new LoadImage("images/minvo_right3.png")).getImage();
    public static final BufferedImage minvo_dead = (new LoadImage("images/minvo_dead.png")).getImage();
    
    public static final BufferedImage mob_dead1 = (new LoadImage("images/mob_dead1.png")).getImage();
    public static final BufferedImage mob_dead2 = (new LoadImage("images/mob_dead2.png")).getImage();
    public static final BufferedImage mob_dead3 = (new LoadImage("images/mob_dead3.png")).getImage();
    
    public static final BufferedImage explosion_left = (new LoadImage("images/explosion_horizontal_left_last.png")).getImage();
    public static final BufferedImage explosion_left1 = (new LoadImage("images/explosion_horizontal_left_last1.png")).getImage();
    public static final BufferedImage explosion_left2 = (new LoadImage("images/explosion_horizontal_left_last2.png")).getImage();
    public static final BufferedImage explosion_right = (new LoadImage("images/explosion_horizontal_right_last.png")).getImage();
    public static final BufferedImage explosion_right1 = (new LoadImage("images/explosion_horizontal_right_last1.png")).getImage();
    public static final BufferedImage explosion_right2 = (new LoadImage("images/explosion_horizontal_right_last2.png")).getImage();
    public static final BufferedImage explosion_horizontal = (new LoadImage("images/explosion_horizontal.png")).getImage();
    public static final BufferedImage explosion_horizontal1 = (new LoadImage("images/explosion_horizontal1.png")).getImage();
    public static final BufferedImage explosion_horizontal2 = (new LoadImage("images/explosion_horizontal2.png")).getImage();

    public static final BufferedImage explosion_down = (new LoadImage("images/explosion_vertical_down_last.png")).getImage();
    public static final BufferedImage explosion_down1 = (new LoadImage("images/explosion_vertical_down_last1.png")).getImage();
    public static final BufferedImage explosion_down2 = (new LoadImage("images/explosion_vertical_down_last2.png")).getImage();
    public static final BufferedImage explosion_top = (new LoadImage("images/explosion_vertical_top_last.png")).getImage();
    public static final BufferedImage explosion_top1 = (new LoadImage("images/explosion_vertical_top_last1.png")).getImage();
    public static final BufferedImage explosion_top2 = (new LoadImage("images/explosion_vertical_top_last2.png")).getImage();
    public static final BufferedImage explosion_vertical = (new LoadImage("images/explosion_vertical.png")).getImage();
    public static final BufferedImage explosion_vertical1 = (new LoadImage("images/explosion_vertical1.png")).getImage();
    public static final BufferedImage explosion_vertical2 = (new LoadImage("images/explosion_vertical2.png")).getImage();

    public static final BufferedImage bomb_exploded = (new LoadImage("images/bomb_exploded.png")).getImage();
    public static final BufferedImage bomb_exploded1 = (new LoadImage("images/bomb_exploded1.png")).getImage();
    public static final BufferedImage bomb_exploded2 = (new LoadImage("images/bomb_exploded2.png")).getImage();
    public static final BufferedImage bomb = (new LoadImage("images/bomb.png")).getImage();
    public static final BufferedImage bomb1 = (new LoadImage("images/bomb_1.png")).getImage();
    public static final BufferedImage bomb2 = (new LoadImage("images/bomb_2.png")).getImage();
    
    public static final BufferedImage flashItem = (new LoadImage("images/powerup_wallpass.png")).getImage();
    public static final BufferedImage Portal = (new LoadImage("images/portal.png")).getImage();
    public static final BufferedImage bombItem = (new LoadImage("images/powerup_bombs.png")).getImage();
    public static final BufferedImage speedItem = (new LoadImage("images/powerup_speed.png")).getImage();
    public static final BufferedImage flameItem = (new LoadImage("images/powerup_flames.png")).getImage();
    public static final BufferedImage flamePassItem = (new LoadImage("images/powerup_flamepass.png")).getImage();
    
    public static final BufferedImage brick = (new LoadImage("images/brick.png")).getImage();
    public static final BufferedImage brick_exploded = (new LoadImage("images/brick_exploded.png  ")).getImage();
    public static final BufferedImage brick_exploded1 = (new LoadImage("images/brick_exploded1.png")).getImage();
    public static final BufferedImage brick_exploded2 = (new LoadImage("images/brick_exploded2.png")).getImage();

    public static final BufferedImage grass = (new LoadImage("images/grass.png")).getImage();
    public static final BufferedImage wall = (new LoadImage("images/wall.png")).getImage();
    public static final BufferedImage portal = (new LoadImage("images/portal.png")).getImage();

    public static final BufferedImage backGroundGame = (new LoadImage("images/Bomberman.png")).getImage();
    public static final BufferedImage continueGame = (new LoadImage("images/Continue.png")).getImage();
    public static final BufferedImage howToPlay = (new LoadImage("images/HowToPlay.png")).getImage();
    public static final BufferedImage start_game = (new LoadImage("images/start_game.png")).getImage();
    public static final BufferedImage back = (new LoadImage("images/back.png")).getImage();
    public static final BufferedImage howToPlayPanel = (new LoadImage("images/howToPlayPanel.png")).getImage();
    public static final BufferedImage getBackGroundGameover = (new LoadImage("images/GameoverBackground.png")).getImage();
    public static final BufferedImage gameover = (new LoadImage("images/Gameover.png")).getImage();
    public static final BufferedImage victory = (new LoadImage("images/Victory.png")).getImage();

    public static final BufferedImage kondoria_dead = (new LoadImage("images/kondoria_dead.png")).getImage();
    public static final BufferedImage kondoria_left1 = (new LoadImage("images/kondoria_left1.png")).getImage();
    public static final BufferedImage kondoria_left2 = (new LoadImage("images/kondoria_left2.png")).getImage();
    public static final BufferedImage kondoria_left3 = (new LoadImage("images/kondoria_left3.png")).getImage();
    public static final BufferedImage kondoria_right1 = (new LoadImage("images/kondoria_right1.png")).getImage();
    public static final BufferedImage kondoria_right2 = (new LoadImage("images/kondoria_right2.png")).getImage();
    public static final BufferedImage kondoria_right3 = (new LoadImage("images/kondoria_right3.png")).getImage();

}