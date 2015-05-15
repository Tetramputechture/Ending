package ending.tile;

import static ending.util.TextureUtils.*;
import org.jsfml.graphics.Texture;


/**
 * Stores all Tile textures.
 * @author Nick
 */
public class TileTextures {
    
    /**
     * The texture of the Unused tile.
     */
    public static final Texture UNUSEDTEXTURE = loadTexture("sprites/tiles/unused.png");

    /**
     * The texture of the Stone Wall tile.
     */
    public static final Texture STONEWALLTEXTURE = loadTexture("sprites/tiles/stonewall.png");

    /**
     * The texture of the Stone Floor tile.
     */
    public static final Texture STONEFLOORTEXTURE = loadTexture("sprites/tiles/stonefloor.png");

    /**
     * The texture of the Door tile.
     */
    public static final Texture DOORTEXTURE = loadTexture("sprites/tiles/door.png");

    /**
     * The texture of the Up Stairs tile.
     */
    public static final Texture UPSTAIRSTEXTURE = loadTexture("sprites/tiles/upstairs.png");

    /**
     * The texture of the Down Stairs tile.
     */
    public static final Texture DOWNSTAIRSTEXTURE = loadTexture("sprites/tiles/downstairs.png");
}
