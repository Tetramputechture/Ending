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
    public Tile getVoidTile() {
        return new Tile(TileType.STONEVOID, false);
    }
    
    @Override
    public Tile getWallTile(Direction direction) {
        switch (direction) {
            case NORTH:
                return new Tile(TileType.STONEWALL_NORTH, false);
            case WEST:
                return new Tile(TileType.STONEWALL_WEST, false);
            case EAST:
                return new Tile(TileType.STONEWALL_EAST, false);
            case SOUTH:
                return new Tile(TileType.STONEWALL_SOUTH, false);
            default:
                return new Tile(TileType.STONEWALL_NORTH, false);
        }
    }
    
    @Override
    public Tile getFloorTile() {
        return new Tile(TileType.STONEFLOOR, true);
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
