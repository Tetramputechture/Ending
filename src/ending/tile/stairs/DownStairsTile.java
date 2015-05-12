package ending.tile.stairs;

import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;
import org.jsfml.system.Time;

/**
 * The Down Stairs tile; leads to a lower Dungeon.
 * @author Nick
 */
public class DownStairsTile extends Tile {
    
    /**
     * Initializes the Sprite of this Tile, and sets its Passable field to True.
     */
    public DownStairsTile() {
        super();
        setPassable(false);
        sprite.setTexture(TileTextures.DOWNSTAIRSTEXTURE);
    }
    
    // make rotation function
    
    /**
     * @return TileType.DOWNSTAIRS.
     */
    public TileType getType() {
        return TileType.DOWNSTAIRS;
    }
    
}
