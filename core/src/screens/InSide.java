package screens;

import Actors.ActorString;
import Actors.Clock;
import Actors.people.In.BadassIn;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Marcin on 2016-05-21.
 */
public class InSide extends MyScreen implements Screen {
    MyGdxGame root;
    BadassIn badass;

    Clock clock;
    int time;
    double deltatime;
    ActorString timerString;
    private BitmapFont font;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    World world;

    public InSide(MyGdxGame root){
        super();
        this.root = root;
        badass = new BadassIn(game);
        clock = new Clock(game);
        deltatime = 0;
        time = 60;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        timerString = new ActorString(font, "1:00", 1200, 600, game);
        game.addActor(timerString);

        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    } 
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        badass.move();
        timer();
        game.act();
        badass.act(delta);

        debugMatrix =  game.getBatch().getProjectionMatrix().cpy();

        game.draw();


        debugRenderer.render(world, debugMatrix);

        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            badass.randomize_direct();
        }

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


    public void timer(){
        deltatime += Gdx.graphics.getDeltaTime();
        if(deltatime > 1){
            deltatime = 0;
            time--;
            timerString.changeString(time/60 + ":" + time%60);
            clock.act(0.f);
            if (time == 0){
                root.outside.action = 2;
                ((Game) Gdx.app.getApplicationListener()).setScreen(root.outside);
            }

        }
    }
}
