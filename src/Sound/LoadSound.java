package Sound;

import javax.sound.sampled.*;
import java.io.IOException;

public class LoadSound {
    Clip clip;

    public LoadSound(String path) {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {

        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
