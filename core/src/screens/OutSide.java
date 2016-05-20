package screens;

import Actors.ActorString;
import Actors.buttons.AbstractButton;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Statics;

/**
 * Created by Michalina on 2016-05-20.
 */
public class OutSide implements AbstractScreen {

    TextureAtlas atlas = null;
    Skin skin = null;
    AbstractButton butStart;
    int action = 10;
    ActorString writing;
    private Stage stage;
    private BitmapFont fontBlue;

    @Override
    public void show() {

    }

    public OutSide() {

        //tworzenie czcionki
        fontBlue = new BitmapFont();
        fontBlue.setColor(Color.RED);
        writing = new ActorString(fontBlue, "action=10", 200, 200, game);
        game.addActor(writing);
    }

    private void queue() {
        action--;
        if (action == 0) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new Menu());
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.act();
        fontBlue.setColor(0.5f, 0.4f, 0, 1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            queue();
            writing.changeString(action);
        }
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
