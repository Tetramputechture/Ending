package ending.dungeon;

import ending.tile.Tile;
import ending.tile.TileType;
import ending.vector.Vector2i;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 *
 * @author Nick
 */
public class Dungeon implements Drawable {

    private final Vector2i size;

    private final TileType[][] tileTypeData;
    
    private final Tile[][] tileData;

    public Dungeon(Vector2i size) {
        this.size = size;
        tileTypeData = new TileType[size.x][size.y];
        for (int i = 0; i < size.x; i++) {
            for (int j = 0; j < size.y; j++) {
                tileTypeData[i][j] = TileType.UNUSED;
            }
        }
        tileData = new Tile[size.x][size.y];
    }

    public void setCellType(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        tileTypeData[x][y] = tileType;
    }

    public TileType getCellType(int x, int y) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        return tileTypeData[x][y];
    }

    public void setCellsType(int xStart, int yStart, int xEnd, int yEnd, TileType tileType) {
        assert (isXInBounds(xStart) && isXInBounds(xEnd)) : "Coordinates of cell X range out of bounds!";
        assert (isYInBounds(yStart) && isYInBounds(yEnd)) : "Coordinates of cell Y range out of bounds!";

        assert (xStart <= xEnd) : "xStart must be lower than xEnd!";
        assert (yStart <= yEnd) : "yStart must be lower than yEnd!";

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                setCellType(x, y, tileType);
            }
        }
    }

    public boolean isXInBounds(int x) {
        return x >= 0 && x < size.x;
    }

    public boolean isYInBounds(int y) {
        return y >= 0 && y < size.y;
    }

    public boolean isAreaUnused(int xStart, int yStart, int xEnd, int yEnd) {
        assert (isXInBounds(xStart) && isXInBounds(xEnd)) : "Coordinates of cell X range out of bounds!";
        assert (isYInBounds(yStart) && isYInBounds(yEnd)) : "Coordinates of cell Y range out of bounds!";

        assert (xStart <= xEnd) : "xStart must be lower than xEnd!";
        assert (yStart <= yEnd) : "yStart must be lower than yEnd!";

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                if(getCellType(x, y) != TileType.UNUSED) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isAdjacent(int x, int y, TileType tileType) {
        assert (isXInBounds(x - 1) && isXInBounds(x + 1));
        assert (isYInBounds(y - 1) && isYInBounds(y + 1));

        return getCellType(x - 1, y) == tileType || getCellType(x + 1, y) == tileType
                || getCellType(x, y - 1) == tileType || getCellType(x, y + 1) == tileType;
    }
    
    public void compile() {
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                Tile t = Tile.getTile(getCellType(x, y));
                t.setPosition(x*16, y*16);
                tileData[x][y] = t;
            }
        }
    }
    
    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        assert tileData[0][0] == null : "compile() must be called before drawing!";
        
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                rt.draw(tileData[x][y]);
            }
        }    
    }
}
