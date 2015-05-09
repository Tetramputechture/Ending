package ending.gamestate.screen;

import ending.game.Game;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;

/**
 * The Screen for the Game to be displayed on.
 *
 * @author Nick
 */
public class GameScreen extends Screen {

    private final Game game;
    
    private final View view;
    
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
                case W:
                    game.getPlayer().move(0, -8);
                    break;
                case A:
                    game.getPlayer().move(-8, 0);
                    break;
                case S:
                    game.getPlayer().move(0, 8);
                    break;
                case D:
                    game.getPlayer().move(8, 0);
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
        
        if (viewToggle) {
            view.setSize(640, 480);
            view.setCenter(game.getPlayer().getPosition());
        } else {
            view.setSize(1280, 960);
            view.setCenter(640, 480);
        }
        
        rt.setView(view);

        rt.draw(game);
        
        rt.draw(game.getPlayer());
    }

}
