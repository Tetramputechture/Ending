package ending.animation;

import java.util.ArrayList;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;

/**
 * Handles animation.
 * Major credit to Maximilian Wagenbach (aka. Foaly) (foaly.f@web.de) for his
 * SFML AnimatedSprite source code (this is that, just ported to JSFML).
 * @author Nick
 */
public class Animation {
    
    private final ArrayList<IntRect> frames;
    
    private Texture texture;
    
    public Animation() {
        frames = new ArrayList<>();
        texture = new Texture();
    }
    
    public void addFrame(IntRect rect) {
        frames.add(rect);
    }
    
    public void setSpriteSheet(Texture texture) {
        this.texture = texture;
    }
    
    public Texture getSpriteSheet() {
        return texture;
    }
    
    public int getFrameCount() {
        return frames.size();
    }
    
    public IntRect getFrame(int frameNumber) {
        return frames.get(frameNumber);
    }
}
