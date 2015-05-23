package ending.tile;

import static ending.util.TextureUtils.loadTexture;
import org.jsfml.graphics.Texture;

/**
 *
 * @author Nick
 */
public enum TileType {
    
    UNUSED("sprites/tiles/unused.png"),
    
    STONEFLOOR("sprites/tiles/stonefloor.png"),
    
    STONEVOID("sprites/tiles/stonewall.png"),
    
    STONEWALL_NORTH("sprites/tiles/stonewallnorth.png"),
    
    STONEWALL_EAST("sprites/tiles/stonewalleast.png"),
    
    STONEWALL_WEST("sprites/tiles/stonewallwest.png"),
    
    STONEWALL_SOUTH("sprites/tiles/stonewallsouth.png"),
    
    DOOR("sprites/tiles/door.png"),
    
    UPSTAIRS("sprites/tiles/upstairs.png"),
    
    DOWNSTAIRS("sprites/tiles/downstairs.png");
    
    private final Texture texture;
    
    public Texture getTexture() {
        return texture;
    }
    
    TileType(String textureName) {
        texture = loadTexture(textureName);
    }
}
