package ending.tile.wall;

import ending.tile.TileTextures;
import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class StoneWallTile extends WallTile {
    
    public StoneWallTile() {
        super();
        sprite.setTexture(TileTextures.STONEWALLTEXTURE);
    }
    
    public static TileType getType() {
        return TileType.STONEWALL;
    }
    
}
