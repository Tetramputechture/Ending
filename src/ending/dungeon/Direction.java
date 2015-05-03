package ending.dungeon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * An enum to handle the game's cardinal directions. 
 * @author Nick
 */
public enum Direction {

    /**
     * The upward direction.
     */
    NORTH,

    /**
     * The downward direction.
     */
    SOUTH,

    /**
     * The leftward direction.
     */
    EAST,

    /**
     * The rightward direction.
     */
    WEST;
    
    private static final List<Direction> values = Arrays.asList(values());
    
    /**
     * Returns a random direction.
     * @param rand the Random object to generate the random direction.
     * @return a random direction; each direction has a 25% chance of being picked.
     */
    public static Direction randDirection(Random rand) {
        return values.get(rand.nextInt(values.size()));
    }
}
