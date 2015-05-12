package ending.dungeon;

import ending.actor.Player;
import ending.tile.Tile;
import ending.tile.TileType;
import ending.tile.door.DoorTile;
import ending.tile.floor.StoneFloorTile;
import ending.vector.Vector2i;
import java.util.Random;

/**
 * Generates a Dungeon based on a seed, and saves the seed.
 *
 * @author Nick
 */
public class DungeonGenerator {

    private long seed;

    private Vector2i size;

    private final Vector2i minRoomSize;
    private final Vector2i maxRoomSize;

    private final int minCorridorLength;
    private final int maxCorridorLength;

    private final int maxFeatures;

    private final int chanceRoom;

    /**
     * Sets this DungeonGenerator's seed randomly. To generate with a specific
     * seed, use <code>generate(int x, int y, long seed)</code>.
     */
    public DungeonGenerator() {
        seed = System.currentTimeMillis();
        minRoomSize = new Vector2i(4, 4);
        maxRoomSize = new Vector2i(8, 6);
        minCorridorLength = 2;
        maxCorridorLength = 6;
        maxFeatures = 200;
        chanceRoom = 75;
    }

    /**
     * Gets the Random seed used by this Dungeon Generator.
     *
     * @return the Random seed used by this Dungeon Generator.
     */
    public long getSeed() {
        return seed;
    }

    /**
     * Generates a new Dungeon based on a random seed.
     *
     * @param x the number of columns of the Dungeon.
     * @param y the number of rows of the Dungeon.
     * @return the generated Dungeon.
     */
    public Dungeon generate(int x, int y) {
        Random rand = new Random(seed);
        size = new Vector2i(x, y);
        Dungeon dungeon = new Dungeon(size);

        makeDungeon(dungeon, rand);

        return dungeon;
    }

    /**
     * Generates a new Dungeon based on a seed.
     *
     * @param x the number of columns of the Dungeon.
     * @param y the number of rows of the Dungeon.
     * @param seed the seed of the RNG used by this Dungeon Generator.
     * @return the generated Dungeon.
     */
    public Dungeon generate(int x, int y, long seed) {
        this.seed = seed;
        return generate(x, y);
    }

