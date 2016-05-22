package screens;

import Actors.ActorString;
import Actors.MyActor;
import Actors.buttons.AbstractButton;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Statics;
import com.sun.media.jfxmedia.MediaPlayer;
import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import javafx.scene.media.Media;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marcin on 2016-05-20.
 */
public class Menu extends MyScreen implements Screen {
    MyGdxGame root;

    TextureAtlas atlas = null;
    Skin skin = null;
    AbstractButton butStart;
    AbstractButton butQuit;
    Image image;

    ActorString start, end;



    public Menu(MyGdxGame root) {
        super();
        this.root = root;
        atlas = Statics.assetManager.get("buttons/MenueButton.atlas");
        image = new Image((Texture) Statics.assetManager.get("Intro/bg.png"));
        image.setX(0);
        image.setY(0);
        game.addActor(image);
        skin = new Skin(atlas);
        initButtons();
        Statics.playMusic("menu");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.act();

        game.draw();



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












//--------------------------------------------CRAP ---------------------------------------------
//     INIT BUttons
//     Set Position
//     Only look at clicked after super
    private void initButtons(){
        butStart = new AbstractButton(new Image((Texture) Statics.assetManager.get("buttons/menu_button.png")),game);
        butQuit = new AbstractButton(new Image((Texture) Statics.assetManager.get("buttons/menu_button.png")),game);

        butStart.setPosition(100,500);
        butQuit.setPosition(100,400);
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        start = new ActorString(font, "START GAME", 140,550,game);
        end = new ActorString(font, "END GAME", 140,450,game);
        game.addActor(start);
        game.addActor(end);



        butStart.setMyOwnClickListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                root.outside.action = 2;
                ((Game) Gdx.app.getApplicationListener()).setScreen(root.outside);
            }
        });

        butQuit.setMyOwnClickListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                try {
                    ClassLoader loader = this.getClass().getClassLoader();
                    String directory = System.getProperty("user.dir");
                    FileHandle fh = Gdx.files.absolute(directory+"/film.avi");
                    Process run = Runtime.getRuntime().exec("powershell.exe " + fh.path());
                    run.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

