package ending.gamestate.screen;

import ending.input.KeyListener;
import ending.widget.Widget;
import java.util.ArrayList;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

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
     * The KeyListeners that are listening to this Screen.
     */
    protected final ArrayList<KeyListener> keyListeners;
    
    /**
     * Initializes this Screen's list of Widgets. 
     */
    public Screen() {
        widgets = new ArrayList<>();
        keyListeners = new ArrayList<>();
    }
    
    public void addKeyListener(KeyListener keyListener) {
        if (!keyListeners.contains(keyListener)) {
            keyListeners.add(keyListener);
        }
    }
    
    public void detachKeyListener(KeyListener keyListener) {
        if (keyListeners.contains(keyListener)) {
            keyListeners.remove(keyListener);
        }
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
    
    /**
     * @return the List of Widgets this Screen holds.
     */
    public ArrayList<Widget> getWidgets() {
        return widgets;
    }
    
    /**
     * @return the List of KeyListeners this Screen holds.
     */
    public ArrayList<KeyListener> getKeyListeners() {
        return keyListeners;
    }
    
    @Override
    public abstract void draw(RenderTarget rt, RenderStates states);
    
}
