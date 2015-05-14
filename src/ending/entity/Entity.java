package ending.entity;

import ending.entity.player.PlayerInputComponent;
import ending.component.GraphicsComponent;
import ending.component.InputComponent;
import ending.component.PhysicsComponent;
import ending.dungeon.Dungeon;
import ending.util.TextureUtils;
import ending.vector.Vector2f;
import org.jsfml.graphics.BasicTransformable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public class Entity extends BasicTransformable {
    
    private final InputComponent input;
    private final GraphicsComponent graphics;
    private final PhysicsComponent physics;
     
    private final Vector2f velocity;
    
    private FloatRect boundingRect;
    
    public Entity(InputComponent input,
            GraphicsComponent graphics,
            PhysicsComponent physics) {
        this.input = input;
        this.graphics = graphics;
        this.physics = physics;
        
        velocity = new Vector2f();
    }
    
    public void update(Time deltaTime, RenderTarget rt, Dungeon dungeon) {
        input.update(this);
        graphics.update(this, rt, deltaTime);
        physics.update(this, deltaTime, dungeon);
    }
    
    public Vector2f getVelocity() {
        return velocity;
    }
    
    public void setBoundingRect(FloatRect rect) {
        this.boundingRect = rect;
    }

    public FloatRect getBoundingRect() {
        return boundingRect;
    }
    
    public static Entity createPlayer() {
        return new Entity(new PlayerInputComponent(),
                new EntityGraphicsComponent(TextureUtils.loadTexture("sprites/player.png")),
                new EntityPhysicsComponent());
    }
 
}
