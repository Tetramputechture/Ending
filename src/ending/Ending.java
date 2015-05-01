package ending;

import ending.dungeon.Dungeon;
import ending.dungeon.DungeonGenerator;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

/**
 *
 * @author Nick
 */
public class Ending {

    public static void main(String[] args) {

        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(1920, 1080), "Ending");
        
        Dungeon dungeon = new DungeonGenerator().generate(120, 68);

        //Main loop
        while (window.isOpen()) {

            dungeon.draw(window);
            
            window.display();

            //Handle events
            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    //The user pressed the close button
                    window.close();
                }
            }
        }
        
        //dungeon.print();
    }

}
