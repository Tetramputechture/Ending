package ending.audio;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.audio.Music;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

/**
 * Handles all game sounds.
 *
 * @author Nick
 */
public final class AudioHandler {
    
    private static final Sound sound = new Sound();
    
    /**
     * Plays a sound with specified filename and pitch.
     *
     * @param filename the filename of the sound to be played
     * @param pitch the pitch of the played sound
     */
    public static void playSound(String filename, float pitch) {

        SoundBuffer sBuffer = new SoundBuffer();

        try {
            sBuffer.loadFromFile(Paths.get(filename));
        } catch (IOException ex) {
            System.out.println("Sound file " + filename + " not found! Returning...");
        }

        sound.setBuffer(sBuffer);

        sound.setPitch(pitch);

        sound.play();
    }
    
    public static Music loadMusic(String filename) {
        
        Music music = new Music();
        try {
            music.openFromFile(Paths.get(filename));
        } catch (IOException ex) {
            Logger.getLogger(AudioHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return music;
    }
}
