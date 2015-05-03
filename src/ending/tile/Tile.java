package ending.tile;

import ending.tile.corridor.StoneCorridorTile;
import ending.tile.door.DoorTile;
import ending.tile.floor.DirtFloorTile;
import ending.tile.floor.StoneFloorTile;
import ending.tile.stairs.DownStairsTile;
import ending.tile.stairs.UpStairsTile;
import ending.tile.wall.DirtWallTile;
import ending.tile.wall.StoneWallTile;
import java.util.Objects;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/**
 * A Tile that encapsulates every object within a Dungeon.
 * @author Nick
 */
public abstract class Tile implements Drawable {
    
    /**
     * The width of every Tile.
     */
    public static final int TILE_WIDTH = 16;

    /**
     * The height of every Tile.
     */
    public static final int TILE_HEIGHT = 16;

    /**
     * The Sprite of this Tile.
     */
    protected final Sprite sprite;

    private boolean passable;

    /**
     * Initializes the Sprite of this Tile, and sets its Passable field to false.
     */
    public Tile() {
        sprite = new Sprite();
        passable = false;
    }

    /**
     * Returns the TileType of this Tile.
     * @return the TileType of this tile; all types found in TileTypes.
     */
    public static TileType getType() {
        return null;
    }

    /**
     * Returns a new instance of a Tile based on a TileType.
     * @param tileType the type of Tile to return.
     * @return a new Tile of type tileType.
     */
    public static Tile getTile(TileType tileType) {
        switch (tileType) {
            case UNUSED:
                return new UnusedTile();
            case DIRTFLOOR:
                return new DirtFloorTile();
            case STONEFLOOR:
                return new StoneFloorTile();
            case STONECORRIDOR:
                return new StoneCorridorTile();
            case DOOR:
                return new DoorTile();
            case DIRTWALL:
                return new DirtWallTile();
            case STONEWALL:
                return new StoneWallTile();
            case DOWNSTAIRS:
                return new DownStairsTile();
            case UPSTAIRS:
                return new UpStairsTile();
            default:
                return null;
        }
    }

    /**
     * Sets the position of this Tile's sprite.
     * @param x the x position of this Tile.
     * @param y the y position of this Tile.
     */
    public void setPosition(int x, int y) {
        sprite.setPosition(new Vector2f(x, y));
    }

    /**
     * Returns if this Tile is passable by other Tiles or not.
     * @return <code>true</code> if this Tile is passable by other Tiles,
     * <code>false</code> if not.
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * Sets if this Tile is passable or not.
     * @param passable the state to change this Tile's passable field to.
     */
    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(sprite);
    }
}
