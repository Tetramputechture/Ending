package ending.component;

import ending.dungeon.Dungeon;
import ending.entity.Entity;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public interface PhysicsComponent {
    
    public void update(Entity a, Time deltaTime, Dungeon dungeon);
    
}
