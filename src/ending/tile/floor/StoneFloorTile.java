package ending.tile.floor;

import ending.tile.TileTextures;
import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class StoneFloorTile extends FloorTile {
    
    public StoneFloorTile() {
        super();
        sprite.setTexture(TileTextures.STONEFLOORTEXTURE);
    }
    
    public static TileType getType() {
        return TileType.STONEFLOOR;
    }
    
}
