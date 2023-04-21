package audio;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SoundManager extends JFrame {

    public static SoundManager main_bgm = new SoundManager("main_bgm.wav");
    public static SoundManager title_screen = new SoundManager("title_screen.wav");
    public static SoundManager collect_item = new SoundManager("collect_item.wav");
    public static SoundManager powerup_get = new SoundManager("powerup_get.wav");
    public static SoundManager place_bomb = new SoundManager("place_bomb.wav");
    public static SoundManager died = new SoundManager("died.wav");
    public static SoundManager move = new SoundManager("move.wav");
    public static SoundManager explosion = new SoundManager("explosion.wav");
    public static SoundManager win = new SoundManager("stage_clear.wav");
    public static SoundManager ending = new SoundManager("miss.wav");
    String soundFile;
    private Clip clip;

    public SoundManager(String soundFile) {
        this.soundFile = soundFile;
        try {
            File f = new File("Bomberman/bomberman/res/sound/" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public static void main(String[] args) {
        main_bgm.play();
    }
}
