package ending.animation;

import java.util.ArrayList;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Handles animation. Major credit to Maximilian Wagenbach (aka. Foaly)
 * (foaly.f@web.de) for his SFML AnimatedSprite source code (this is that, just
 * ported to JSFML).
 *
 * @author Nick
 */
public class Animation {

    private final ArrayList<IntRect> frames;

    private final ArrayList<IntRect> frameBounds;

    private Texture texture;

    public Animation() {
        frames = new ArrayList<>();
        frameBounds = new ArrayList<>();
        texture = new Texture();
    }

    public Animation(Texture spriteSheet) {
        this();
        this.texture = spriteSheet;
    }

    public void addFrame(IntRect rect) {
        frames.add(rect);
        frameBounds.add(calculateBounds(rect));
    }

    public IntRect calculateBounds(IntRect rect) {

        float width = Math.abs(rect.width) + rect.left;
        float height = Math.abs(rect.height) + rect.top;

        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;

        Image img = texture.copyToImage();

        for (int x = rect.left; x < width; x++) {
            for (int y = rect.top; y < height; y++) {
                if (img.getPixel(x, y).a != 0) {
                    if (x < minX) {
                        minX = x - rect.left;
                    } else if (x > maxX) {
                        maxX = x - rect.left;
                    }
                    if (y < minY) {
                        minY = y;
                    } else if (y > maxY) {
                        maxY = y;
                    }
                }
            }
        }

        minY -= rect.top;
        maxY -= rect.top;

        return new IntRect(minX, minY, maxX - minX + 1, maxY - minY + 1);
    }

    public void setSpriteSheet(Texture spriteSheet) {
        this.texture = spriteSheet;
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
    
    public IntRect getFrameBounds(int frameNumber) {
        return frameBounds.get(frameNumber);
    }
}
