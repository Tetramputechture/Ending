package ending.util;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.Font;

/**
 *
 * @author Nick
 */
public final class Fonts {
    
    public static final Font quicksandLight = loadFont("fonts/Quicksand-Light.ttf");
    
    public static final Font quicksandRegular = loadFont("fonts/Quicksand-Regular.ttf");
    
    public static final Font quicksandBold = loadFont("fonts/Quicksand-Bold.ttf");
    
    private static Font loadFont(String filename) {
        Font f = new Font();
        try {
            f.loadFromFile(Paths.get(filename));
        } catch (IOException ex) {
            System.out.println("Font file " + filename + " not found! Returning null..");
            return null;
        }
        return f;
    }
    
}
