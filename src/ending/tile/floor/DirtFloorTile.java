package ending.tile.floor;

import ending.tile.TileTextures;
import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class DirtFloorTile extends FloorTile {
    
    public DirtFloorTile() {
        super();
        sprite.setTexture(TileTextures.DIRTFLOORTEXTURE);
    }

    public static TileType getType() {
        return TileType.DIRTFLOOR;
    }
    
}
