package ending.actor.player;

import ending.actor.Actor;
import ending.component.InputComponent;
import ending.vector.Vector2f;
import org.jsfml.window.Keyboard;

/**
 *
 * @author Nick
 */
public class PlayerInputComponent implements InputComponent {
    
    private final int speed = 70;

    @Override
    public void update(Actor a) {
        Vector2f velocity = a.getVelocity();
        
        if (Keyboard.isKeyPressed(Keyboard.Key.S)) {
            velocity.y += speed;
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.A)) {
            velocity.x -= speed;
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.D)) {
            velocity.x += speed;
        }
        if (Keyboard.isKeyPressed(Keyboard.Key.W)) {
            velocity.y -= speed;
        }
    }

}
