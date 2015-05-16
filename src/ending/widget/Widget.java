package ending.widget;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.View;

/**
 * A GUI Widget.
 *
 * @author Nick
 */
public abstract class Widget implements Drawable {

    /**
     * The View that this Widget is parented to.
     */
    protected View view;
    
    public void update() {
    }

    @Override
    public abstract void draw(RenderTarget rt, RenderStates states);

    /**
     * Sets the View for this Widget to be parented to.
     *
     * @param view the view that this Widget will be parented to.
     */
    public void setView(View view) {
        this.view = view;
    }

}
