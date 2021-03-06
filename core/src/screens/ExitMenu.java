package screens;

import Actors.buttons.AbstractButton;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Statics;

/**
 * Created by Michalina on 2016-05-22.
 */
public class ExitMenu extends MyScreen implements Screen {

    MyGdxGame root;

    TextureAtlas atlas = null;
    Skin skin = null;

    AbstractButton butBack;
    AbstractButton butQuit;

    public ExitMenu(MyGdxGame root) {
        super();
        this.root = root;
        atlas = Statics.assetManager.get("buttons/MenueButton.atlas");
        skin = new Skin(atlas);
        initButtons();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        Statics.playMusic("end");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.act();

        game.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


    //--------------------------------------------CRAP ---------------------------------------------
//     INIT BUttons
//     Set Position
//     Only look at clicked after super
    private void initButtons() {
        butBack = new AbstractButton(new Image(skin.getDrawable("ButtonUp9")), game);
        butQuit = new AbstractButton(new Image(skin.getDrawable("ButtonUp9")), game);

        butBack.setPosition(500, 500);
        butQuit.setPosition(500, 400);

        butBack.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                root.outside.action = 2;
                ((Game) Gdx.app.getApplicationListener()).setScreen(root.menu);
            }
        });

        butQuit.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
    }
}

