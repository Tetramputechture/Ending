package ending.tile.stairs;

import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class UpStairsTile extends Tile {
    
    public UpStairsTile() {
        super();
        setPassable(true);
        sprite.setTexture(TileTextures.UPSTAIRSTEXTURE);
    }
    
    public static TileType getType() {
        return TileType.UPSTAIRS;
    }
    
}
