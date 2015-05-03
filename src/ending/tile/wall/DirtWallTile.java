package ending.tile.wall;

import ending.tile.TileTextures;
import ending.tile.TileType;

/**
 * The Dirt Wall tile.
 * @author Nick
 */
public class DirtWallTile extends WallTile {
    
    /**
     * Initializes the Sprite of this WallTile.
     */
    public DirtWallTile() {
        super();
        sprite.setTexture(TileTextures.DIRTWALLTEXTURE);
    }
    
    /**
     * @return TileType.DIRTWALL.
     */
    public static TileType getType() {
        return TileType.DIRTWALL;
    }
    
}
