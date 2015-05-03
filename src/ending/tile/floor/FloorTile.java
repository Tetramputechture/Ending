package ending.tile.floor;

import ending.tile.Tile;

/**
 * A passable, non-interactable Tile.
 * @author Nick
 */
public abstract class FloorTile extends Tile {
    
    /**
     * Initializes this FloorTile, and sets its Passable field to true.
     */
    public FloorTile() {
        super();
        setPassable(true);
    }
    
}
