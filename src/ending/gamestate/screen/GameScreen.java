package ending.gamestate.screen;

import ending.game.Game;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 *
 * @author Cliff
 */
public class GameScreen extends Screen {
    
    private final Game game;
    
    public GameScreen() {
        game = new Game();
    }
    
    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(game);
    }
    
}
