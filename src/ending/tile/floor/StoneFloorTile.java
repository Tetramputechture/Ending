package ending.tile.floor;

import ending.tile.TileTextures;
import ending.tile.TileType;
import org.jsfml.system.Time;

/**
 * The Stone Floor Tile.
 * @author Nick
 */
public class StoneFloorTile extends FloorTile {
    
    /**
     * Initializes the Sprite of this FloorTile.
     */
    public StoneFloorTile() {
        super();
        sprite.setTexture(TileTextures.STONEFLOORTEXTURE);
    }
    
    /**
     * @return TileType.STONEFLOOR.
     */
    public TileType getType() {
        return TileType.STONEFLOOR;
    }
    
}
