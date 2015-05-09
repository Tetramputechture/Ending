package ending.game;

import ending.actor.Player;
import ending.dungeon.Dungeon;
import ending.dungeon.DungeonGenerator;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 * Handles all Game logic. 
 * @author Nick
 */
public class Game implements Drawable {
    
    private Dungeon dungeon;
    
    private Player player;
    
    private final DungeonGenerator dg;
    
    /**
     * Generates a new Dungeon.
     */
    public Game() {
        dg = new DungeonGenerator();
        dungeon = dg.generate(40, 30);
        player = dungeon.getPlayer();
    }
    
    public void generateNewDungeon() {
        dungeon = dg.generate(40, 30, System.currentTimeMillis());
        player = dungeon.getPlayer();
    }
    
    public Player getPlayer() {
        return player;
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(dungeon);
    }
    
    
    
}
