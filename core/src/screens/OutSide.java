package screens;

import Actors.ActorString;
import Actors.Background;
import Actors.people.AbstractPerson;
import Actors.people.Out.AbstractOutPerson;
import Utils.QueueCreator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
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

import java.awt.*;

/**
 * Created by Michalina on 2016-05-20.
 */
public class OutSide extends MyScreen implements Screen{
    MyGdxGame root;
    public int action = 10;
    ActorString moneyString, pointString;
    BitmapFont font = new BitmapFont();

    Array<AbstractOutPerson> persons = new Array<AbstractOutPerson>();



    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        action = 10;
    }

    public OutSide(MyGdxGame root) {
        super();
        TextureAtlas atlas = Statics.assetManager.get("Other/Other.pack");
        Skin skin = new Skin(atlas);
        new Background(new Image(skin.getDrawable("bg_out")),game);
        this.root = root;
        persons = QueueCreator.CreateQueue(persons, game, root);
        QueueCreator.shade();

        font.setColor(Color.GREEN);
        font.getData().setScale(3,3);
        moneyString = new ActorString(font, root.getMoney(), 1250, 740, gui);
        pointString = new ActorString(font, "Move: " + 10, 1150, 640, gui);
        gui.addActor(moneyString);
        gui.addActor(pointString);
    }

    @Override
    public void render(float delta) {
        moneyString.changeString(root.getMoney());
        pointString.changeString("Move: "+root.outside.action);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.act();
        game.draw();
        gui.act();
        gui.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.C) || action==0) {
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
