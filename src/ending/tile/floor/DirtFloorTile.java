package ending.tile.floor;

import ending.tile.TileTextures;
import ending.tile.TileType;

/**
 * The Dirt Floor Tile.
 * @author Nick
 */
public class DirtFloorTile extends FloorTile {
    
    /**
     * Initializes the Sprite of this FloorTile.
     */
    public DirtFloorTile() {
        super();
        sprite.setTexture(TileTextures.DIRTFLOORTEXTURE);
    }

    /**
     * @return TileType.DIRTFLOOR.
     */
    public TileType getType() {
        return TileType.DIRTFLOOR;
    }
    
}
