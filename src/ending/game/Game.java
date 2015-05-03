package ending.game;

import ending.dungeon.Dungeon;
import ending.dungeon.DungeonGenerator;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 *
 * @author Nick
 */
public class Game implements Drawable {
    
    private Dungeon dungeon;
    
    public Game() {
        DungeonGenerator dg = new DungeonGenerator();
        dungeon = dg.generate(40, 30);
        dungeon.compile();
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(dungeon);
    }
    
    
    
}
