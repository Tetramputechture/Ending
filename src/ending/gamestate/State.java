package ending.gamestate;

import ending.gamestate.screen.Screen;
import ending.gamestate.screen.ScreenType;

/**
 * Handles all aspects of state.
 * @author Nick
 */
public final class State {
    
    private static Screen currentScreen;
    
    /**
     * Gets the current Screen of the State.
     * @return the State's current displaying Screen.
     */
    public static Screen getCurrentScreen() {
        return currentScreen;
    }
    
    /**
     * Sets the current Screen of the State.
     * @param screenType the type of the Screen to set.
     */
    public static void setCurrentScreen(ScreenType screenType) {
        currentScreen = Screen.getScreen(screenType);
    }
    
    /**
     * Sets the current Screen of the State.
     * @param screen the Screen to set the State to.
     */
    public static void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }
}
