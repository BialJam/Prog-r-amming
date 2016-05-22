package screens;

import Actors.ActorString;
import Actors.Clock;

import Actors.buttons.AbstractButton;
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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    AbstractButton fadeIn, fadeOut, bodyBtn, alcoBtn, cleanerBtn, foodBtn, securityBtn;

    Clock clock;

    static int time;
    float timeLights = 0;

    double deltatime;
    ActorString timerString;
    ActorString cleanerString, alcoString, securityString, foodString;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    World world;
    ActorString moneyString;
    BitmapFont font, font_bttn;

    static public Array<AbstractInPerson> persons;
    Array<JustLights> parket = new Array<JustLights>();
    Array<JustLights> parket2 = new Array<JustLights>();

    // Debug
    ActorString mousePosition;

    public InSide(MyGdxGame root) {
        super();
        createBacground();
        createButtons();
        this.root = root;

        font_bttn = new BitmapFont();
        font_bttn.setColor(Color.BLACK);

        alcoString = new ActorString(font_bttn, "alco", 1100, 598, gui);
        foodString = new ActorString(font_bttn, "food", 1100, 497, gui);
        securityString = new ActorString(font_bttn, "security", 1100, 396, gui);
        cleanerString = new ActorString(font_bttn, "cleaner", 1100, 295, gui);

        alcoString.setVisible(false);
        foodString.setVisible(false);
        securityString.setVisible(false);
        cleanerString.setVisible(false);

        gui.addActor(alcoString);
        gui.addActor(foodString);
        gui.addActor(cleanerString);
        gui.addActor(securityString);

        clock = new Clock(gui);
        deltatime = 0;
        time = 60;

        font = new BitmapFont();
        font.setColor(Color.GREEN);
        font.getData().setScale(3, 3);
        moneyString = new ActorString(font, root.getMoney(), 1250, 740, gui);
        gui.addActor(moneyString);

        persons = new Array<AbstractInPerson>();

        if (Statics.debug) {
            Vector3 newPoints = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            newPoints = game.getViewport().unproject(newPoints);
            String toWrite = "X:" + newPoints.x + " Y: " + newPoints.y;
            mousePosition = new ActorString(font, toWrite, (int)newPoints.x, (int)newPoints.y, game);
            game.addActor(mousePosition);
        }

        initBox2d();
        createDemFuckingWalls();
        createDemLights();
        createTables(game);
    }

    private void initBox2d() {
        debugRenderer = new Box2DDebugRenderer();
        world = Statics.world;
        debugMatrix = game.getBatch().getProjectionMatrix();
    }

    @Override
    public void show() {
        time = 60;
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        moneyString.changeString(root.getMoney());

        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Statics.world.step(1 / 60f, 6, 2);
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            for (AbstractInPerson per : persons) {
                per.applyForce();
            }
        }
        timer();
        game.act();
        gui.act();

        alcoString.changeString(root.getAlco());
        foodString.changeString(root.getFood());
        cleanerString.changeString(root.getCleaner());
        securityString.changeString(root.getSecurity());

        game.act();
        game.draw();
        gui.draw();
        debugRenderer.render(world, debugMatrix);
        Statics.rayHandler.setCombinedMatrix(game.getCamera().combined);
        Statics.rayHandler.updateAndRender();

        if(Statics.debug){
            Vector3 newPoints = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            newPoints = game.getViewport().unproject(newPoints);
            String toWrite = "X:" + newPoints.x + " Y: " + newPoints.y;
            mousePosition.changeString(toWrite);
            mousePosition.x = (int)newPoints.x;
            mousePosition.y = (int)newPoints.y;
        }

        SwitchLights();
        gui.act();
        gui.draw();

        if(persons.size == 0){
            ((Game) Gdx.app.getApplicationListener()).setScreen(root.exit);
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

    public void addPerson(AbstractInPerson person) {
        persons.add(person);
    }

    private void createButtons() {
        TextureAtlas atlas = Statics.assetManager.get("Other/Gui.pack");
        Skin skin = new Skin(atlas);

        bodyBtn = new AbstractButton(new Image(skin.getDrawable("guiBody")), gui);
        bodyBtn.setPosition(1366, 250); //491

        alcoBtn = new AbstractButton(new Image(skin.getDrawable("guiAlcoBtn")), gui);
        alcoBtn.setPosition(946 + 491, 578); //491
        alcoBtn.image.setScale(.3f);

        foodBtn = new AbstractButton(new Image(skin.getDrawable("guiFoodBtn")), gui);
        foodBtn.setPosition(946 + 491, 477); //491
        foodBtn.image.setScale(.3f);

        cleanerBtn = new AbstractButton(new Image(skin.getDrawable("guiCleanerBtn")), gui);
        cleanerBtn.setPosition(946 + 491, 275); //491
        cleanerBtn.image.setScale(.3f);

        securityBtn = new AbstractButton(new Image(skin.getDrawable("guiSecurityBtn")), gui);
        securityBtn.setPosition(946 + 491, 376); //491
        securityBtn.image.setScale(.3f);

        fadeIn = new AbstractButton(new Image(skin.getDrawable("guiOpenBtn")), gui);
        fadeIn.setPosition(1325, 600);
        fadeIn.image.setScale(.3f);

        fadeOut = new AbstractButton(new Image(skin.getDrawable("guiCloseBtn")), gui);
        fadeOut.setPosition(880, 600);
        fadeOut.image.setScale(.3f);
        fadeOut.image.setVisible(false);

        fadeIn.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                fadeOut.image.setVisible(true);
                fadeIn.image.setVisible(false);
                bodyBtn.image.addAction(Actions.moveBy(-491, 0, 1));
                alcoBtn.image.addAction(Actions.moveBy(-491, 0, 1));
                cleanerBtn.image.addAction(Actions.moveBy(-491, 0, 1));
                foodBtn.image.addAction(Actions.moveBy(-491, 0, 1));
                securityBtn.image.addAction(Actions.moveBy(-491, 0, 1));

                alcoString.setVisible(true);
                foodString.setVisible(true);
                securityString.setVisible(true);
                cleanerString.setVisible(true);
            }
        });

        fadeOut.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                fadeIn.image.setVisible(true);
                fadeOut.image.setVisible(false);
                bodyBtn.image.addAction(Actions.moveBy(491, 0, 1));
                alcoBtn.image.addAction(Actions.moveBy(491, 0, 1));
                cleanerBtn.image.addAction(Actions.moveBy(491, 0, 1));
                foodBtn.image.addAction(Actions.moveBy(491, 0, 1));
                securityBtn.image.addAction(Actions.moveBy(491, 0, 1));

                alcoString.setVisible(false);
                foodString.setVisible(false);
                securityString.setVisible(false);
                cleanerString.setVisible(false);

            }
        });

        alcoBtn.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Kupiłeś alco");
                root.setMoney(-50);
                if (root.getMoneyInt()>=0)  root.setAlco(10);
                else root.setMoney(50);
            }
        });

        foodBtn.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Kupiłeś żarcie");
                root.setMoney(-50);
                if (root.getMoneyInt()>=0)  root.setFood(10);
                else root.setMoney(50);
            }
        });

        securityBtn.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Kupiłeś guarda");
                root.setMoney(-(root.getSecurityInt()+1)*20);
                if (root.getMoneyInt()>=0)  root.setSecurity(1);
                else root.setMoney((root.getSecurityInt()+1)*20);
            }
        });

        cleanerBtn.setMyOwnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Kupiłeś sprzatanie");
                root.setMoney(-(root.getCleanerInt()+1)*20);
                if (root.getMoneyInt()>=0)  root.setCleaner(1);
                else root.setMoney((root.getSecurityInt()+1)*20);
            }
        });
    }

    private void createBacground() {
        TextureAtlas atlas = Statics.assetManager.get("Other/Other.pack");
        Skin skin = new Skin(atlas);
        new Background(new Image(skin.getDrawable("bg_in")), game);
    }

    // kieryunek lewy dol -> prawy dol ->_prawa gora -> lewa gora
    private void createDemFuckingWalls() {
        new JustABodyWall(100, 100, 140, 138, 45);
        new JustABodyWall(80, 110, 220, 175, 0);
        new JustABodyWall(40, 40, 300, 60, -45);
        new JustABodyWall(280, 25, 610, 30, 0);
        new JustABodyWall(110, 60, 988, 72, 45);
        new JustABodyWall(180, 60, 1270, 190, 45);
        new JustABodyWall(50, 70, 1363, 422, 45);
        new JustABodyWall(10, 140, 1353, 640, 0);
        new JustABodyWall(20, 230, 15, 535, 0);
        new JustABodyWall(670, 10, 690, 760, 0);
        new JustABodyWall(155, 40, 1123, 330, 45, true);

    }

    private void createDemLights() {
        new JustLights(1100, 150, 400, Color.RED);
        new JustLights(1300, 350, 400, Color.RED);
        new JustLights(80, 730, 800, Color.WHITE);
        new JustLights(1250, 730, 800, Color.WHITE);
        new JustLights(700, 730, 800, Color.WHITE);
        new JustLights(50, 300, 250, Color.WHITE);


//        Disco Ligths
        parket.add(new JustLights(400, 150, 400, Color.GOLD));
        parket.add(new JustLights(800, 150, 400, Color.BLUE));

        parket2.add(new JustLights(600, 70, 300, Color.GREEN));
        parket2.add(new JustLights(380, 170, 300, Color.GREEN));
        parket2.add(new JustLights(820, 170, 300, Color.GREEN));

        for (JustLights lgt : parket2) {
            lgt.light.setActive(false);
        }
    }

    //Policz czas i co 1 sec wylaczaj i wlaczaj swiatla
    private void SwitchLights() {
        timeLights += Gdx.graphics.getDeltaTime();
        if (timeLights > 1.0) {
            for (JustLights lgt : parket) {
                if (lgt.light.isActive()) {
                    lgt.light.setActive(false);
                } else {
                    lgt.light.setActive(true);
                }
            }
            for (JustLights lgt : parket2) {
                if (lgt.light.isActive()) {
                    lgt.light.setActive(false);
                } else {
                    lgt.light.setActive(true);
                }
            }
            timeLights = 0;
        }
    }

    public void timer() {
        deltatime += Gdx.graphics.getDeltaTime();
        if (deltatime > 1) {
            deltatime = 0;
            time--;
            clock.act(0.f);
            if (time == 0) {
                root.outside.action = 10;
                ((Game) Gdx.app.getApplicationListener()).setScreen(root.outside);
            }
        }
    }

    private void createTables(Stage stage) {
        TextureAtlas atlas = Statics.assetManager.get("Other/Other.pack");//table1
        Skin skin = new Skin(atlas);
        Image image = new Image(skin.getDrawable("table1"));
        image.setPosition(1050, 190);
        image.rotateBy(45);
        image.scaleBy(1.0f, 0.2f);
        stage.addActor(image);
    }

    public static int getTime() {
        return time;
    }
}
