package ending.tile.wall;

import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class StoneWallTile extends WallTile {
    
    public StoneWallTile() {
        super();
        sprite.setTexture(TextureUtils.getTexture("sprites/tiles/stonewall.png"));
    }
    
    public static TileType getType() {
        return TileType.STONEWALL;
    }
    
}
