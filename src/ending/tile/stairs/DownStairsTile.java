package ending.tile.stairs;

import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class DownStairsTile extends Tile {
    
    public DownStairsTile() {
        super();
        setPassable(true);
        sprite.setTexture(TileTextures.DOWNSTAIRSTEXTURE);
    }
    
    public static TileType getType() {
        return TileType.DOWNSTAIRS;
    }
    
}
