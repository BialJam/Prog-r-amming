package screens;

import Actors.MyActor;
import Actors.buttons.AbstractButton;
import Actors.people.Out.Badass;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-20.
 */
public class Menu  implements  AbstractScreen {

    TextureAtlas atlas = null;
    Skin skin = null;
    AbstractButton butStart;
    Badass badass;

    public Menu() {

        atlas = Statics.assetManager.get("buttons/MenueButton.atlas");
        skin = new Skin(atlas);
        butStart = new AbstractButton("ButtonUp9",game);
        badass = new Badass(game);
        butStart.setPosition(300,300);
        butStart.setMyOwnClickListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("CLicked");
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.act();

        game.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new OutSide());
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
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
}
