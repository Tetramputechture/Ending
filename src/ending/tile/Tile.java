package ending.tile;

import ending.actor.Player;
import ending.tile.corridor.StoneCorridorTile;
import ending.tile.door.DoorTile;
import ending.tile.floor.DirtFloorTile;
import ending.tile.floor.StoneFloorTile;
import ending.tile.stairs.DownStairsTile;
import ending.tile.stairs.UpStairsTile;
import ending.tile.wall.DirtWallTile;
import ending.tile.wall.StoneWallTile;
import ending.util.SpriteUtils;
import java.util.Stack;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;

/**
 * A Tile that encapsulates every object within a Dungeon.
 * @author Nick
 */
public abstract class Tile implements Drawable {
    
    /**
     * The width of every Tile.
     */
    public static final int TILE_WIDTH = 32;

    /**
     * The height of every Tile.
     */
    public static final int TILE_HEIGHT = 32;

    /**
     * The Sprite of this Tile.
     */
    protected final Sprite sprite;
    
    private Transform transform;

    private final Stack<Tile> children;
    
    private boolean passable;

    /**
     * Initializes the Sprite and children of this Tile, 
     * and sets its Passable field to false.
     */
    public Tile() {
        sprite = new Sprite();
        transform = new Transform();
        children = new Stack<>();
        passable = false;
    }

    /**
     * Returns the TileType of this Tile.
     * @return the TileType of this tile; all types found in TileTypes.
     */
    public abstract TileType getType();

    /**
     * Returns a new instance of a Tile based on a TileType.
     * @param tileType the type of Tile to return.
     * @return a new Tile of type tileType.
     */
    public static Tile getTileFromTileType(TileType tileType) {
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
     * Gets the X coordinate of this Tile's sprite.
     * @return the x coordinate of this Tile's position.
     */
    public float getPositionX() {
        return sprite.getPosition().x;
    }
    
    /**
     * Gets the Y coordinate of this Tile's sprite.
     * @return the Y coordinate of this Tile's position.
     */
    public float getPositionY() {
        return sprite.getPosition().y;
    }
    
    public Vector2f getPosition() {
        return sprite.getPosition();
    }
    
    public float getRotation() {
        return sprite.getRotation();
    }
    
    public void rotateAroundCenter(float degrees) {
        ending.vector.Vector2f center = SpriteUtils.getTextureCenter(TileTextures.DOORTEXTURE);
        ending.vector.Vector2f rotateOrigin = new ending.vector.Vector2f(sprite.getPosition()).add(center);
        transform = Transform.rotate(transform, degrees, rotateOrigin.x, rotateOrigin.y);
    }

    /**
     * Sets the position of this Tile and its children.
     * @param x the x position of this Tile and its children.
     * @param y the y position of this Tile and its children.
     */
    public void setPosition(int x, int y) {
        sprite.setPosition(new Vector2f(x, y));
        for (Tile t : children) {
            t.setPosition(x, y);
        }
    }
    
    public void move(int x, int y) {
        sprite.move(x, y);
    }
    
    public Stack<Tile> getChildren() {
        return children;
    }
    
    public void pushTile(Tile tile) {
        children.push(tile);
    }
    
    public void pushTile(TileType tileType) {
        children.push(getTileFromTileType(tileType));
    }
    
    public Tile popTile() {
        return children.pop();
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

    public void draw(RenderTarget rt, RenderStates states) {
        RenderStates newStates = new RenderStates(
                states.blendMode,
                Transform.combine(states.transform, transform),
                sprite.getTexture(),
                states.shader);
        
        sprite.draw(rt, newStates);
        
        for (Tile t : children) {
            t.draw(rt, newStates);
        }
    }
}
