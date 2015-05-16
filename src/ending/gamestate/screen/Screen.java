package ending.gamestate.screen;

import ending.input.InputHandler;
import ending.input.KeyListener;
import ending.widget.Widget;
import java.util.ArrayList;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.Keyboard;

/**
 * A displayable Screen.
 * @author Nick
 */
public abstract class Screen implements Drawable {
    
    /**
     * The Widgets that this Screen holds.
     */
    protected final ArrayList<Widget> widgets;
    
    /**
     * The KeyListener listening to this Screen.
     */
    private KeyListener keyListener;

    /**
     * Initializes this Screen's list of Widgets. 
     */
    public Screen() {
        widgets = new ArrayList<>();
    }
    
    /**
     * Returns a new instance of a Screen based on a ScreenType.
     * @param screenType the type of Screen to return.
     * @return a new Screen of type screenType.
     */
    public static Screen getScreenBasedOnType(ScreenType screenType) {
        switch (screenType) {
            case MAIN_MENU:
                return new MainMenuScreen();
            case GAME:
                return new GameScreen();
            default:
                return null;
        }
    }
    
    public void addKeyListener(KeyListener k) {
        keyListener = k;
    }
    
    public KeyListener getKeyListener() {
        return keyListener;
    }
    
    /**
     * @return the List of Widgets this Screen holds.
     */
    public ArrayList<Widget> getWidgets() {
        return widgets;
    }
    
    public void update() {
        for (Widget w : widgets) {
            w.update();
        }
    }
    
    @Override
    public abstract void draw(RenderTarget rt, RenderStates states);
    
    
}
