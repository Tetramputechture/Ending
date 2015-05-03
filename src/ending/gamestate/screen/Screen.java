package ending.gamestate.screen;

import ending.widget.Widget;
import java.util.ArrayList;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 *
 * @author Cliff
 */
public abstract class Screen implements Drawable {
    
    protected final ArrayList<Widget> widgets;
    
    public Screen() {
        widgets = new ArrayList<>();
    }
    
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
    
    public ArrayList<Widget> getWidgets() {
        return widgets;
    }
    
    @Override
    public abstract void draw(RenderTarget rt, RenderStates states);
    
}
