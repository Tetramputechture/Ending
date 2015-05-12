package ending.gamestate.screen;

import ending.game.Game;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.View;

/**
 * The Screen for the Game to be displayed on.
 *
 * @author Nick
 */
public class GameScreen extends Screen {

    private final Game game;
    
    private View view;
    
    private boolean viewToggle;

    /**
     * Instantiates a new Game for this GameScreen to display.
     */
    public GameScreen() {
        game = new Game();

        addKeyListener((e) -> {
            switch (e.key) {
                case SPACE:
                    game.generateNewDungeon();
                    break;
                case Z:
                    viewToggle = !viewToggle;
                    break;
            }
        });
        
        view = new View();
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.clear(Color.BLACK);
        
        game.update();
        
        if (viewToggle) {
            view = game.getView();
        } else {
            view = new View();
            view.setSize(1280, 960);
            view.setCenter(640, 480);
        }
        rt.setView(view);
        rt.draw(game);
    }

}
