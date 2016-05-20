package screens;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-20.
 */
public  interface AbstractScreen extends Screen{

    Viewport viewport = new ExtendViewport(Statics.WIDTH,Statics.HEIGHT);;
    Stage game = new Stage(viewport);
    Stage gui = new Stage(viewport);

    InputMultiplexer inputMultiplexer = new InputMultiplexer(game,gui);


}
