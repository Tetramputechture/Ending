package ending.dungeon;

import ending.tile.TileType;

/**
 *
 * @author Nick
 */
public class StoneDungeonStyle implements DungeonStyle {

    @Override
    public TileType getUnusedTileType() {
        return TileType.UNUSED;
    }

    @Override
    public TileType getVoidTileType() {
        return TileType.STONEVOID;
    }

    @Override
    public TileType getWallTileType(Direction direction) {
        switch (direction) {
            case NORTH:
                return TileType.STONEWALL_NORTH;
            case EAST:
                return TileType.STONEWALL_EAST;
            case WEST:
                return TileType.STONEWALL_WEST;
            case SOUTH:
                return TileType.STONEWALL_SOUTH;
            default:
                return TileType.STONEWALL_NORTH;
        }
    }

    @Override
    public TileType getFloorTileType() {
        return TileType.STONEFLOOR;
    }

    @Override
    public TileType getUpStairsTileType() {
        return TileType.UPSTAIRS;
    }

    @Override
    public TileType getDownStairsTileType() {
        return TileType.DOWNSTAIRS;
    }
    
}
