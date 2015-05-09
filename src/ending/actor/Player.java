package ending.actor;

import ending.tile.Tile;
import ending.tile.TileTextures;
import ending.tile.TileType;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 *
 * @author Nick
 */
public class Player extends Tile {
    
    public Player() {
        sprite.setTexture(TileTextures.PLAYERTEXTURE);
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(sprite);
    }

    @Override
    public TileType getType() {
        return TileType.PLAYER;
    }
}
