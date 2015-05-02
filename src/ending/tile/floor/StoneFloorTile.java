package ending.tile.floor;

import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class StoneFloorTile extends FloorTile {
    
    public StoneFloorTile() {
        super();
        sprite.setTexture(TextureUtils.getTexture("sprites/tiles/stonefloor.png"));
    }
    
    public static TileType getType() {
        return TileType.STONEFLOOR;
    }
    
}
