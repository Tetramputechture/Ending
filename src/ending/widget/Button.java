package ending.widget;

import ending.input.InputHandler;
import ending.input.MouseListener;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import ending.vector.Vector2f;
import ending.vector.Vector2i;
import org.jsfml.window.Mouse;

/**
 * A Widget that has a Label and a Frame, surrounding the Label.
 *
 * @author Nick
 */
public class Button extends Widget {

    /**
     * The Label of this Button.
     */
    private Label label;

    /**
     * The Frame of this Button.
     */
    private final Frame frame;

    private MouseListener mouseListener;

    /**
     * Constructs a new CButton with a white border and transparent background.
     *
     * @param text the Text of the button.
     */
    public Button(Text text) {
        label = new Label(text);
        frame = new Frame(new RectangleShape(), Color.TRANSPARENT);

        updateFrame();
    }

    @Override
    public void update() {
        if (mouseListener != null) {
            Vector2i pos = InputHandler.getMousePosition();
            if (pos != null && frame.contains(InputHandler.getMousePosition())) {
                mouseListener.mouseEntered();
                if (InputHandler.isMouseButtonPressed(Mouse.Button.LEFT)) {
                    mouseListener.mouseClicked();
                }
            } else {
                mouseListener.mouseExited();
            }
        }
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.draw(label);
        rt.draw(frame);
    }

    public void addMouseListener(MouseListener l) {
        mouseListener = l;
    }

    private void updateFrame() {
        RectangleShape buttonBorder = new RectangleShape();
        FloatRect textRect = label.getTextObject().getGlobalBounds();

        buttonBorder.setPosition(textRect.left, textRect.top);
        buttonBorder.setSize(new Vector2f(textRect.width + 10, textRect.height + 10).toVector2f());
        buttonBorder.setFillColor(Color.TRANSPARENT);

        frame.setBorderRect(buttonBorder);
    }

    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        label.setPosition(x, y);
        updateFrame();
    }

    /**
     *
     * @return
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the Label of this button, also changing the Frame of this button.
     *
     * @param label the Label to set.
     */
    public void setLabel(Label label) {
        this.label = label;
        updateFrame();
    }

    /**
     *
     * @return
     */
    public Frame getFrame() {
        return frame;
    }
}
