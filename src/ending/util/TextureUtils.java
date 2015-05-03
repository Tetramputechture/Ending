package ending.util;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.Texture;

/**
 * Handles all Texture utilities.
 * @author Nick
 */
public final class TextureUtils {

    /**
     * Loads a texture from a file.
     * @param filename the filename of the texture.
     * @return a new Texture based on the filename's image; null if file not found.
     */
    public static Texture loadTexture(String filename) {
        Texture t = new Texture();
        try {
            t.loadFromFile(Paths.get(filename));
        } catch (IOException ex) {
            System.out.println("Image " + filename + " not found! Returning null...");
            return null;
        }
        return t;
    }
}
