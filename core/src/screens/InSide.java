package screens;

import Actors.ActorString;
import Actors.people.In.BadassIn;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Marcin on 2016-05-21.
 */
public class InSide extends MyScreen implements Screen {
    MyGdxGame root;
    BadassIn badass;
    int time;
    double deltatime;
    ActorString timerString;
    private BitmapFont font;

    public InSide(MyGdxGame root){
        super();
        this.root = root;
        badass = new BadassIn(game);
        deltatime = 0;
        time = 180;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        timerString = new ActorString(font, "3:00", 1200, 600, game);
        game.addActor(timerString);
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


    public void timer(){
        deltatime += Gdx.graphics.getDeltaTime();
        if(deltatime > 1){
            deltatime = 0;
            time--;
            timerString.changeString(time/60 + ":" + time%60);
            if (time == 0){
                root.outside.action = 2;
                ((Game) Gdx.app.getApplicationListener()).setScreen(root.outside);
            }

        }
    }
}
