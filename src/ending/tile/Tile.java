package ending.tile;

import ending.dungeon.Direction;
import static ending.dungeon.Direction.EAST;
import static ending.dungeon.Direction.SOUTH;
import static ending.dungeon.Direction.WEST;
import ending.util.SpriteUtils;
import java.util.Stack;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Transform;
import org.jsfml.system.Vector2f;

/**
 * A Tile that encapsulates every object within a Dungeon.
 * @author Nick
 */
public class Tile implements Drawable {
    
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
    private final Sprite sprite;
    
    /**
     * The Texture of this Tile.
     */
    private final TileType tileType;
    
    private Transform transform;

    private final Stack<Tile> children;
    
    private boolean passable;

    /**
     * Initializes the Sprite and children of this Tile, 
     * and sets its Passable field to false.
     */
    public Tile(TileType texture, boolean isPassable) {
        this.tileType= texture;
        this.passable = isPassable;
        sprite = new Sprite(texture.getTexture());
        transform = new Transform();
        children = new Stack<>();
    }
    
    public TileType getTileType() {
        return tileType;
    }
    
    public Vector2f getPosition() {
        return sprite.getPosition();
    }
    
    public float getRotation() {
        return sprite.getRotation();
    }
    
    public FloatRect getGlobalBounds() {
        return sprite.getGlobalBounds();
    }
    
    public void rotateAroundCenter(float degrees) {
        ending.vector.Vector2f center = SpriteUtils.getTextureCenter(tileType.getTexture());
        ending.vector.Vector2f rotateOrigin = new ending.vector.Vector2f(sprite.getPosition()).add(center);
        transform = Transform.rotate(transform, degrees, rotateOrigin.x, rotateOrigin.y);
    }
    
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
    
    @Override
    public boolean equals(Object o) {
        
        if (o == null) {
            return false;
        }
        
        if (o.getClass() != getClass()) {
            return false;
        }
        
        Tile t = (Tile) o;
        
        return tileType == t.tileType && passable == t.passable;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + tileType.hashCode();
        hash = 37 * hash + (passable ? 1 : 0);
        return hash;
    }
}
