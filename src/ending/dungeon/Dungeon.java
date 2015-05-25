package ending.dungeon;

import ending.entity.Entity;
import ending.tile.Tile;
import ending.tile.TileType;
import ending.vector.Vector2i;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;

/**
 * Holds all tile data within a map, and stores utility functions for the
 * Dungeon generator.
 *
 * @author Nick
 */
public final class Dungeon {
    
    private final DungeonStyle dungeonStyle;

    private final Vector2i size;

    private final Tile[][] tileData;
    
    private final Tile[][] detailData;
    
    private final Entity[][] entityData;
    
    private boolean compiled;

    /**
     * Constructs a new Dungeon, with all tiles initially set to
     * <code>Tile.UNUSED</code>.
     *
     * @param size how many tiles this Dungeon will have, specified by (number
     * of columns, number of rows).
     */
    public Dungeon(DungeonStyle dungeonStyle, Vector2i size) {
        this.dungeonStyle = dungeonStyle;
        this.size = size;
        tileData = new Tile[size.x][size.y];
        detailData = new Tile[size.x][size.y];
        
        TileType unused = dungeonStyle.getUnusedTileType();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                addTile(x, y, unused);
            }
        }
        entityData = new Entity[size.x][size.y];
    }
    
    public DungeonStyle getDungeonStyle() {
        return dungeonStyle;
    }
    
    public Vector2i getSize() {
        return size;
    }

    /**
     * Sets a cell to a Tile.
     *
     * @param x the x coordinate of the cell to be set.
     * @param y the y coordinate of the cell to be set.
     * @param tileType the TileType to set this cell to.
     */
    public void addTile(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";
        
        Tile tile = new Tile(tileType);

        tileData[x][y] = tile;
    }
    
    /**
     * Sets a cell to a Detail Tile (drawn over entities)
     *
     * @param x the x coordinate of the cell to be set.
     * @param y the y coordinate of the cell to be set.
     * @param tileType the Tile to set this cell to.
     */
    public void addDetail(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";
        detailData[x][y] = new Tile(tileType);
    }
    
    public void pushTile(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";
        
        Tile tile = new Tile(tileType);
        
        if (tileData[x][y] == null) {
            tileData[x][y] = tile;
        } else {
            tileData[x][y].pushTile(tile);
        }
    }
    
    public void pushDetail(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";
        
        Tile tile = new Tile(tileType);
        
        if (detailData[x][y] == null) {
            detailData[x][y] = tile;
        } else {
            detailData[x][y].pushTile(tile);
        }
    }
    
    /**
     * Sets a range of cells to a tile.
     *
     * @param xStart the starting x value of the range.
     * @param yStart the starting y value of the range.
     * @param xEnd the ending x value of the range.
     * @param yEnd the ending y value of the range.
     * @param tileType the tile type to set the range of cells to.
     */
    public void addTiles(int xStart, int yStart, int xEnd, int yEnd, TileType tileType) {
        assert(isRangeInBounds(xStart, yStart, xEnd, yEnd)) : "Specified bounds invalid!";
        
        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                addTile(x, y, tileType);
            }
        }
    }
    
    /**
     * Gets the tile type of a cell.
     *
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     * @return the tile of the cell.
     */
    public Tile getTile(int x, int y) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        return tileData[x][y];
    }
    
    public boolean getTileTypeEquals(int x, int y, TileType tileType) {
        return getTile(x, y).getTileType() == tileType;
    }

    public boolean holdsTileType(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";

        if (tileData[x][y].getTileType() == tileType) {
            return true;
        } else {
            for (Tile t : tileData[x][y].getChildren()) {
                if (t.getTileType() == tileType) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean holdsDetailType(int x, int y, TileType tileType) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";
        
        if (detailData[x][y] == null) {
            return false;
        }

        if (detailData[x][y].getTileType() == tileType) {
            return true;
        } else {
            for (Tile t : detailData[x][y].getChildren()) {
                if (t.getTileType() == tileType) {
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
     * Returns if an integer is within this Dungeon's number of columns.
     *
     * @param x the x coordinate to be checked.
     * @return <code>true</code> if 0 &lt= x &lt= Dungeon Columns,
     * <code>false</code> otherwise.
     */
    public boolean isXInBounds(int x) {
        return x >= 0 && x < tileData.length;
    }

    /**
     * Returns if an integer is within this Dungeon's number of rows.
     *
     * @param y the y coordinate to be checked.
     * @return true if 0 &lt= x &lt= Dungeon Rows, false otherwise.
     */
    public boolean isYInBounds(int y) {
        return y >= 0 && y < tileData[0].length;
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
        return containsOnly(xStart, yStart, xEnd, yEnd, dungeonStyle.getUnusedTileType());
    }
    
    /**
     * If a range of cells if of a specified tile type.
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
                if (getTile(x, y).getTileType() != tileType) {
                    return false;
                }
            }
        }
        
        return true;  
    }

    /**
     * Returns if a cell is adjacent to a cell of a certain tile.
     *
     * @param x the x coordinate of the cell.
     * @param y the y coordinate of the cell.
     * @param tileType the tile  to be checked if the cell is adjacent to.
     * @return <code>true</code> if the cell has another cell of type
     * <code>tileType</code> adjacent (to the first cell north, east, south, or
     * west) to it, <code>false</code> otherwise
     */
    public boolean isAdjacent(int x, int y, TileType tileType) {
        assert (isXInBounds(x - 1) && isXInBounds(x + 1));
        assert (isYInBounds(y - 1) && isYInBounds(y + 1));

        return holdsTileType(x - 1, y, tileType) || holdsTileType(x + 1, y, tileType)
                || holdsTileType(x, y - 1, tileType) || holdsTileType(x, y + 1, tileType);
    }
    
    public void addEntity(int x, int y, Entity a) {
        assert (isXInBounds(x) && isYInBounds(y)) : "Coordinates of cell out of bounds!";
        entityData[x][y] = a;
    }
    
    private void compile() {
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                int posX = x * Tile.TILE_WIDTH;
                int posY = y * Tile.TILE_HEIGHT;
                
                tileData[x][y].setPosition(posX, posY);
                
                Entity e = entityData[x][y];
                if (e != null) {
                    e.setPosition(posX, posY);
                }
                
                Tile d = detailData[x][y];
                if (d != null) {
                    d.setPosition(posX, posY);
                }
            }
        }
        compiled = true;
    }
    
    private void drawBaseTiles(RenderTarget rt, RenderStates states) {
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                if (tileData[x][y] != null) {
                    rt.draw(tileData[x][y]);
                }
            }
        }
    }
    
    private void drawDetailTiles(RenderTarget rt, RenderStates states) {
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                if (detailData[x][y] != null) {
                    rt.draw(detailData[x][y]);
                }
            }
        }
    }
    
    private void updateEntities(Time deltaTime, RenderTarget rt) {
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                if (entityData[x][y] != null) {
                    entityData[x][y].update(deltaTime, rt, this);
                }
            }
        }
    }
    
    public void update(Time deltaTime, RenderTarget rt, RenderStates states) {
        if (!compiled) {
            compile();
        }
        drawBaseTiles(rt, states);
        updateEntities(deltaTime, rt);
        drawDetailTiles(rt, states);
    }
}
