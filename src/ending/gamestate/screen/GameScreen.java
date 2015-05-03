package ending.gamestate.screen;

import ending.game.Game;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 * The Screen for the Game to be displayed on.
 * @author Nick
 */
public class GameScreen extends Screen {
    
    private final Game game;
    
    /**
     * Instantiates a new Game for this GameScreen to display.
     */
    public GameScreen() {
        game = new Game();
    }
    
    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(game);
    }
    
}