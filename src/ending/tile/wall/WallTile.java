package ending.tile.wall;

import ending.tile.Tile;

/**
 * A non-passable, non-interactable tile.
 * @author Nick
 */
public abstract class WallTile extends Tile {
    
    /**
     * Sets this Tile's passable field to false.
     */
    public WallTile() {
        super();
        setPassable(false);
    }
    
}
