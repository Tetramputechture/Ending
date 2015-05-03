package ending.tile.stairs;

import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;

/**
 * The Down Stairs tile; leads to an upper Dungeon.
 * @author Nick
 */
public class UpStairsTile extends Tile {
    
    /**
     * Initializes the Sprite of this Tile, and sets is Passable field to true.
     */
    public UpStairsTile() {
        super();
        setPassable(true);
        sprite.setTexture(TileTextures.UPSTAIRSTEXTURE);
    }
    
    /**
     * @return TileType.UPSTAIRS.
     */
    public static TileType getType() {
        return TileType.UPSTAIRS;
    }
    
}
