package ending.gamestate.screen;

import ending.gamestate.GameState;
import ending.util.Fonts;
import ending.util.TextUtils;
import ending.util.TextureUtils;
import ending.widget.Button;
import ending.widget.Widget;
import ending.widget.widgetlistener.ButtonAdapter;
import ending.window.WindowConfig;
import java.util.ArrayList;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;

/**
 *
 * @author Nick
 */
public class MainMenuScreen extends Screen {

    private final Sprite backgroundSprite;

    public MainMenuScreen() {
        super();

        backgroundSprite = new Sprite(TextureUtils.loadTexture("sprites/mainmenu.png"));

        Text enterGameText = new Text();
        enterGameText.setFont(Fonts.quicksandBold);
        enterGameText.setString("Enter Game");
        enterGameText.setColor(new Color(73, 73, 73));
        TextUtils.setOriginAtCenter(enterGameText);
        enterGameText.setPosition(120, WindowConfig.WINDOW_HEIGHT - 200);

        Button enterGameButton = new Button(enterGameText);
        enterGameButton.addMouseListener(new ButtonAdapter(() -> GameState.setCurrentScreen(ScreenType.GAME),
                "sounds/buttonhover.wav",
                new Color(242, 242, 242),
                new Color(73, 73, 73)));

        widgets.add(enterGameButton);

        Text optionsText = new Text();
        optionsText.setFont(Fonts.quicksandBold);
        optionsText.setString("Options");
        optionsText.setColor(new Color(73, 73, 73));
        TextUtils.setOriginAtCenter(optionsText);
        optionsText.setPosition(140, WindowConfig.WINDOW_HEIGHT - 140);

        Button optionsButton = new Button(optionsText);
        optionsButton.addMouseListener(new ButtonAdapter(() -> GameState.setCurrentScreen(ScreenType.GAME),
                "sounds/buttonhover.wav",
                new Color(242, 242, 242),
                new Color(73, 73, 73)));

        widgets.add(optionsButton);
        
        Text exitGameText = new Text();
        exitGameText.setFont(Fonts.quicksandBold);
        exitGameText.setString("Exit Game");
        exitGameText.setColor(new Color(73, 73, 73));
        TextUtils.setOriginAtCenter(exitGameText);
        exitGameText.setPosition(210, WindowConfig.WINDOW_HEIGHT - 80);

        Button exitGameButton = new Button(exitGameText);
        exitGameButton.addMouseListener(new ButtonAdapter(() -> GameState.setCurrentScreen(ScreenType.GAME),
                "sounds/buttonhover.wav",
                new Color(242, 242, 242),
                new Color(73, 73, 73)));

        widgets.add(exitGameButton);
    }

    @Override
    public void draw(RenderTarget rt, RenderStates states) {
        rt.clear(Color.BLUE);
        rt.draw(backgroundSprite);
        for (Widget w : widgets) {
            rt.draw(w);
        }
    }

}
