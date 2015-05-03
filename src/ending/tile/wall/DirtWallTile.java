package ending.tile.wall;

import ending.tile.TileTextures;
import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class DirtWallTile extends WallTile {
    
    public DirtWallTile() {
        super();
        sprite.setTexture(TileTextures.DIRTWALLTEXTURE);
    }
    
    public static TileType getType() {
        return TileType.DIRTWALL;
    }
    
}
