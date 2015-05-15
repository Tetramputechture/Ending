package ending.input;

/**
 *
 * @author Nick
 */
public interface MouseListener extends Listener {

    /**
     * Called when a mouse button is clicked.
     */
    void mouseClicked();
    
    /**
     * Called when the mouse is moved.
     */
    void mouseMoved();

    /**
     * Called when the mouse enters a widget's bounding rectangle.

     */
    void mouseEntered();

    /**
     * Called when the mouse exits a widget's bounding rectangle.
     *
     * @param m the mouseEvent that occurred.
     */
    void mouseExited();
}
