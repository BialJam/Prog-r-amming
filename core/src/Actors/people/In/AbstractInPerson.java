package Actors.people.In;

import Actors.ActorString;
import Actors.MyActor;
import Actors.people.In.needs.*;
import Utils.PersonBody;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.btree.decorator.Random;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Statics;

import java.util.Collection;

/**
 * Created by Marcin on 2016-05-21.
 */
public class AbstractInPerson extends MyActor {

    public int health;
    public int happines;
    public int drunk;
    public int angry;
    public int want = 0;
    public boolean finishedWant = true;
    public Need need;
    public Array<Need> allNeeds;

    // 0 - losowo łazi po planszy
    // 1 - chce mu się chlać
    // 2 - chce mu się tańczyć
    // 3 - chce mu się napierdalać
    // 4 - chce sie rzygac
    // 5 - wychodzi z baru.
    // 6 - nieprzytomny
    Array<Integer> chances;
    public PersonBody body;

    private MathUtils math = new MathUtils();

    private float deltatime;
    private boolean tick = false;


    protected boolean moveRotate = true;


    // DEBUG
    BitmapFont font;
    private ActorString debugNeedString;

    private String makeDebugString() {
        return "Type:" + ((need == null) ? "null" : need.toString()) + "\n" +
                "Happ: " + happines + "\n" +
                "Najeb:" + drunk + "\n" +
                "angry:" + angry + "\n" +
                "health" + health + "\n";
    }

    // END DEBUG

    public AbstractInPerson(Stage stage) {
        super(stage);
        x = 100;
        y = 100;
        health = 100;
        happines = 50;
        angry = 0;
        drunk = 0;
        angle = 0;
        prev_angle = 0;
        maxSpeed = 400;
//        randomize_direct();
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        initChances();
        stage.addActor(this);
        animate = 0;
        body = new PersonBody(image);

        // inicjalizacja tablicy potrzeb
        allNeeds = new Array<>();
        allNeeds.add(new MoveRandomly(this));
        allNeeds.add(new Drink(this));
        allNeeds.add(new Dance(this));
        allNeeds.add(new Fight(this));
        allNeeds.add(new Puke(this));
        allNeeds.add(new Escape(this));
        allNeeds.add(new Injured(this));

        if (Statics.debug) {
            font = new BitmapFont();
            font.setColor(Color.BLACK);
            debugNeedString = new ActorString(font, makeDebugString(), (int) (image.getX() + 10), (int) (image.getY()), stageIBelongTo);
            stageIBelongTo.addActor(debugNeedString);
        }
    }

    public void randomize_direct() {
        speedX = (float) ((Math.random() * (Math.sqrt(maxSpeed)) * 2) - (Math.sqrt(maxSpeed)));
        speedY = (float) Math.sqrt((maxSpeed - speedX * speedX));
    }

    protected Array<Integer> newChances(Integer... coll) {
        Array<Integer> array = new Array<Integer>();
        for (Integer in : coll) {
            array.add(in);
        }
        return array;
    }

    /**
     * Inicjalizacja szans.
     * Tworzy pierwszą podstawową tablicę szans.
     */
    public void initChances() {
        // Suma szans musi być równa 100.
        chances = newChances(33, 33, 34, 0, 0, 0);
    }

    /**
     * Update szans.
     * W zależności od stanu postaci zmieniają się szansa na zachowanie.
     * Jeżeli stan postaci jest normalny (inny niż wymienione) to resetujemy (od nowa initChances)
     */
    public void refreshChances() {
        if (happines < 10) {
            chances = newChances(0, 0, 0, 0, 0, 100);
        } else if (drunk > 75) {
            chances = newChances(0, 20, 20, 10, 30, 20);
        } else if (angry > 80) {
            chances = newChances(0, 20, 20, 10, 30, 20);
        } else {
            initChances();
        }
    }

    public void randomizeWant() {
        refreshChances();
        if (finishedWant || need == null) { // jeżeli poprzednia potrzeba nie jest spełniona to nie losujemy następnej.
            int rand = MathUtils.random(0, 100);
            int sumChance = 0;

            for (int i = 0; i < chances.size; i++) {
                if (rand >= sumChance && rand <= sumChance + chances.get(i)) {
                    want = i;
                    finishedWant = false;
                    need = allNeeds.get(i);
                    break;
                } else {
                    sumChance += chances.get(i);
                }
            }
        }
        if (health < 0) {
            finishedWant = false;
            need = allNeeds.get(6);
        }
        if (drunk > 100) {
            finishedWant = false;
            need = allNeeds.get(4);
        }
    }

    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }



    public void move() {
        animate();
        randomizeWant();
        need.doIt();
        if (Statics.debug) {
            if (Gdx.app.getInput().isKeyJustPressed(Input.Keys.ENTER)){
                health = 100;
                need = allNeeds.get(3);
                want = 3;
            }

            debugNeedString.changeString(makeDebugString());
            debugNeedString.x = (int) image.getX() + 30;
            debugNeedString.y = (int) image.getY();
        }
    }



    public void setPosition(int x, int y) {
        image.setPosition(x, y);
    }

    public void setImages(String name) {
        setImages("Characters_in/Characters_in.pack", name);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Vector2 position = body.body.getPosition();
        image.setPosition(position.x - image.getWidth() / 2, position.y - image.getHeight() / 3);
        setAngle(body.body.getLinearVelocity());
        move();
        drawIcons();


    }

    public void applyForce() {
        math.random(0, 8);
        body.body.applyForceToCenter(1000000f, 3f, true);

    }

    public void moveTotarget(Vector2 target) {
        Vector2 start = body.body.getPosition();
        Vector2 vector = new Vector2(target.x - start.x, target.y - start.y);
        vector.nor();
        vector.x = vector.x * 10000000;
        vector.y = vector.y * 10000000;
        body.body.setLinearVelocity(vector);
    }

    public Vector2 getPersonVector() {
        return new Vector2(this.image.getX(), this.image.getY());
    }

    public void clearNeeds(){
        need.clearNeed();
    }
    public void clearAllNeeds(){
        for (Need ne : allNeeds){
            ne.clearNeed();
        }
    }

    public void drawIcons(){
        ((Drink) allNeeds.get(1)).ico.setVisible(false);
        ((Dance) allNeeds.get(2)).ico.setVisible(false);
        ((Fight) allNeeds.get(3)).ico.setVisible(false);
        ((Fight) allNeeds.get(3)).ico.image.setVisible(false);
        ((Fight) allNeeds.get(3)).ico.setVisible(false);

        if (want == 1){
            ((Drink) allNeeds.get(1)).ico.setVisible(true);
        }
        if (want == 2){
            ((Dance) allNeeds.get(2)).ico.setVisible(true);
        }
        if (want == 3) {
            ((Fight) allNeeds.get(3)).ico.image.setVisible(true);
            ((Fight) allNeeds.get(3)).ico.setVisible(true);
        }
        if (want != 3 ) {
            ((Fight) allNeeds.get(3)).ico.image.setVisible(false);
            ((Fight) allNeeds.get(3)).ico.setVisible(false);
        }
    }
}
