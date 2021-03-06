package ending.game;

import ending.entity.Entity;
import ending.dungeon.Dungeon;
import ending.dungeon.DungeonGenerator;
import ending.dungeon.StoneDungeonStyle;
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
    
    private final Entity player;
    
    private final Clock frameClock;
    
    private Time deltaTime;
    
    /**
     * Generates a new Dungeon.
     */
    public Game() {
        dg = new DungeonGenerator();
        dungeon = dg.generate(new StoneDungeonStyle(), 40, 30, 1431834694713L);
        view = new View();
        view.setSize(400, 300);
        player = Entity.createPlayer();
        dungeon.addEntity(20, 15, player);
        frameClock = new Clock();
    }
    
    public View getView() {
        return view;
    }
    
    public void generateNewDungeon() {
        dungeon = dg.generate(new StoneDungeonStyle(), 40, 30, System.currentTimeMillis());
        dungeon.addEntity(20, 15, player);
    }
    
    public void update() {
        view.setCenter(player.getPosition());
        deltaTime = frameClock.restart();
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        dungeon.update(deltaTime, rt, states);
    }
}
