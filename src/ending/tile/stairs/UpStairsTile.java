package ending.tile.stairs;

import ending.tile.Tile;
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
        sprite.setTexture(TextureUtils.getTexture("sprites/tiles/upstairs.png"));
    }
    
    public static TileType getType() {
        return TileType.UPSTAIRS;
    }
    
}
