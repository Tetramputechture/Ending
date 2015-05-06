package ending.util;

import ending.vector.Vector2f;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
/**
 *
 * @author Nick
 */
public class SpriteUtils {
    
    public static Vector2f getTextureCenter(Texture t) {
        return new Vector2f(t.getSize()).scl(0.5f);
    }
    
    public static void setOriginAtCenter(Sprite s, Texture t) {
        s.setOrigin(getTextureCenter(t).toVector2f());
    }
    
}
