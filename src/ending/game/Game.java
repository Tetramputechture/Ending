package ending.game;

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
    
    private final DungeonGenerator dg;
    
    /**
     * Generates a new Dungeon.
     */
    public Game() {
        dg = new DungeonGenerator();
        dungeon = dg.generate(40, 30);
    }
    
    public void generateNewDungeon() {
        dungeon = dg.generate(40, 30, System.currentTimeMillis());
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(dungeon);
    }
    
    
    
}
