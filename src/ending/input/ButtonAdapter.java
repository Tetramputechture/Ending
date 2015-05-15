package ending.input;

import ending.action.Action;
import ending.audio.AudioHandler;
import ending.widget.Button;
import org.jsfml.graphics.Color;

/**
 * A CMouseListener that can play a sound and change color on mouse entrance,
 * and performs an Action on click.
 *
 * @author Nick
 */
public class ButtonAdapter extends MouseAdapter {
    
    private final Button button;

    private final Action clickAction;
    private final String soundFileName;
    private final Color entranceColor;

    private final Color exitColor;

    private boolean shouldPlaySound;

    /**
     * Constructs a new CButtonAdapater.
     *
     * @param button the Button to respond to this Listener.
     * @param clickAction the Action to perform on click.
     * @param soundFileName the Sound to play when the mouse enters the label.
     * Null for no sound.
     * @param entranceColor the Color for the button to change when the mouse
     * enters the label. Null for no color change.
     * @param exitColor the Color for the button to change when the mouse exits
     * the Label. Null for no color change.
     */
    public ButtonAdapter(Button button,
            Action clickAction,
            String soundFileName,
            Color entranceColor,
            Color exitColor) {
        
        this.button = button;
        this.clickAction = clickAction;
        this.soundFileName = soundFileName;
        this.entranceColor = entranceColor;
        this.exitColor = exitColor;
    }

    @Override
    public void mouseClicked() {
        clickAction.execute();
    }

    @Override
    public void mouseEntered() {
        if (shouldPlaySound && soundFileName != null) {
            new AudioHandler().playSound(soundFileName, 1f);
        }
        shouldPlaySound = false;
        if (entranceColor != null) {
            button.getLabel().setColor(entranceColor);
        }
    }

    @Override
    public void mouseExited() {
        if (exitColor != null) {
            button.getLabel().setColor(exitColor);
        }
        shouldPlaySound = true;
    }
}
