package ending.tile.door;

import ending.dungeon.Direction;
import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;
import org.jsfml.system.Time;

/**
 * The Door Tile.
 * @author Nick
 */
public class DoorTile extends Tile {
    
    /**
     * Initializes the Sprite of this Tile, and sets its Passable field to false.
     */
    public DoorTile() {
        super();
        setPassable(false);
        sprite.setTexture(TileTextures.DOORTEXTURE);
    }

    /**
     * Sets the rotation of this door relative to a Direction.
     * POSITION OF THIS DOOR MUST BE SET IN ORDER FOR THIS FUNCTION TO 
     * WORK PROPERLY.
     * @param direction the direction in which to rotate this door.
     */
    public void rotateBasedOnDirection(Direction direction) {
        switch (direction) {
            // door sprite is already aligned with North direction
            case EAST:
                rotateAroundCenter(90);
                break;
            case SOUTH:
                rotateAroundCenter(180);
                break;
            case WEST:
                rotateAroundCenter(-90);
                break;
        }
    }
    /**
     * @return TileType.DOOR.
     */
    public TileType getType() {
        return TileType.DOOR;
    }
}
