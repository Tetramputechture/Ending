package ending.window;

import ending.gamestate.State;
import ending.gamestate.screen.ScreenType;
import ending.input.InputHandler;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;

/**
 *
 * @author Nick
 */
public class Window {
    
    private final RenderWindow rw;
    
    private final InputHandler input;
    
    /**
     *
     * @param title
     */
    public Window(String title) {
        rw = new RenderWindow();
        rw.create(new VideoMode(WindowConfig.WINDOW_WIDTH, WindowConfig.WINDOW_HEIGHT), title);
        
        input = new InputHandler();
        
        State.setCurrentScreen(ScreenType.MAIN_MENU);
    }
    
    /**
     *
     */
    public void display() {
        while (rw.isOpen()) {
            input.handleInput(rw);
            rw.draw(State.getCurrentScreen());
            rw.display();
        }
    }
}
