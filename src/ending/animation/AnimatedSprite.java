package ending.animation;

import org.jsfml.graphics.BasicTransformable;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.Transform;
import org.jsfml.graphics.Vertex;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;

/**
 * Handles animated sprites. Major credit to Maximilian Wagenbach (aka. Foaly)
 * (foaly.f@web.de) for his SFML AnimatedSprite source code (this is that, just
 * ported to JSFML).
 *
 * @author Nick
 */
public class AnimatedSprite extends BasicTransformable implements Drawable {

    private Animation animation;

    private Time frameTime;

    private Time currentTime;

    private int currentFrame;

    private boolean isPaused;

    private boolean isLooped;

    private Texture texture;

    private final Vertex[] vertices;
    
    private int minX, maxX, minY, maxY;
    private int height;

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public AnimatedSprite(Time frameTime, boolean paused, boolean looped) {
        this.frameTime = frameTime;
        isPaused = paused;
        isLooped = looped;
        vertices = new Vertex[4];
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
        texture = animation.getSpriteSheet();
        currentFrame = 0;
        setFrame(currentFrame, true);
    }

    public void setFrameTime(Time time) {
        this.frameTime = time;
    }

    public void play() {
        isPaused = false;
    }

    public void play(Animation animation) {
        if (getAnimation() != animation) {
            setAnimation(animation);
        }
        play();
    }

    public void pause() {
        isPaused = true;
    }

    public void stop() {
        isPaused = true;
        currentFrame = 0;
        setFrame(currentFrame, true);
    }

    public void setLooped(boolean looped) {
        this.isLooped = looped;
    }

    public Animation getAnimation() {
        return animation;
    }

    public FloatRect getLocalBounds() {
        IntRect rect = animation.getFrame(currentFrame);

        float width = Math.abs(rect.width) + rect.left;
        height = Math.abs(rect.height) + rect.top;
        
        minX = Integer.MAX_VALUE;
        maxX = 0;
        minY = Integer.MAX_VALUE;
        maxY = 0;
        
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
        
                
        System.out.println("Top point:" + rect.top);
        System.out.println("Min Y value: " + minY);
        System.out.println("Max Y value: " + maxY);

        return new FloatRect(0, 0, maxX - minX + 1, maxY - minY + 1);
    }

    public FloatRect getGlobalBounds() {
        return getTransform().transformRect(getLocalBounds());
    }

    public boolean isLooped() {
        return isLooped;
    }

    public boolean isPlaying() {
        return !isPaused;
    }

    public Time getFrameTime() {
        return frameTime;
    }

    public void setFrame(int newFrame, boolean resetTime) {

        if (animation != null) {

            // calculate new vertex positions and texture coordinates 
            IntRect rect = animation.getFrame(newFrame);

            Vector2f texCoordA = new Vector2f(0, 0);
            Vector2f texCoordB = new Vector2f(0, rect.height);
            Vector2f texCoordC = new Vector2f(rect.width, rect.height);
            Vector2f texCoordD = new Vector2f(rect.width, 0);

            float left = rect.left + 0.0001f;
            float right = left + rect.width;
            float top = rect.top;
            float bottom = top + rect.height;

            vertices[0] = new Vertex(texCoordA, new Vector2f(left, top));
            vertices[1] = new Vertex(texCoordB, new Vector2f(left, bottom));
            vertices[2] = new Vertex(texCoordC, new Vector2f(right, bottom));
            vertices[3] = new Vertex(texCoordD, new Vector2f(right, top));
        }

        if (resetTime) {
            currentTime = Time.ZERO;
        }
    }

    public void update(Time deltaTime) {
        // if not paused and animation is valid
        if (!isPaused && animation != null) {
            // add delta time
            currentTime = Time.add(currentTime, deltaTime);

            // if current time is bigger than frame time, advance one frame
            if (currentTime.compareTo(frameTime) > 0) {
                // reset time, but keep remainder
                currentTime = Time.getMicroseconds(currentTime.asMicroseconds() % frameTime.asMicroseconds());

                // get next frame index
                if (currentFrame + 1 < animation.getFrameCount()) {
                    currentFrame++;
                } else {
                    // animation has ended
                    currentFrame = 0;

                    if (!isLooped) {
                        isPaused = true;
                    }
                }

                // set the current frame, not resetting the time
                setFrame(currentFrame, false);
            }
        }
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        if (animation != null && texture != null) {
            RenderStates newStates = new RenderStates(
                    states.blendMode,
                    Transform.combine(states.transform, getTransform()),
                    texture,
                    states.shader);
            target.draw(vertices, PrimitiveType.QUADS, newStates);
        }
    }

}
