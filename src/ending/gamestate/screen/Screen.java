package ending.gamestate.screen;

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
    public static Screen getScreen(ScreenType screenType) {
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
     *
     * @return
     */
    public ArrayList<Widget> getWidgets() {
        return widgets;
    }
    
    /**
     *
     * @param rt
     * @param states
     */
    @Override
    public abstract void draw(RenderTarget rt, RenderStates states);
    
}
