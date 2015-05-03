package ending.widget;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import ending.vector.Vector2i;

/**
 * A Widget that has a border and a fill color.
 *
 * @author Nick
 */
public class Frame extends Widget {

    private RectangleShape borderRect;

    private FloatRect boundingRect;

    /**
     *
     * @param borderRect
     * @param fillColor
     */
    public Frame(RectangleShape borderRect, Color fillColor) {
        this.borderRect = borderRect;
        this.borderRect.setFillColor(fillColor);

        boundingRect = borderRect.getGlobalBounds();
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        if (view != null) {
            rt.setView(view);
            rt.draw(borderRect);
            rt.setView(rt.getDefaultView());
        } else {
            rt.draw(borderRect);
        }
    }

    /**
     *
     * @param borderRect
     */
    public void setBorderRect(RectangleShape borderRect) {
        this.borderRect = borderRect;
        boundingRect = borderRect.getGlobalBounds();
    }

    /**
     *
     * @param color
     */
    public void setBorderColor(Color color) {
        borderRect.setOutlineColor(color);
    }

    /**
     *
     * @return
     */
    public Color getBorderColor() {
        return borderRect.getOutlineColor();
    }

    /**
     *
     * @param color
     */
    public void setFillColor(Color color) {
        borderRect.setFillColor(color);
    }

    /**
     *
     * @return
     */
    public Color getFillColor() {
        return borderRect.getFillColor();
    }

    /**
     *
     * @param v
     * @return
     */
    public boolean contains(Vector2i v) {
        return boundingRect.contains(v.x, v.y);
    }
}
