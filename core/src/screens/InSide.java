package screens;

import Actors.people.In.BadassIn;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Marcin on 2016-05-21.
 */
public class InSide extends MyScreen implements Screen {
    MyGdxGame root;
    BadassIn badass;

    public InSide(MyGdxGame root){
        super();
        this.root = root;
        badass = new BadassIn(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        badass.move();
        game.act();
        game.draw();

    }

    @Override
    public void resize(int width, int height) {

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
