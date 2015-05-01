package ending.tile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.Texture;

public enum Tile {

    UNUSED,
    DIRTWALL,
    DIRTFLOOR,
    STONEWALL,
    STONEFLOOR,
    CORRIDOR,
    DOOR,
    UPSTAIRS,
    DOWNSTAIRS,
    CHEST;
    
    public static Texture getTexture(String filename) {
        Texture t = new Texture();
        try {
            t.loadFromFile(Paths.get(filename));
        } catch (IOException ex) {
            System.out.println("Image " + filename + " not found! Returning null...");
            return null;
        }
        return t;
    }
    
    public static final Texture UNUSED_TEXTURE = getTexture("sprites/tiles/unused.png");
    public static final Texture DIRTWALL_TEXTURE = getTexture("sprites/tiles/dirtwall.png");
    public static final Texture DIRTFLOOR_TEXTURE = getTexture("sprites/tiles/dirtfloor.png");
    public static final Texture STONEWALL_TEXTURE = getTexture("sprites/tiles/stonewall.png");
    public static final Texture STONEFLOOR_TEXTURE = getTexture("sprites/tiles/stonefloor.png");
    public static final Texture CORRIDOR_TEXTURE = new Texture(STONEFLOOR_TEXTURE);
    public static final Texture DOOR_TEXTURE = getTexture("sprites/tiles/door.png");
    public static final Texture UPSTAIRS_TEXTURE = getTexture("sprites/tiles/upstairs.png");
    public static final Texture DOWNSTAIRS_TEXTURE = getTexture("sprites/tiles/downstairs.png");
};
