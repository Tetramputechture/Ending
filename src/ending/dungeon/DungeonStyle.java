package ending.dungeon;

import ending.tile.Tile;

/**
 *
 * @author Nick
 */
public interface DungeonStyle {
    
    public Tile getUnusedTile();
        
    public Tile getVoidTile();
    
    public Tile getWallTile(Direction direction);
    
    public Tile getFloorTile();
    
    public Tile getDoorTile();
    
    public Tile getUpStairsTile();
    
    public Tile getDownStairsTile();
    
}
