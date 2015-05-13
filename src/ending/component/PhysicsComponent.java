package ending.component;

import ending.actor.Actor;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public interface PhysicsComponent {
    
    public void update(Actor a, Time deltaTime);
    
}
