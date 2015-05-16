package ending.dungeon;

import ending.tile.Tile;

/**
 *
 * @author Nick
 */
public interface DungeonStyle {
    
    public Tile getUnusedTile();
    
    public Tile getFloorTile();
    
    public Tile getCorridorTile();
    
    public Tile getWallTile();
    
    public Tile getDoorTile();
    
    public Tile getUpStairsTile();
    
    public Tile getDownStairsTile();
    
}
