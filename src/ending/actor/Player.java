package ending.actor;

import ending.animation.AnimatedSprite;
import ending.animation.Animation;
import ending.vector.Vector2f;
import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Time;
import org.jsfml.window.Keyboard;

/**
 *
 * @author Nick
 */
public class Player extends Actor {

    private final AnimatedSprite pSprite;

    private Animation currentAnimation;
    
    private final Animation down;
    private final Animation up;
    private final Animation right;
    private final Animation left;

    private final Vector2f velocity;
    
    private final float speed = 70;
    
    private final double sqrt2 = Math.sqrt(2);

    public Player() {
        super();

        Texture texture = new Texture();
        try {
            texture.loadFromFile(Paths.get("sprites/player.png"));
        } catch (IOException ex) {
            System.out.println("Sprite sheet file not found!");
        }

        down = new Animation();
        down.setSpriteSheet(texture);
        down.addFrame(new IntRect(0, 0, 32, 32));
        down.addFrame(new IntRect(32, 0, 32, 32));
        down.addFrame(new IntRect(64, 0, 32, 32));
        down.addFrame(new IntRect(96, 0, 32, 32));
        down.addFrame(new IntRect(128, 0, 32, 32));
        
        up = new Animation();
        up.setSpriteSheet(texture);
        up.addFrame(new IntRect(0, 32, 32, 32));
        up.addFrame(new IntRect(32, 32, 32, 32));
        up.addFrame(new IntRect(64, 32, 32, 32));
        up.addFrame(new IntRect(96, 32, 32, 32));
        up.addFrame(new IntRect(128, 32, 32, 32));
        
        right = new Animation();
        right.setSpriteSheet(texture);
        right.addFrame(new IntRect(0, 64, 32, 32));
        right.addFrame(new IntRect(32, 64, 32, 32));
        right.addFrame(new IntRect(64, 64, 32, 32));
        right.addFrame(new IntRect(96, 64, 32, 32));
        right.addFrame(new IntRect(128, 64, 32, 32));
        
        left = new Animation();
        left.setSpriteSheet(texture);
        left.addFrame(new IntRect(0, 96, 32, 32));
        left.addFrame(new IntRect(32, 96, 32, 32));
        left.addFrame(new IntRect(64, 96, 32, 32));
        left.addFrame(new IntRect(96, 96, 32, 32));
        left.addFrame(new IntRect(128, 96, 32, 32));

        currentAnimation = down;

        pSprite = new AnimatedSprite(Time.getSeconds(0.1f), true, false);
        
        velocity = new Vector2f();
    }

    @Override
    public void setPosition(org.jsfml.system.Vector2f pos) {
        pSprite.setPosition(pos);
    }

    @Override
    public org.jsfml.system.Vector2f getPosition() {
        return pSprite.getPosition();
    }

    @Override
    public void update(Time deltaTime) {
        
        if (Keyboard.isKeyPressed(Keyboard.Key.S)) {
            currentAnimation = down;
            velocity.y += speed;
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.A)) {
            currentAnimation = left;
            velocity.x -= speed;
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.D)) {
            currentAnimation = right;
            velocity.x += speed;
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.W)) {
            currentAnimation = up;
            velocity.y -= speed;
        }
        
        // if player is moving diagonally, divide vector by sqrt 2
        if (velocity.x != 0 && velocity.y != 0) {
            velocity.x /= sqrt2;
            velocity.y /= sqrt2;
        }

        pSprite.play(currentAnimation);
        pSprite.move(velocity.scl(deltaTime.asSeconds()).toVector2f());

        // if not moving, stop animation
        // should be replaced with idle animation
        if (velocity.isZero()) {
            pSprite.stop();
        }
        
        // set velocity back to 0 for next frame
        velocity.x = 0;
        velocity.y = 0;

        pSprite.update(deltaTime);
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        target.draw(pSprite);
    }

}
