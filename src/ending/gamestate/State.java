package ending.gamestate;

import ending.gamestate.screen.Screen;
import ending.gamestate.screen.ScreenType;
import ending.input.InputHandler;
import ending.window.Window;

/**
 * Handles all aspects of state.
 * @author Nick
 */
public final class State {
    
    private static Window currentWindow;
    
    private static Screen currentScreen;
    
    private static InputHandler input;
    
    public static Window getCurrentWindow() {
        return currentWindow;
    }
    
    public static void setCurrentWindow(Window w) {
        currentWindow = w;
    }
    
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
        currentScreen = Screen.getScreenBasedOnType(screenType);
    }
    
    /**
     * Sets the current Screen of the State.
     * @param screen the Screen to set the State to.
     */
    public static void setCurrentScreen(Screen screen) {
        currentScreen = screen;
    }
    
    public static void setInputHandler(InputHandler ih) {
        input = ih;
    }
    
    public static InputHandler getInputHandler() {
        return input;
    }
}
