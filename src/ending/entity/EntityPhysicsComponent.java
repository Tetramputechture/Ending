package ending.entity;

import ending.component.PhysicsComponent;
import ending.dungeon.Dungeon;
import ending.tile.Tile;
import ending.vector.Vector2f;
import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Time;

/**
 *
 * @author Nick
 */
public class EntityPhysicsComponent implements PhysicsComponent {

    private static final double SQRT2 = Math.sqrt(2);

    @Override
    public void update(Entity a, Time deltaTime, Dungeon dungeon) {

        Vector2f velocity = a.getVelocity();

        // if entity is moving diagonally, divide vector by sqrt 2
        if (velocity.x != 0 && velocity.y != 0) {
            velocity.x /= SQRT2;
            velocity.y /= SQRT2;
        }

        a.move(velocity.scl(deltaTime.asSeconds()).toVector2f());
        
        // see if entity is colliding with an unpassable tile
        Tile[][] tiles = dungeon.getTiles();
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                Tile t = tiles[y][x];
                if (!t.isPassable()) {
                    FloatRect intersection = a.getBoundingRect().intersection(t.getGlobalBounds());
                    if (intersection != null) {
                        
                    }   
                }
            }
        }
        
        // set velocity back to 0 for next frame
        velocity.x = 0;
        velocity.y = 0;
    }
}
