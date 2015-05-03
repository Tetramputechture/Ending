package ending.tile.stairs;

import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;

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
        setPassable(true);
        sprite.setTexture(TileTextures.DOWNSTAIRSTEXTURE);
    }
    
    /**
     * @return TileType.DOWNSTAIRS.
     */
    public static TileType getType() {
        return TileType.DOWNSTAIRS;
    }
    
}
