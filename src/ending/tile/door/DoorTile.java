package ending.tile.door;

import ending.dungeon.Direction;
import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;

/**
 * The Door Tile.
 * @author Nick
 */
public class DoorTile extends Tile {
    
    /**
     * Initializes the Sprite of this Tile, and sets its Passable field to false.
     * Position must be specified at construction to properly rotate the door.
     * @param x the x coordinate of the door
     * @param y the y coordinate of the door
     * @param direction the direction of the area the door is placed in
     */
    public DoorTile(int x, int y, Direction direction) {
        super();
        setPassable(false);
        sprite.setTexture(TileTextures.DOORTEXTURE);
        sprite.setPosition(x, y);
        rotateBasedOnDirection(direction);
    }

    private void rotateBasedOnDirection(Direction direction) {
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
