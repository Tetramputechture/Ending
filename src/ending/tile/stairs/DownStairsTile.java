package ending.tile.stairs;

import ending.tile.Tile;
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
        sprite.setTexture(TextureUtils.getTexture("sprites/tiles/downstairs.png"));
    }
    
    public static TileType getType() {
        return TileType.DOWNSTAIRS;
    }
    
}
