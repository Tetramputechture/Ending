package ending.window;

import ending.gamestate.State;
import ending.gamestate.screen.Screen;
import ending.gamestate.screen.ScreenType;
import ending.input.InputHandler;
import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Shader;
import org.jsfml.graphics.ShaderSourceException;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;

/**
 *
 * @author Nick
 */
public class Window {
    
    private final RenderWindow rw;

    public Window(String title) {
        rw = new RenderWindow(new VideoMode(WindowConfig.WINDOW_WIDTH, WindowConfig.WINDOW_HEIGHT), title, WindowStyle.DEFAULT, new ContextSettings(2));
        
        State.setCurrentScreen(ScreenType.MAIN_MENU);
    }
    
    public void setView(View view) {
        rw.setView(view);
    }
    
    public RenderWindow getRenderWindow() {
        return rw;
    }
    
    public void display() {
        while (rw.isOpen()) {
            Screen s = State.getCurrentScreen();
            s.update();
            
            rw.clear();
            rw.draw(s);
            rw.display();
            
            InputHandler.handleEvents(this);
        }
    }
    
    public void resize() {
        
    }
}
