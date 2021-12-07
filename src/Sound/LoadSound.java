package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class LoadSound {
    Clip clip;
    URL soundURL[] = new URL[1];
    public LoadSound(String path) {
        soundURL[0] = getClass().getResource(path);

    }
    public void play() {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[0]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {

        }
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
