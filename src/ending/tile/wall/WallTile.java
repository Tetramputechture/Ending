package ending.tile.wall;

import ending.tile.Tile;

/**
 *
 * @author Nick
 */
public abstract class WallTile extends Tile {
    
    public WallTile() {
        super();
        setPassable(false);
    }
    
}
