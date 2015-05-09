package ending.input;

import ending.gamestate.State;
import org.jsfml.graphics.RenderWindow;
import ending.vector.Vector2i;
import ending.widget.Button;
import ending.widget.Widget;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.window.event.MouseButtonEvent;
import org.jsfml.window.event.MouseEvent;

/**
 * Handles all key presses and mouse actions.
 * @author Nick
 */
public class InputHandler {

    /**
     * Handles the input of a RenderWindow.
     * @param rw the RenderWindow for this inputHandler to handle.
     */
    public void handleInput(RenderWindow rw) {
        for (Event event : rw.pollEvents()) {
            switch (event.type) {
                case CLOSED:
                    System.out.println("!");
                    rw.close();
                    break;
                case KEY_PRESSED:
                    handleKeyPress(event.asKeyEvent());
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

    private void handleKeyPress(KeyEvent keyEvent) {
        for (KeyListener k : State.getCurrentScreen().getKeyListeners()) {
            k.keyPressed(keyEvent);
        }
    }

    private void handleMouseMove(MouseEvent mouseEvent) {
        Vector2i mousePos = new Vector2i(mouseEvent.position);

        for (Widget w : State.getCurrentScreen().getWidgets()) {
            if (w instanceof Button) {
                Button b = (Button) w;
                if (b.getFrame().contains(mousePos)) {
                    b.setEntered();
                    return;
                }
                b.setExited();
            }
        }
    }

    private void handleMouseClick(MouseButtonEvent mouseButtonEvent) {
        Vector2i mousePos = new Vector2i(mouseButtonEvent.position);

        if (mouseButtonEvent.button == Mouse.Button.LEFT) {
            for (Widget w : State.getCurrentScreen().getWidgets()) {
                if (w instanceof Button) {
                    Button b = (Button) w;
                    if (b.getFrame().contains(mousePos)) {
                        b.setClicked();
                        return;
                    }
                }
            }
        }
    }

}
