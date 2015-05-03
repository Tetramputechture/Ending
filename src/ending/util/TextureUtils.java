package ending.util;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.Texture;

/**
 *
 * @author Nick
 */
public final class TextureUtils {

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
