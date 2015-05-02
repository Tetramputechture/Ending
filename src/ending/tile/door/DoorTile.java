package ending.tile.door;

import ending.tile.Tile;
import ending.tile.TileType;
import ending.util.TextureUtils;

/**
 *
 * @author Nick
 */
public class DoorTile extends Tile {
    
    public DoorTile() {
        super();
        setPassable(false);
        sprite.setTexture(TextureUtils.getTexture("sprites/tiles/door.png"));
    }
    
    public static TileType getType() {
        return TileType.DOOR;
    }
}
