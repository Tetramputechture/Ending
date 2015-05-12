package ending.tile.wall;

import ending.tile.TileTextures;
import ending.tile.TileType;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public class StoneWallTile extends WallTile {
    
    /**
     * Initializes the Sprite of this WallTile.
     */
    public StoneWallTile() {
        super();
        sprite.setTexture(TileTextures.STONEWALLTEXTURE);
    }
    
    /**
     * @return TileType.STONEWALL
     */
    public TileType getType() {
        return TileType.STONEWALL;
    }

}
