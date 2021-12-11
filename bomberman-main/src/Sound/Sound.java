package Sound;

import javax.xml.transform.Source;

public class Sound {
    public final LoadSound titleScreen;
    public final LoadSound item;
    public final LoadSound gameOver;
    public final LoadSound levelComplete;
    public final LoadSound findDoor;
    public final LoadSound bonusStage;
    public final LoadSound stageTheme;
    public final LoadSound bom;
    public final LoadSound makeBom;
    public final LoadSound click;
    public final LoadSound victory;



    public Sound() {
        titleScreen = new LoadSound("Sound/TitleScreen.wav");
        item = new LoadSound("Sound/item.wav");
        gameOver = new LoadSound("Sound/GameOver.wav");
        levelComplete = new LoadSound("Sound/LevelComplete.wav");
        findDoor = new LoadSound("Sound/FindDoor.wav");
        bonusStage = new LoadSound("Sound/BonusStage.wav");
        stageTheme = new LoadSound("Sound/StageTheme.wav");
        bom = new LoadSound("Sound/BOM_11_L.wav");
        makeBom = new LoadSound("Sound/Link bonuslife.wav");
        click = new LoadSound("Sound/Link bonuslife.wav");
        victory = new LoadSound("Sound/EndingTheme.wav");
    }
    public void stopAllSound() {
        titleScreen.stop();
        item.stop();
        gameOver.stop();
        levelComplete.stop();
        findDoor.stop();
        bonusStage.stop();
        stageTheme.stop();
        bom.stop();
        makeBom.stop();
        victory.stop();
    }
}
