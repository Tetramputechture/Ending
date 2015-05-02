package ending.dungeon;

import ending.tile.Tile;
import ending.tile.TileType;
import ending.vector.Vector2i;
import org.jsfml.graphics.RenderWindow;

/**
 *
 * @author Nick
 */
public class Dungeon {

    private final Vector2i size;

    private final TileType[] data;

    public Dungeon(Vector2i size) {
        this.size = size;
        data = new TileType[size.x * size.y];
        for (int i = 0; i < data.length; i++) {
            data[i] = TileType.UNUSED;
        }
    }

    public void setCell(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        data[x + size.x * y] = tileType;
    }

    public TileType getCell(int x, int y) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        return data[x + size.x * y];
    }

    public void setCells(int xStart, int yStart, int xEnd, int yEnd, TileType tileType) {
        assert (isXInBounds(xStart) && isXInBounds(xEnd)) : "Coordinates of cell X range out of bounds!";
        assert (isYInBounds(yStart) && isYInBounds(yEnd)) : "Coordinates of cell Y range out of bounds!";

        assert (xStart <= xEnd) : "xStart must be lower than xEnd!";
        assert (yStart <= yEnd) : "yStart must be lower than yEnd!";

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                setCell(x, y, tileType);
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
                if(getCell(x, y) != TileType.UNUSED) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isAdjacent(int x, int y, TileType tileType) {
        assert (isXInBounds(x - 1) && isXInBounds(x + 1));
        assert (isYInBounds(y - 1) && isYInBounds(y + 1));

        return getCell(x - 1, y) == tileType || getCell(x + 1, y) == tileType
                || getCell(x, y - 1) == tileType || getCell(x, y + 1) == tileType;
    }
    
    public void draw(RenderWindow rw) {
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                Tile t = Tile.getTile(getCell(x, y));
                t.setPosition(x*16, y*16);
                rw.draw(t);
            }
        }    
    }
}
