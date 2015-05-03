package ending.gamestate;

import ending.gamestate.screen.Screen;
import ending.gamestate.screen.ScreenType;

/**
 *
 * @author Nick
 */
public final class GameState {
    
    private static Screen currentScreen;
    
    public static Screen getCurrentScreen() {
        return currentScreen;
    }
    
    public static void setCurrentScreen(ScreenType screenType) {
        currentScreen = Screen.getScreen(screenType);
    }
    
    public static void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }
}
