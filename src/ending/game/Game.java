package ending.game;

import ending.actor.Player;
import ending.dungeon.Dungeon;
import ending.dungeon.DungeonGenerator;
import ending.window.WindowConfig;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;

/**
 * Handles all Game logic. 
 * @author Nick
 */
public class Game implements Drawable {
    
    private Dungeon dungeon;
    
    private final DungeonGenerator dg;
    
    private final View view;
    
    private final Player player;
    
    private final Clock frameClock;
    
    /**
     * Generates a new Dungeon.
     */
    public Game() {
        dg = new DungeonGenerator();
        dungeon = dg.generate(40, 30);
        view = new View();
        view.setSize(320, 240);
        player = new Player();
        dungeon.addActor(20, 15, player);
        frameClock = new Clock();
    }
    
    public View getView() {
        return view;
    }
    
    public void generateNewDungeon() {
        dungeon = dg.generate(40, 30, System.currentTimeMillis());
    }
    
    public void update() {
        view.setCenter(player.getPosition());
        
        Time deltaTime = frameClock.restart();
        
        dungeon.updateActors(deltaTime);
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(dungeon);
    }
    
    
    
}
