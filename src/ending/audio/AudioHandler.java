package ending.audio;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

/**
 * Handles all game sounds.
 *
 * @author Nick
 */
public final class AudioHandler {
    
    private final Sound sound;
    
    /**
     * Constructs a new Sound object to hold the SoundBuffer.
     */
    public AudioHandler() {
        sound = new Sound();
    }
    
    /**
     * Plays a sound with specified filename and pitch.
     *
     * @param filename the filename of the sound to be played
     * @param pitch the pitch of the played sound
     */
    public void playSound(String filename, float pitch) {

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
}
