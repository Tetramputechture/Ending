package ending.component;

import ending.entity.Entity;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public interface GraphicsComponent {
    
    public void update(Entity a, RenderTarget rt, Time deltaTime);
    
}
