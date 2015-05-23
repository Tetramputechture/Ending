package ending.entity;

import ending.animation.AnimatedSprite;
import ending.animation.Animation;
import ending.component.GraphicsComponent;
import ending.gamestate.State;
import ending.vector.Vector2f;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public class EntityGraphicsComponent implements GraphicsComponent {
    
    private final AnimatedSprite sprite;
    
    private final Animation south;
    private final Animation west;
    private final Animation north;
    private final Animation east;
    private final Animation northwest;
    private final Animation northeast;
    private final Animation southwest;
    private final Animation southeast;
    
    private Animation currentAnimation;
    
    private final int spriteWidth = 32;
    private final int spriteHeight = 32;
    
    private final int numFrames = 5;
    
    private final RectangleShape entityBoundingBoxOutline;
    private final RectangleShape geometryBoundingBoxOutline;
    
    public EntityGraphicsComponent(Texture spriteSheet) {
        entityBoundingBoxOutline = new RectangleShape();
        entityBoundingBoxOutline.setFillColor(Color.TRANSPARENT);
        entityBoundingBoxOutline.setOutlineColor(Color.RED);
        entityBoundingBoxOutline.setOutlineThickness(1);
        
        geometryBoundingBoxOutline = new RectangleShape();
        geometryBoundingBoxOutline.setFillColor(Color.TRANSPARENT);
        geometryBoundingBoxOutline.setOutlineColor(Color.WHITE);
        geometryBoundingBoxOutline.setOutlineThickness(1);
        
        sprite = new AnimatedSprite(Time.getSeconds(0.1f), true, false);
        
        south = new Animation(spriteSheet);
        addFramesToAnimation(south, 0);

        north = new Animation(spriteSheet);
        addFramesToAnimation(north, 32);

        east = new Animation(spriteSheet);
        addFramesToAnimation(east, 64);

        west = new Animation(spriteSheet);
        addFramesToAnimation(west, 96);
        
        northwest = new Animation(spriteSheet);
        addFramesToAnimation(northwest, 128);
        
        northeast = new Animation(spriteSheet);
        addFramesToAnimation(northeast, 160);
        
        southwest = new Animation(spriteSheet);
        addFramesToAnimation(southwest, 192);
        
        southeast = new Animation(spriteSheet);
        addFramesToAnimation(southeast, 224);   
        
        currentAnimation = south;
        
        sprite.setAnimation(currentAnimation);
    }
    
    private void addFramesToAnimation(Animation animation, int height) {
        for (int i = 0; i < numFrames; i++) {
            animation.addFrame(new IntRect(i*spriteWidth, height, spriteWidth, spriteHeight));
        }
    }

    @Override
    public void update(Entity a, RenderTarget rt, Time deltaTime) {

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
        // northwest
        if (velocity.x < 0 && velocity.y < 0) {
            currentAnimation = northwest;
        }
        // northeast
        if (velocity.x > 0 && velocity.y < 0) {
            currentAnimation = northeast;
        }
        // southwest
        if (velocity.x < 0 && velocity.y > 0) {
            currentAnimation = southwest;
        }
        // southeast
        if (velocity.x > 0 && velocity.y > 0) {
            currentAnimation = southeast;
        }

        sprite.play(currentAnimation);

        // if not moving, stop animation
        if (velocity.isZero()) {
            sprite.stop();
        }

        sprite.update(deltaTime);
        
        sprite.setPosition(a.getPosition());
        
        FloatRect spriteBounds = sprite.getGlobalBounds();
        FloatRect geoBounds = new FloatRect(spriteBounds.left+3, spriteBounds.top+spriteBounds.height-3, spriteBounds.width-6, 3);
        
        // update bounding boxes
        a.setEntityBoundingBox(spriteBounds);
        a.setGeometryBoundingBox(geoBounds);
           
        entityBoundingBoxOutline.setPosition(sprite.getPosition());
        entityBoundingBoxOutline.setSize(new Vector2f(spriteBounds.width, spriteBounds.height).toVector2f());
       
        geometryBoundingBoxOutline.setPosition(geoBounds.left, geoBounds.top);
        geometryBoundingBoxOutline.setSize(new Vector2f(geoBounds.width, geoBounds.height).toVector2f());
        
        rt.draw(sprite);
        if (State.isShowEntityBoundingBoxes()) {
            //rt.draw(entityBoundingBoxOutline);
            rt.draw(geometryBoundingBoxOutline);
        }
    }
    
}
