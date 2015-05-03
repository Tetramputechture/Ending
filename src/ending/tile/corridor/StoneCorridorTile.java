package ending.tile.corridor;

import ending.tile.TileType;
import ending.tile.floor.StoneFloorTile;

/**
 * The Stone Corridor Tile, used for Dungeon generation.
 * @author Nick
 */
public class StoneCorridorTile extends StoneFloorTile {
    
    /**
     * @return TileType.STONECORRIDOR.
     */
    public static TileType getType() {
        return TileType.STONECORRIDOR;
    }
    
}
