package ending.tile;

import static ending.util.TextureUtils.loadTexture;
import org.jsfml.graphics.Texture;

/**
 *
 * @author Nick
 */
public enum TileType {
    
    UNUSED("sprites/tiles/unused.png", false),
    
    STONEFLOOR("sprites/tiles/stonefloor.png", true),
    
    STONEVOID("sprites/tiles/stonewall.png", false),
    
    STONEWALL_NORTH("sprites/tiles/stonewallnorth.png", false),
    
    STONEWALL_EAST("sprites/tiles/stonewalleast.png", false),
    
    STONEWALL_WEST("sprites/tiles/stonewallwest.png", false),
    
    STONEWALL_SOUTH("sprites/tiles/stonewallsouth.png", true),
    
    UPSTAIRS("sprites/tiles/upstairs.png", false),
    
    DOWNSTAIRS("sprites/tiles/downstairs.png", false);
    
    private final Texture texture;
    private final boolean passable;
    
    public Texture getTexture() {
        return texture;
    }
    
    public boolean isPassable() {
        return passable;
    }
    
    TileType(String textureName, boolean passable) {
        texture = loadTexture(textureName);
        this.passable = passable;
    }
}
