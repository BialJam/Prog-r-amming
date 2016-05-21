package screens;

import Actors.people.AbstractPerson;
import Actors.people.Out.AbstractOutPerson;
import Utils.QueueCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Michalina on 2016-05-20.
 */
public class OutSide extends MyScreen implements Screen{


    Array<AbstractOutPerson> persons = new Array<AbstractOutPerson>();


    @Override
    public void show() {

    }

    public OutSide() {
        super();
        persons = QueueCreator.CreateQueue(persons,game);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
