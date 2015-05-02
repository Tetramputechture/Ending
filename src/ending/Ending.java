package ending;

import ending.dungeon.Dungeon;
import ending.dungeon.DungeonGenerator;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

/**
 *
 * @author Nick
 */
public class Ending {

    public static void main(String[] args) {

        RenderWindow window = new RenderWindow();
        window.create(new VideoMode(640, 480), "Ending");
        
        Dungeon dungeon = new DungeonGenerator().generate(40, 30);
        
        View view = new View();
        
        view.setSize(256, 256);
        
        view.setCenter(640/2, 480/2);

        //Main loop
        while (window.isOpen()) {
            
            //window.setView(view);
            

            dungeon.draw(window);
            

            window.display();

            //Handle events
            for (Event event : window.pollEvents()) {
                if (event.type == Event.Type.CLOSED) {
                    //The user pressed the close button
                    window.close();
                }
                if (event.type == Event.Type.KEY_PRESSED) {
                    KeyEvent k = event.asKeyEvent();
                    switch (k.key) {
                        case A:
                            view.move(new Vector2f(-3, 0));
                            break;
                        case D:
                            view.move(new Vector2f(3, 0));
                            break;
                    }
                }
            }
        }
        
        //dungeon.print();
    }

}
