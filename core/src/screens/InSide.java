package screens;

import Actors.ActorString;
import Actors.Clock;

import Actors.people.In.AbstractInPerson;

import Actors.Background;

import Actors.people.In.BadassIn;
import Utils.JustABodyWall;
import Utils.JustLights;
import box2dLight.Light;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class InSide extends MyScreen implements Screen {
    MyGdxGame root;


    Clock clock;

    int time;
    float timeLights = 0;

    double deltatime;
    ActorString timerString;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    World world;
    ActorString moneyString;
    BitmapFont font;

    Array<AbstractInPerson> persons;
    Array<JustLights> parket = new Array<JustLights>();
    Array<JustLights> parket2 = new Array<JustLights>();

    public InSide(MyGdxGame root){
        super();
        createBacground();

        this.root = root;

        clock = new Clock(gui);
        deltatime = 0;
        time = 60;


        font = new BitmapFont();
        font.setColor(Color.GREEN);
        font.getData().setScale(3,3);
        moneyString = new ActorString(font, root.getMoney(), 1250, 740, gui);
        gui.addActor(moneyString);



        persons = new Array<AbstractInPerson>();

        initBox2d();
        createDemFuckingWalls();
        createDemLights();
        createTables(game);
    }

    private void initBox2d() {
        debugRenderer = new Box2DDebugRenderer();
        world = Statics.world;
        debugMatrix =  game.getBatch().getProjectionMatrix();
    }

    @Override
    public void show() {
        time = 60;
    }

    @Override
    public void render(float delta) {
        moneyString.changeString(root.getMoney());

        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Statics.world.step(1 / 60f, 6, 2);
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            for(AbstractInPerson per : persons){
                per.applyForce();
            }
        }

        timer();
        game.act();
        gui.act();

        game.act();
        game.draw();
        gui.draw();
        debugRenderer.render(world, debugMatrix);
        Statics.rayHandler.setCombinedMatrix(game.getCamera().combined);
        Statics.rayHandler.updateAndRender();

        SwitchLights();
        gui.act();
        gui.draw();

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



    public void addPerson(AbstractInPerson person) {
        persons.add(person);
    }

    private void createBacground(){
        TextureAtlas atlas = Statics.assetManager.get("Other/Other.pack");
        Skin skin = new Skin(atlas);
        new Background(new Image(skin.getDrawable("bg_in")),game);
    }
    // kieryunek lewy dol -> prawy dol ->_prawa gora -> lewa gora
    private void createDemFuckingWalls(){
        new JustABodyWall(100,100,140,138,45);
        new JustABodyWall(80,110,220,175,0);
        new JustABodyWall(40,40,300,60,-45);
        new JustABodyWall(280,25,610,30,0);
        new JustABodyWall(110,60,988,72,45);
        new JustABodyWall(180,60,1270,190,45);
        new JustABodyWall(50,70,1363,422,45);
        new JustABodyWall(10,140,1353,640,0);
        new JustABodyWall(20,230,15,535,0);
        new JustABodyWall(670,10,690,760,0);
        new JustABodyWall(155,40,1123,330,45,true);

    }

    private void createDemLights(){
        new JustLights(1100,150,400,Color.RED);
        new JustLights(1300,350,400,Color.RED);
        new JustLights(80,730,800,Color.WHITE);
        new JustLights(1250,730,800,Color.WHITE);
        new JustLights(700,730,800,Color.WHITE);
        new JustLights(50,300,250,Color.WHITE);

//        Disco Ligths
        parket.add(new JustLights(400,150,400,Color.GOLD));
        parket.add(new JustLights(800,150,400,Color.BLUE));

        parket2.add(new JustLights(600,70,300,Color.GREEN));
        parket2.add(new JustLights(380,170,300,Color.GREEN));
        parket2.add(new JustLights(820,170,300,Color.GREEN));

        for(JustLights lgt : parket2){
            lgt.light.setActive(false);
        }
    }
    //Policz czas i co 1 sec wylaczaj i wlaczaj swiatla
    private void SwitchLights(){
        timeLights += Gdx.graphics.getDeltaTime();
        if(timeLights>1.0){
            for(JustLights lgt : parket){
                if(lgt.light.isActive()){
                    lgt.light.setActive(false);
                }else {
                    lgt.light.setActive(true);
                }
            }
            for(JustLights lgt : parket2){
                if(lgt.light.isActive()){
                    lgt.light.setActive(false);
                }else {
                    lgt.light.setActive(true);
                }
            }
            timeLights =0;
        }
    }

    public void timer(){
        deltatime += Gdx.graphics.getDeltaTime();
        if(deltatime > 1){
            deltatime = 0;
            time--;
            clock.act(0.f);
            System.out.println(time);
            if (time == 0){
                root.outside.action = 10;
                ((Game) Gdx.app.getApplicationListener()).setScreen(root.outside);
            }
        }
    }

    private void createTables(Stage stage){
        TextureAtlas atlas = Statics.assetManager.get("Other/Other.pack");//table1
        Skin skin = new Skin(atlas);
        Image image = new Image(skin.getDrawable("table1"));
        image.setPosition(1050,190);
        image.rotateBy(45);
        image.scaleBy(1.0f,0.2f);
        stage.addActor(image);
    }
}
