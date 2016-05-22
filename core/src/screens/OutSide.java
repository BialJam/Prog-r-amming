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
import java.util.*;
import java.util.List;

/**
 * Created by Michalina on 2016-05-20.
 */
public class OutSide extends MyScreen implements Screen{
    MyGdxGame root;
    private List<AbstractPerson> animate;
    public int action = 12;
    double deltatime=0;
    int frame=0;
    ActorString moneyString, pointString;
    BitmapFont font = new BitmapFont();

    Array<AbstractOutPerson> persons = new Array<AbstractOutPerson>();

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        action = 12 + (root.getSecurityInt()*2);
        persons = QueueCreator.CreateQueue(persons, game, root);
        Statics.playMusic("inside");
        Statics.now.setVolume(0.2f);

    }

    public OutSide(MyGdxGame root) {
        super();
        animate = new ArrayList<>();
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

    public void timer() {
        deltatime += Gdx.graphics.getDeltaTime();
        if (deltatime > 1) {
            deltatime = 0;
            Random rand = new Random();
            int  n = rand.nextInt(3);
            for(AbstractPerson ab: animate) {
                ab.image.setVisible(false);
            }

        }
    }

    @Override
    public void render(float delta) {
        moneyString.changeString(root.getMoney());
        pointString.changeString("Move: "+root.outside.action);
        timer();
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
