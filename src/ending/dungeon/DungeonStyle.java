package ending.dungeon;

import ending.tile.TileType;

/**
 *
 * @author Nick
 */
public interface DungeonStyle {
    
    public TileType getUnusedTileType();
        
    public TileType getVoidTileType();
    
    public TileType getWallTileType(Direction direction);
    
    public TileType getFloorTileType();
    
    public TileType getUpStairsTileType();
    
    public TileType getDownStairsTileType();
    
}
