package ending.entity.player;

import ending.entity.Entity;
import ending.component.InputComponent;
import ending.input.InputHandler;
import ending.vector.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;

/**
 *
 * @author Nick
 */
public class PlayerInputComponent implements InputComponent {
    
    private final int speed = 70;

    @Override
    public void update(Entity a) {
        Vector2f velocity = a.getVelocity();
        
        if (InputHandler.isKeyPressed(Keyboard.Key.S)) {
            velocity.y += speed;
        }
        if (InputHandler.isKeyPressed(Keyboard.Key.A)) {
            velocity.x -= speed;
        }
        if (InputHandler.isKeyPressed(Keyboard.Key.D)) {
            velocity.x += speed;
        }
        if (InputHandler.isKeyPressed(Keyboard.Key.W)) {
            velocity.y -= speed;
        }
    }

}
