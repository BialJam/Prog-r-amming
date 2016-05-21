package screens;

import Actors.Background;
import Actors.people.AbstractPerson;
import Actors.people.Out.AbstractOutPerson;
import Utils.QueueCreator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Statics;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Michalina on 2016-05-20.
 */
public class OutSide extends MyScreen implements Screen{
    MyGdxGame root;
    int action = 10;

    Array<AbstractOutPerson> persons = new Array<AbstractOutPerson>();



    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public OutSide(MyGdxGame root) {
        super();
        TextureAtlas atlas = Statics.assetManager.get("Other/Other.pack");
        Skin skin = new Skin(atlas);
        new Background(new Image(skin.getDrawable("bg_out")),game);
        this.root = root;
        persons = QueueCreator.CreateQueue(persons, game, root);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.act();
        game.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(root.inside);
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
