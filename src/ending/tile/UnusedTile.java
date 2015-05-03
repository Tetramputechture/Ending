package ending.tile;

import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class UnusedTile extends Tile {
    
    public UnusedTile() {
        super();
        setPassable(false);
        sprite.setTexture(TileTextures.UNUSEDTEXTURE);
    }
    
    public static TileType getType() {
        return TileType.UNUSED;
    }
    
}
