package ending.dungeon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Direction {

    NORTH, SOUTH, EAST, WEST;
    
    private static final List<Direction> values = Arrays.asList(values());
    
    public static Direction randDirection(Random rand) {
        return values.get(rand.nextInt(values.size()));
    }
}
