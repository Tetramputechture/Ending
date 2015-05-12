package ending.actor;

import org.jsfml.graphics.BasicTransformable;
import org.jsfml.graphics.Drawable;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public abstract class Actor extends BasicTransformable implements Drawable {
    
    public abstract void update(Time deltaTime);
 
}
