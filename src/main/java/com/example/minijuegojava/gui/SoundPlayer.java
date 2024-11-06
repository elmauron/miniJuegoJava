package com.example.minijuegojava.gui;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    public static void playSound(String soundFileName) {
        try {
            // Get the resource URL from the classpath
            URL soundFileUrl = SoundPlayer.class.getClassLoader().getResource("sonidos/" + soundFileName);
            if (soundFileUrl == null) {
                throw new IOException("Sound file not found: " + soundFileName);
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFileUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}