package ending.tile.wall;

import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class DirtWallTile extends WallTile {
    
    public DirtWallTile() {
        super();
        sprite.setTexture(TextureUtils.getTexture("sprites/tiles/dirtwall.png"));
    }
    
    public static TileType getType() {
        return TileType.DIRTWALL;
    }
    
}
