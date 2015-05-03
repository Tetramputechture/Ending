package ending.util;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Text;

/**
 *
 * @author Nick
 */
public final class TextUtils {
    
    public static void setOriginAtCenter(Text t) { 
        FloatRect textbounds = t.getLocalBounds();
        t.setOrigin(textbounds.left + textbounds.width / 2.0f,
                textbounds.top + textbounds.height / 2.0f);
    }
    
}