    private int randInt(Random rand, int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    private boolean isValidArea(Dungeon dungeon, int xStart, int yStart, int xEnd, int yEnd) {
        if (!dungeon.isXInBounds(xStart)
                || !dungeon.isXInBounds(xEnd)
                || !dungeon.isYInBounds(yStart)
                || !dungeon.isYInBounds(yEnd)) {
            return false;
        }

        return dungeon.isAreaUnused(xStart, yStart, xEnd, yEnd);
    }

    private boolean makeCorridor(Dungeon dungeon,
            Random rand,
            int x,
            int y,
            int doorX,
            int doorY,
            Direction direction) {

        assert (x >= 0 && x < size.x);
        assert (y >= 0 && y < size.y);

        int length = randInt(rand, minCorridorLength, maxCorridorLength);

        int xStart = x;
        int yStart = y;

        int xEnd = x;
        int yEnd = y;

        switch (direction) {
            case NORTH:
                yStart = y - length;
                break;
            case EAST:
                xEnd = x + length;
                break;
            case SOUTH:
                yEnd = y + length;
                break;
            case WEST:
                xStart = x - length;
                break;
        }

        if (!isValidArea(dungeon, xStart, yStart, xEnd, yEnd)) {
            return false;
        }

        dungeon.setCells(xStart, yStart, xEnd, yEnd, TileType.STONECORRIDOR);

        makeDoor(dungeon, doorX, doorY, direction);

        return true;
    }

    private boolean makeRoom(Dungeon dungeon,
            Random rand,
            int x,
            int y,
            Direction direction) {

        int xLength = randInt(rand, minRoomSize.x, maxRoomSize.x);
        int yLength = randInt(rand, minRoomSize.y, maxRoomSize.y);

        int xStart = x;
        int yStart = y;

        int xEnd = x;
        int yEnd = y;

        switch (direction) {
            case NORTH:
                yStart = y - yLength;
                xStart = x - xLength / 2;
                xEnd = x + (xLength + 1) / 2;
                break;
            case EAST:
                yStart = y - yLength / 2;
                yEnd = y + (yLength + 1) / 2;
                xEnd = x + xLength;
                break;
            case SOUTH:
                yEnd = y + yLength;
                xStart = x - xLength / 2;
                xEnd = x + (xLength + 1) / 2;
                break;
            case WEST:
                yStart = y - yLength / 2;
                yEnd = y + (yLength + 1) / 2;
                xStart = x - xLength;
                break;
        }

        if (!isValidArea(dungeon, xStart, yStart, xEnd, yEnd)) {
            return false;
        }

        dungeon.setCells(xStart, yStart, xEnd, yEnd, TileType.STONEWALL);
        dungeon.setCells(xStart + 1, yStart + 1, xEnd - 1, yEnd - 1, TileType.STONEFLOOR);

        return true;
    }

    private boolean makeFeature(Dungeon dungeon,
            Random rand,
            int x,
            int y,
            int xmod,
            int ymod,
            Direction direction) {

        int chance = randInt(rand, 0, 100);

        if (chance <= chanceRoom) {
            if (makeRoom(dungeon, rand, x + xmod, y + ymod, direction)) {
                makeDoor(dungeon, x, y, direction);
                dungeon.setCell(x + xmod, y + ymod, TileType.STONEFLOOR);
                return true;
            }
            return false;
        } else {
            return makeCorridor(dungeon, rand, x + xmod, y + ymod, x, y, direction);
        }
    }

    private void makeDoor(Dungeon dungeon, int x, int y, Direction direction) {
        StoneFloorTile floorAndDoor = new StoneFloorTile();
        DoorTile door = new DoorTile();
        door.setPosition(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
        door.rotateBasedOnDirection(direction);
        if (checkDoor(dungeon, x, y, direction)) {
            floorAndDoor.pushTile(door);
        }
        dungeon.setCell(x, y, floorAndDoor);
    }

    private boolean checkDoor(Dungeon dungeon, int x, int y, Direction direction) {
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            if (dungeon.getCellType(x - 1, y) == TileType.STONEWALL
                    && dungeon.getCellType(x + 1, y) == TileType.STONEWALL) {
                return true;
            }
        } else if (dungeon.getCellType(x, y - 1) == TileType.STONEWALL
                && dungeon.getCellType(x, y + 1) == TileType.STONEWALL) {
            return true;
        }
        return false;
    }

    private boolean makeFeature(Dungeon dungeon, Random rand) {
        int maxTries = 1000;

        for (int tries = 0; tries < maxTries; tries++) {
            // Pick a random wall or corridor tile.
            // Make sure it has no adjacent doors (looks weird to have doors next to each other).
            // Find a direction from which it's reachable.
            // Attempt to make a feature (room or corridor) starting at this point.

            int x = randInt(rand, 1, size.x - 2);
            int y = randInt(rand, 1, size.y - 2);

            TileType cellXY = dungeon.getCellType(x, y);
            TileType cellXPlusOneY = dungeon.getCellType(x + 1, y);
            TileType cellXMinusOneY = dungeon.getCellType(x - 1, y);
            TileType cellXYPlusOne = dungeon.getCellType(x, y + 1);
            TileType cellXYMinusOne = dungeon.getCellType(x, y - 1);

            if (!(cellXY == TileType.STONEWALL || cellXY == TileType.STONECORRIDOR)) {
                continue;
            }

            if (dungeon.isAdjacent(x, y, TileType.DOOR)) {
                continue;
            }

            if (cellXYPlusOne == TileType.STONEFLOOR || cellXYPlusOne == TileType.STONECORRIDOR) {
                if (makeFeature(dungeon, rand, x, y, 0, -1, Direction.NORTH)) {
                    return true;
                }
            } else if (cellXMinusOneY == TileType.STONEFLOOR || cellXMinusOneY == TileType.STONECORRIDOR) {
                if (makeFeature(dungeon, rand, x, y, 1, 0, Direction.EAST)) {
                    return true;
                }
            } else if (cellXYMinusOne == TileType.STONEFLOOR || cellXYMinusOne == TileType.STONECORRIDOR) {
                if (makeFeature(dungeon, rand, x, y, 0, 1, Direction.SOUTH)) {
                    return true;
                }
            } else if (cellXPlusOneY == TileType.STONEFLOOR || cellXPlusOneY == TileType.STONECORRIDOR) {
                if (makeFeature(dungeon, rand, x, y, -1, 0, Direction.WEST)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean makeStairs(Dungeon dungeon, Random rand, TileType tileType) {
        int maxTries = 10000;

        for (int tries = 0; tries < maxTries; tries++) {
            int x = randInt(rand, 1, size.x - 2);
            int y = randInt(rand, 1, size.y - 2);

            if (!(dungeon.isAdjacent(x, y, TileType.STONEFLOOR)
                    || dungeon.isAdjacent(x, y, TileType.STONECORRIDOR)
                    && dungeon.isAdjacent(x, y, TileType.STONEWALL))) {
                continue;
            }

            if (dungeon.isAdjacent(x, y, TileType.DOOR)) {
                continue;
            }

            dungeon.setCell(x, y, tileType);

            return true;
        }
        return false;
    }

    private boolean makeDungeon(Dungeon dungeon, Random rand) {

        long start = System.nanoTime();

        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                if (y == 0 || y == size.y - 1 || x == 0 || x == size.x - 1) {
                    dungeon.setCell(x, y, TileType.STONEWALL);
                }
            }
        }

        // Make one room in the middle to start things off.
        makeRoom(dungeon, rand, size.x / 2, size.y / 2, Direction.randDirection(rand));

        for (int features = 1; features < maxFeatures; features++) {
            if (!makeFeature(dungeon, rand)) {
                break;
            }
        }

        if (!makeStairs(dungeon, rand, TileType.UPSTAIRS)) {
            System.out.println("Unable to place up stairs.");
        }

        if (!makeStairs(dungeon, rand, TileType.DOWNSTAIRS)) {
            System.out.println("Unable to place down stairs");
        }

        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                if (dungeon.getCellType(x, y) == TileType.UNUSED) {
                    dungeon.setCell(x, y, TileType.STONEWALL);
                }
            }
        }

        double time = (System.nanoTime() - start) / 1e9;

        System.out.println("Dungeon generated in " + time + " seconds with "
                + "seed " + seed);

        return true;
    }

}
