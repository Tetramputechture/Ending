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
    public void update(Entity e, Time deltaTime, Dungeon dungeon) {

        Vector2f pos = new Vector2f(e.getPosition());
        Vector2f velocity = e.getVelocity();
        FloatRect bounds = e.getGeometryBoundingBox();

        // if entity is moving diagonally, divide vector by sqrt 2
        if (velocity.x != 0 && velocity.y != 0) {
            velocity.x /= SQRT2;
            velocity.y /= SQRT2;
        }

        e.move(velocity.scl(deltaTime.asSeconds()).toVector2f());
        
        // see if entity is colliding with an unpassable tile
        for (int x = 0; x < dungeon.getSize().x; x++) {
            for (int y = 0; y < dungeon.getSize().y; y++) {
                Tile t = dungeon.getTile(x, y);
                if (!t.isPassable()) {
                    FloatRect area = bounds.intersection(t.getGlobalBounds());
                    if (area != null) {
                        
                        // vertical collision
                        if (area.width > area.height && area.height != bounds.height) {
                            
                            // top
                            if (area.contains(area.left, bounds.top)) {
                                e.setPosition(pos.x, pos.y + area.height);
                            // down
                            } else {
                                e.setPosition(pos.x, pos.y - area.height);
                            }
                            
                        // horizontal collision
                        } else if (area.width < area.height || area.height == bounds.height) {
                            
                            // right
                            if (area.contains(bounds.left + bounds.width - 0.0001f, area.top + 1)) {
                                e.setPosition(pos.x - area.width, pos.y);
                            // left
                            } else {
                                e.setPosition(pos.x + area.width, pos.y);
                            }
                        }
                    }   
                }
            }
        }
        
        // set velocity back to 0 for next frame
        velocity.x = 0;
        velocity.y = 0;
    }
}
