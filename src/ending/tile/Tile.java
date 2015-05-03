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
 *
 * @author Nick
 */
public abstract class Tile implements Drawable {

    protected final Sprite sprite;

    private boolean passable;

    public Tile() {
        sprite = new Sprite();
        passable = false;
    }

    public static TileType getType() {
        return null;
    }

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

    public void setPosition(int x, int y) {
        sprite.setPosition(new Vector2f(x, y));
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(sprite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Tile other = (Tile) o;

        return getType().equals(other.getType());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.sprite);
        hash = 59 * hash + (this.passable ? 1 : 0);
        return hash;
    }

}
