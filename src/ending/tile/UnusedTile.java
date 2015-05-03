package ending.tile;

/**
 * The Unused Tile. Used in Dungeon generation.
 * @author Nick
 */
public class UnusedTile extends Tile {
    
    /**
     * Initializes the Sprite of this Tile, and sets its Passable field to false.
     */
    public UnusedTile() {
        super();
        setPassable(false);
        sprite.setTexture(TileTextures.UNUSEDTEXTURE);
    }
    
    /**
     * @return TileType.UNUSED.
     */
    public static TileType getType() {
        return TileType.UNUSED;
    }
    
}
