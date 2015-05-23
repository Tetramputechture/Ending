package ending.input;

import ending.gamestate.State;
import ending.vector.Vector2i;
import ending.window.Window;
import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseButtonEvent;
import org.jsfml.window.event.MouseEvent;

/**
 * Handles all key presses and mouse actions.
 *
 * @author Nick
 */
public final class InputHandler {

    private static final ArrayList<Keyboard.Key> keysPressed = new ArrayList<>();

    private static final ArrayList<Mouse.Button> mouseButtonsPressed = new ArrayList<>();

    private static Vector2i mousePosition;

    /**
     * Handles the events of a Window.
     *
     * @param w the Window for this inputHandler to handle.
     */
    public static void handleEvents(Window w) {
        RenderWindow rw = w.getRenderWindow();
        for (Event event : rw.pollEvents()) {
            switch (event.type) {
                case CLOSED:
                    rw.close();
                    break;
                case KEY_PRESSED:
                    handleKeyPress(event.asKeyEvent());
                    break;
                case KEY_RELEASED:
                    handleKeyRelease(event.asKeyEvent());
                    break;
                case MOUSE_MOVED:
                    handleMouseMove(event.asMouseEvent());
                    break;
                case MOUSE_BUTTON_PRESSED:
                    handleMouseClick(event.asMouseButtonEvent());
                    break;
            }
        }
    }

    private static void handleKeyPress(KeyEvent keyEvent) {
        if (!keysPressed.contains(keyEvent.key)) {
            keysPressed.add(keyEvent.key);
        }
        
        KeyListener k = State.getCurrentScreen().getKeyListener();
        if (k != null) {
            k.keyPressed(keyEvent);
        }
    }

    private static void handleKeyRelease(KeyEvent keyEvent) {
        keysPressed.remove(keyEvent.key);
    }
    
    public static ArrayList<Keyboard.Key> getKeysPressed() {
        return keysPressed;
    }

    public static boolean isKeyPressed(Keyboard.Key key) {
        return keysPressed.contains(key);
    }

    private static void handleMouseMove(MouseEvent mouseEvent) {
        mousePosition = new Vector2i(mouseEvent.position);
    }

    private static void handleMouseClick(MouseButtonEvent mouseButtonEvent) {
        if (!mouseButtonsPressed.contains(mouseButtonEvent.button)) {
            mouseButtonsPressed.add(mouseButtonEvent.button);
        }
    }
    
    public static boolean isMouseButtonPressed(Mouse.Button button) {
        return mouseButtonsPressed.contains(button);
    }
    
    public static Vector2i getMousePosition() {
        return mousePosition;
    }
}
