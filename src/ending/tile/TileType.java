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
    
    STONEWALL("sprites/tiles/stonewall.png"),
    
    DOOR("sprites/tiles/door.png"),
    
    UPSTAIRS("sprites/tiles/upstairs.png"),
    
    DOWNSTAIRS("sprites/tiles/downstairs.png");
    
    private final String path = "sprites/tiles/";
    
    private final Texture texture;
    
    public Texture getTexture() {
        return texture;
    }
    
    TileType(String textureName) {
        texture = loadTexture(textureName);
    }
}
