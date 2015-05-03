package ending.dungeon;

import ending.tile.Tile;
import ending.tile.TileType;
import ending.vector.Vector2i;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 * Holds all tile data within a map, and stores utility functions
 * for the Dungeon generator.
 * @author Nick
 */
public class Dungeon implements Drawable {

    private final Vector2i size;

    private final TileType[][] tileTypeData;
    
    private final Tile[][] tileData;

    /**
     * Constructs a new Dungeon, with all tiles initially set to
     * <code>TileType.UNUSED</code>.
     * @param size how many tiles this Dungeon will have, specified by
     * (number of columns, number of rows).
     */
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

    /**
     * Sets a cell to a tile type.
     * @param x the x coordinate of the cell to be set.
     * @param y the y coordinate of the cell to be set.
     * @param tileType the tile type to set the cell to.
     */
    public void setCellType(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        tileTypeData[x][y] = tileType;
    }

    /**
     * Gets the tile type of a cell.
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     * @return the tile type of the cell.
     */
    public TileType getCellType(int x, int y) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        return tileTypeData[x][y];
    }

    /**
     * Sets a range of cells to a tile type.
     * @param xStart the starting x value of the range.
     * @param yStart the starting y value of the range.
     * @param xEnd the ending x value of the range.
     * @param yEnd the ending y value of the range. 
     * @param tileType the tile type to set the range of cells to.
     */
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

    /**
     * Returns if an integer is within this Dungeon's number of columns.
     * @param x the x coordinate to be checked.
     * @return <code>true</code> if 0 &lt= x &lt= Dungeon Columns, <code>false</code> otherwise.
     */
    public boolean isXInBounds(int x) {
        return x >= 0 && x < size.x;
    }

    /**
     * Returns if an integer is within this Dungeon's number of rows.
     * @param y the y coordinate to be checked.
     * @return true if 0 &lt= x &lt= Dungeon Rows, false otherwise.
     */
    public boolean isYInBounds(int y) {
        return y >= 0 && y < size.y;
    }

    /**
     * Returns if a range of cells is of <code>TileType.UNUSED.</code>
     * @param xStart the starting x value of the range.
     * @param yStart the starting y value of the range.
     * @param xEnd the ending x value of the range.
     * @param yEnd the ending y value of the range. 
     * @return <code>true</code> if each cell within the range is of type 
     * <code>TileType.UNUSED</code>, <code>false</code> otherwise.
     */
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

    /**
     * Returns if a cell is adjacent to a cell of a certain tile type.
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     * @param tileType the tile type to be checked if the cell is adjacent to.
     * @return <code>true</code> if the cell has another cell of type <code>tileType</code> 
     * adjacent (to the first cell north, east, south, or west) to it, 
     * <code>false</code> otherwise
     */
    public boolean isAdjacent(int x, int y, TileType tileType) {
        assert (isXInBounds(x - 1) && isXInBounds(x + 1));
        assert (isYInBounds(y - 1) && isYInBounds(y + 1));

        return getCellType(x - 1, y) == tileType || getCellType(x + 1, y) == tileType
                || getCellType(x, y - 1) == tileType || getCellType(x, y + 1) == tileType;
    }
    
    /**
     * Compiles the tile type data into Tile class data.
     */
    public void compile() {
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                Tile t = Tile.getTile(getCellType(x, y));
                t.setPosition(x*Tile.TILE_WIDTH, y*Tile.TILE_HEIGHT);
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
