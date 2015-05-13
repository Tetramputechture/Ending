package ending.actor;

import ending.actor.player.PlayerInputComponent;
import ending.component.GraphicsComponent;
import ending.component.InputComponent;
import ending.component.PhysicsComponent;
import ending.util.TextureUtils;
import ending.vector.Vector2f;
import org.jsfml.graphics.BasicTransformable;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public class Actor extends BasicTransformable {
    
    private final InputComponent input;
    private final GraphicsComponent graphics;
    private final PhysicsComponent physics;
     
    private final Vector2f velocity;
    
    public Actor(InputComponent input,
            GraphicsComponent graphics,
            PhysicsComponent physics) {
        this.input = input;
        this.graphics = graphics;
        this.physics = physics;
        
        velocity = new Vector2f();
    }
    
    public void update(Time deltaTime, RenderTarget rt) {
        input.update(this);
        graphics.update(this, rt, deltaTime);
        physics.update(this, deltaTime);
    }
    
    public Vector2f getVelocity() {
        return velocity;
    }
    
    public static Actor createPlayer() {
        return new Actor(new PlayerInputComponent(),
                new ActorGraphicsComponent(TextureUtils.loadTexture("sprites/player.png")),
                new ActorPhysicsComponent());
    }
 
}
