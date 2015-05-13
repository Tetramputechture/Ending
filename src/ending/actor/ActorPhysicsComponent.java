package ending.actor;

import ending.component.PhysicsComponent;
import ending.vector.Vector2f;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public class ActorPhysicsComponent implements PhysicsComponent {
        
    private static final double SQRT2 = Math.sqrt(2);

    @Override
    public void update(Actor a, Time deltaTime) {
        
        Vector2f velocity = a.getVelocity();
        
        // if actor is moving diagonally, divide vector by sqrt 2
        if (velocity.x != 0 && velocity.y != 0) {
            velocity.x /= SQRT2;
            velocity.y /= SQRT2;
        }
           
        a.move(velocity.scl(deltaTime.asSeconds()).toVector2f());
        
        // set velocity back to 0 for next frame
        velocity.x = 0;
        velocity.y = 0;
    }
}
