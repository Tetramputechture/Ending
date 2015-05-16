package ending.dungeon;

import ending.tile.Tile;
import ending.tile.TileType;

/**
 *
 * @author Nick
 */
public class StoneDungeonStyle implements DungeonStyle {
    

    @Override
    public Tile getUnusedTile() {
        return new Tile(TileType.UNUSED, false);
    }

    @Override
    public Tile getFloorTile() {
        return new Tile(TileType.STONEFLOOR, true);
    }

    @Override
    public Tile getCorridorTile() {
        return getFloorTile();
    }

    @Override
    public Tile getWallTile() {
        return new Tile(TileType.STONEWALL, false);
    }

    @Override
    public Tile getDoorTile() {
        return new Tile(TileType.DOOR, false);
    }

    @Override
    public Tile getUpStairsTile() {
        return new Tile(TileType.UPSTAIRS, false);
    }

    @Override
    public Tile getDownStairsTile() {
        return new Tile(TileType.DOWNSTAIRS, false);
    }
    
}
