package ending.util;

import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Text;

/**
 * Handles all Fonts and Text utilities.
 * @author Nick
 */
public final class TextUtils {
    
    /**
     * The Quicksand Light font.
     */
    public static final Font quicksandLight = loadFont("fonts/Quicksand-Light.ttf");
    
    /**
     * The Quicksand Regular font.
     */
    public static final Font quicksandRegular = loadFont("fonts/Quicksand-Regular.ttf");
    
    /**
     * The Quicksand Bold font.
     */
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
    
    /**
     * Sets the origin of a Text object at its center.
     * @param t the Text to set the origin of.
     */
    public static void setOriginAtCenter(Text t) { 
        FloatRect textbounds = t.getLocalBounds();
        t.setOrigin(textbounds.left + textbounds.width / 2.0f,
                textbounds.top + textbounds.height / 2.0f);
    }
    
}
