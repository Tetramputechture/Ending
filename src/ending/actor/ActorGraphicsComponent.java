package ending.actor;

import ending.animation.AnimatedSprite;
import ending.animation.Animation;
import ending.component.GraphicsComponent;
import ending.util.TextureUtils;
import ending.vector.Vector2f;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public class ActorGraphicsComponent implements GraphicsComponent {
    
    private final AnimatedSprite aSprite;
    
    private final Animation south;
    private final Animation west;
    private final Animation north;
    private final Animation east;
    
    private Animation currentAnimation;
    
    public ActorGraphicsComponent(Texture spriteSheet) {
        aSprite = new AnimatedSprite(Time.getSeconds(0.1f), true, false);
        
        south = new Animation();
        south.setSpriteSheet(spriteSheet);
        south.addFrame(new IntRect(0, 0, 32, 32));
        south.addFrame(new IntRect(32, 0, 32, 32));
        south.addFrame(new IntRect(64, 0, 32, 32));
        south.addFrame(new IntRect(96, 0, 32, 32));
        south.addFrame(new IntRect(128, 0, 32, 32));

        north = new Animation();
        north.setSpriteSheet(spriteSheet);
        north.addFrame(new IntRect(0, 32, 32, 32));
        north.addFrame(new IntRect(32, 32, 32, 32));
        north.addFrame(new IntRect(64, 32, 32, 32));
        north.addFrame(new IntRect(96, 32, 32, 32));
        north.addFrame(new IntRect(128, 32, 32, 32));

        east = new Animation();
        east.setSpriteSheet(spriteSheet);
        east.addFrame(new IntRect(0, 64, 32, 32));
        east.addFrame(new IntRect(32, 64, 32, 32));
        east.addFrame(new IntRect(64, 64, 32, 32));
        east.addFrame(new IntRect(96, 64, 32, 32));
        east.addFrame(new IntRect(128, 64, 32, 32));

        west = new Animation();
        west.setSpriteSheet(spriteSheet);
        west.addFrame(new IntRect(0, 96, 32, 32));
        west.addFrame(new IntRect(32, 96, 32, 32));
        west.addFrame(new IntRect(64, 96, 32, 32));
        west.addFrame(new IntRect(96, 96, 32, 32));
        west.addFrame(new IntRect(128, 96, 32, 32));

        currentAnimation = south;
    }

    @Override
    public void update(Actor a, RenderTarget rt, Time deltaTime) {

        Vector2f velocity = a.getVelocity();
        
        // south
        if (velocity.x == 0 && velocity.y > 0) {
            currentAnimation = south;
        }
        // east
        if (velocity.x > 0 && velocity.y == 0) {
            currentAnimation = east;
        }
        // north
        if (velocity.x == 0 && velocity.y < 0) {
            currentAnimation = north;
        }
        // west
        if (velocity.x < 0 && velocity.y == 0) {
            currentAnimation = west;
        }

        aSprite.play(currentAnimation);

        // if not moving, stop animation
        // should be replaced with idle animation
        if (velocity.isZero()) {
            aSprite.stop();
        }

        aSprite.update(deltaTime);
        
        RenderStates rdefault = RenderStates.DEFAULT;
        RenderStates newStates = new RenderStates(
            rdefault.blendMode,
            Transform.combine(rdefault.transform, a.getTransform()),
            rdefault.texture,
            rdefault.shader
            );
        
        rt.draw(aSprite, newStates);
    }
    
}
