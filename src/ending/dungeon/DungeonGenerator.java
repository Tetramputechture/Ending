package ending.dungeon;

import ending.tile.TileType;
import ending.vector.Vector2i;
import java.util.Random;

/**
 *
 * @author Nick
 */
public class DungeonGenerator {

    private long seed;

    private Vector2i size;

    private final int maxFeatures;

    private final int chanceRoom;

    public DungeonGenerator() {
        seed = System.currentTimeMillis();
        size = new Vector2i(80, 25);
        maxFeatures = 200;
        chanceRoom = 75;
    }
    
    public void setSeed(long seed) {
        this.seed = seed;
    }
    
    public Dungeon generate(int x, int y) {
        Random rand = new Random(seed);
        size = new Vector2i(x, y);
        Dungeon dungeon = new Dungeon(size);

        makeDungeon(dungeon, rand);

        return dungeon;
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
            int x, int y,
            int maxLength,
            Direction direction) {

        assert (x >= 0 && x < size.x);
        assert (y >= 0 && y < size.y);

        assert (maxLength > 0 && maxLength <= Math.max(size.x, size.y));

        int length = randInt(rand, 2, maxLength);

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

        return true;
    }

    private boolean makeRoom(Dungeon dungeon,
            Random rand,
            int x,
            int y,
            int xMaxLength,
            int yMaxLength,
            Direction direction) {

        int xLength = randInt(rand, 4, xMaxLength);
        int yLength = randInt(rand, 4, yMaxLength);

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
            if (makeRoom(dungeon, rand, x + xmod, y + ymod, 8, 6, direction)) {
                dungeon.setCell(x, y, TileType.DOOR);
                dungeon.setCell(x + xmod, y + ymod, TileType.STONEFLOOR);
                return true;
            }
            return false;
        } else {
            if (makeCorridor(dungeon, rand, x + xmod, y + ymod, 6, direction)) {
                dungeon.setCell(x, y, TileType.DOOR);
                return true;
            }
            return false;
        }
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

            TileType cellXY = dungeon.getCell(x, y);
            TileType cellXPlusOneY = dungeon.getCell(x + 1, y);
            TileType cellXMinusOneY = dungeon.getCell(x - 1, y);
            TileType cellXYPlusOne = dungeon.getCell(x, y + 1);
            TileType cellXYMinusOne = dungeon.getCell(x, y - 1);

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

    private boolean makeSpecialTile(Dungeon dungeon, Random rand, TileType tileType) {
        int maxTries = 10000;

        for (int tries = 0; tries < maxTries; tries++) {
            int x = randInt(rand, 1, size.x - 2);
            int y = randInt(rand, 1, size.y - 2);

            if (!(dungeon.isAdjacent(x, y, TileType.STONEFLOOR) || dungeon.isAdjacent(x, y, TileType.STONECORRIDOR))) {
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
        makeRoom(dungeon, rand, size.x / 2, size.y / 2, 8, 6, Direction.randDirection(rand));

        for (int features = 1; features < maxFeatures; features++) {
            if (!makeFeature(dungeon, rand)) {
                break;
            }
        }

        if (!makeSpecialTile(dungeon, rand, TileType.UPSTAIRS)) {
            System.out.println("Unable to place up stairs.");
        }

        if (!makeSpecialTile(dungeon, rand, TileType.DOWNSTAIRS)) {
            System.out.println("Unable to place down stairs");
        }
        
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                if (dungeon.getCell(x, y) == TileType.UNUSED) {
                    dungeon.setCell(x, y, TileType.STONEWALL);
                }
                
            }
        }
        
        double time = (System.nanoTime()-start)/1e9;
        
        System.out.println("Dungeon generated in " + time + " seconds!");

        return true;
    }

}
