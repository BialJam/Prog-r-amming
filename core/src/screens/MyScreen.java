package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Statics;

/**
 * Created by sury on 20.05.16.
 */
public class MyScreen {
    protected Viewport viewport = new FillViewport(Statics.WIDTH,Statics.HEIGHT);
    protected Stage game = new Stage(viewport);
    protected Stage gui = new Stage(viewport);

    InputMultiplexer inputMultiplexer = new InputMultiplexer(game,gui);

    public MyScreen() {
    }

    public Stage getGameStage(){
        return game;
    }
}
