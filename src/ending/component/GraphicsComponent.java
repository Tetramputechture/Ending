package ending.component;

import ending.actor.Actor;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public interface GraphicsComponent {
    
    public void update(Actor a, RenderTarget rt, Time deltaTime);
    
}
