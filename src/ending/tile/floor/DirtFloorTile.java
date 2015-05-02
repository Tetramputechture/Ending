package ending.tile.floor;

import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class DirtFloorTile extends FloorTile {
    
    public DirtFloorTile() {
        super();
        sprite.setTexture(TextureUtils.getTexture("sprites/tiles/dirtfloor.png"));
    }

    public static TileType getType() {
        return TileType.DIRTFLOOR;
    }
    
}
