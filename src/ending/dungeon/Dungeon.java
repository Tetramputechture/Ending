package ending.dungeon;

import ending.actor.Player;
import ending.tile.TileType;
import ending.tile.Tile;
import ending.vector.Vector2i;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 * Holds all tile data within a map, and stores utility functions for the
 * Dungeon generator.
 *
 * @author Nick
 */
public class Dungeon implements Drawable {

    private final Vector2i size;

    private final Tile[][] tileData;
    
    private Player player;

    /**
     * Constructs a new Dungeon, with all tiles initially set to
     * <code>Tile.UNUSED</code>.
     *
     * @param size how many tiles this Dungeon will have, specified by (number
     * of columns, number of rows).
     */
    public Dungeon(Vector2i size) {
        this.size = size;
        tileData = new Tile[size.x][size.y];
        for (int i = 0; i < size.x; i++) {
            for (int j = 0; j < size.y; j++) {
                tileData[i][j] = Tile.getTileFromTileType(TileType.UNUSED);
            }
        }
    }
    
    public void setPlayer(Player p) {
        player = p;
    }
    
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets a cell to a Tile.
     *
     * @param x the x coordinate of the cell to be set.
     * @param y the y coordinate of the cell to be set.
     * @param tile the Tile to set this cell to.
     */
    public void setCell(int x, int y, Tile tile) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        tile.setPosition(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);

        tileData[x][y] = tile;
    }

    /**
     * Sets a cell to a Tile, based on a Tile Type.
     *
     * @param x the x coordinate of the cell to be set.
     * @param y the y coordinate of the cell to be set.
     * @param tileType the type of Tile to set this cell to.
     */
    public void setCell(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        Tile t = Tile.getTileFromTileType(tileType);
        t.setPosition(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);

        tileData[x][y] = t;
    }

    /**
     * Gets the tile type of a cell.
     *
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     * @return the tile of the cell.
     */
    public Tile getCell(int x, int y) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        return tileData[x][y];
    }

    /**
     * Gets the tile type of a cell.
     *
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     * @return the tile type of the cell.
     */
    public TileType getCellType(int x, int y) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        return tileData[x][y].getType();
    }

    public boolean holdsType(int x, int y, TileType type) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        if (tileData[x][y].getType() == type) {
            return true;
        } else {
            for (Tile t : tileData[x][y].getChildren()) {
                if (t.getType() == type) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isRangeInBounds(int xStart, int yStart, int xEnd, int yEnd) {
        return isXInBounds(xStart) && 
                isXInBounds(xEnd) && 
                isYInBounds(yStart) && 
                isYInBounds(yEnd) &&
                xStart <= xEnd && 
                yStart <= yEnd;
    }

    /**
     * Sets a range of cells to a tile.
     *
     * @param xStart the starting x value of the range.
     * @param yStart the starting y value of the range.
     * @param xEnd the ending x value of the range.
     * @param yEnd the ending y value of the range.
     * @param tile the tile to set the range of cells to.
     */
    public void setCells(int xStart, int yStart, int xEnd, int yEnd, Tile tile) {
        assert(isRangeInBounds(xStart, yStart, xEnd, yEnd)) : "Specified bounds invalid!";
        
        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                setCell(x, y, tile);
            }
        }
    }

    /**
     * Sets a range of cells to a Tile, based on a Tile Type.
     *
     * @param xStart the starting x value of the range.
     * @param yStart the starting y value of the range.
     * @param xEnd the ending x value of the range.
     * @param yEnd the ending y value of the range.
     * @param tileType the type of tile to set the range of cells to.
     */
    public void setCells(int xStart, int yStart, int xEnd, int yEnd, TileType tileType) {
        assert(isRangeInBounds(xStart, yStart, xEnd, yEnd)) : "Specified bounds invalid!";

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                setCell(x, y, tileType);
            }
        }
    }

    /**
     * Returns if an integer is within this Dungeon's number of columns.
     *
     * @param x the x coordinate to be checked.
     * @return <code>true</code> if 0 &lt= x &lt= Dungeon Columns,
     * <code>false</code> otherwise.
     */
    public boolean isXInBounds(int x) {
        return x >= 0 && x < size.x;
    }

    /**
     * Returns if an integer is within this Dungeon's number of rows.
     *
     * @param y the y coordinate to be checked.
     * @return true if 0 &lt= x &lt= Dungeon Rows, false otherwise.
     */
    public boolean isYInBounds(int y) {
        return y >= 0 && y < size.y;
    }

    /**
     * Returns if a range of cells is of <code>Tile.UNUSED.</code>
     *
     * @param xStart the starting x value of the range.
     * @param yStart the starting y value of the range.
     * @param xEnd the ending x value of the range.
     * @param yEnd the ending y value of the range.
     * @return <code>true</code> if each cell within the range is of type
     * <code>Tile.UNUSED</code>, <code>false</code> otherwise.
     */
    public boolean isAreaUnused(int xStart, int yStart, int xEnd, int yEnd) {
        return containsOnly(xStart, yStart, xEnd, yEnd, TileType.UNUSED);
    }
    
    /**
     * If a range of cells if of a specified tile.
     * @param xStart the starting x value of the range.
     * @param yStart the starting y value of the range.
     * @param xEnd the ending x value of the range.
     * @param yEnd the ending y value of the range.
     * @param tileType the TileType to check for.
     * @return <code>true</code> if each cell within the range is of type
     * <code>tileType</code>, <code>false</code> otherwise.
     */
    public boolean containsOnly(int xStart, int yStart, int xEnd, int yEnd, TileType tileType) {
        assert(isRangeInBounds(xStart, yStart, xEnd, yEnd)) : "Specified bounds invalid!";
        
        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                if (getCellType(x, y) != tileType) {
                    return false;
                }
            }
        }
        
        return true;  
    }

    /**
     * Returns if a cell is adjacent to a cell of a certain tile type.
     *
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     * @param tileType the tile type to be checked if the cell is adjacent to.
     * @return <code>true</code> if the cell has another cell of type
     * <code>tileType</code> adjacent (to the first cell north, east, south, or
     * west) to it, <code>false</code> otherwise
     */
    public boolean isAdjacent(int x, int y, TileType tileType) {
        assert (isXInBounds(x - 1) && isXInBounds(x + 1));
        assert (isYInBounds(y - 1) && isYInBounds(y + 1));

        return holdsType(x - 1, y, tileType) || holdsType(x + 1, y, tileType)
                || holdsType(x, y - 1, tileType) || holdsType(x, y + 1, tileType);
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                Tile tile = tileData[x][y];
                //tile.setPosition(x*Tile.TILE_WIDTH, y*Tile.TILE_HEIGHT);
                rt.draw(tile);
            }
        }
    }
}
