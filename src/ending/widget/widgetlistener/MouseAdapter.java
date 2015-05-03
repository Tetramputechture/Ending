package ending.widget.widgetlistener;

import ending.widget.Button;

/**
 * A convenience class that defaults all methods of MouseListener to do nothing.
 *
 * @author Nick
 */
public abstract class MouseAdapter implements MouseListener {

    @Override
    public void mouseClicked(Button b) {
    }

    @Override
    public void mouseReleased(Button b) {
    }

    @Override
    public void mouseEntered(Button b) {
    }

    @Override
    public void mouseExited(Button b) {
    }

}
