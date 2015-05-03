package ending.tile.door;

import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;

/**
 * The Door Tile.
 * @author Nick
 */
public class DoorTile extends Tile {
    
    /**
     * Initializes the Sprite of this Tile, and sets its Passable field to false.
     */
    public DoorTile() {
        super();
        setPassable(false);
        sprite.setTexture(TileTextures.DOORTEXTURE);
    }
    
    /**
     * @return TileType.DOOR.
     */
    public static TileType getType() {
        return TileType.DOOR;
    }
}
