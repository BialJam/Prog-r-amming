package Actors.people.In;

import Actors.MyActor;
import Utils.PersonBody;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.btree.decorator.Random;
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

    protected int health;
    protected int happines;
    protected int drunk;
    protected int angry;
    protected int want = 0;
    protected boolean finishedWant = true;

    // 0 - losowo łazi po planszy
    // 1 - chce mu się chlać
    // 2 - chce mu się tańczyć
    // 3 - chce mu się napierdalać
    // 4 - chce sie rzygac
    // 5 - wychodzi z baru.
    Array<Integer> chances;
    public PersonBody body;

    private MathUtils math = new MathUtils();
    private int animate = 0;
    private float deltatime;
    private boolean tick = false;
    private int rotate = 1;

    protected boolean moveRotate = true;
    protected float speedX = 0.5f, speedY = 0.5f, angle = 0.0f, prev_angle, maxSpeed;

    public AbstractInPerson(Stage stage) {
        super(stage);
        x = 100;
        y = 100;
        health = 1000;
        happines = 500;
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
    }

    public void randomize_direct() {
        System.out.println(Math.random() % 20);
        speedX = (float) ((Math.random() * (Math.sqrt(maxSpeed)) * 2) - (Math.sqrt(maxSpeed)));
        speedY = (float) Math.sqrt((maxSpeed - speedX * speedX));
        System.out.println(maxSpeed + "+" + speedX + " " + speedY);
    }

    protected Array<Integer> newChances(Integer... coll){
        Array<Integer> array = new Array<Integer>();
        for(Integer in: coll){
            array.add(in);
        }
        return array;
    }

    /**
     * Inicjalizacja szans.
     * Tworzy pierwszą podstawową tablicę szans.
     */
    public void initChances(){
        // Suma szans musi być równa 100.
         chances = newChances(33,33,34,0,0,0);
    }

    /**
     * Update szans.
     * W zależności od stanu postaci zmieniają się szansa na zachowanie.
     * Jeżeli stan postaci jest normalny (inny niż wymienione) to resetujemy (od nowa initChances)
     */
    public void refreshChances(){
        if(happines < 10){
            chances = newChances(0,0,0,0,0,100);
        }else if(drunk > 75){
            chances = newChances(0,20,20,10,30,20);
        }else if(angry > 80){
            chances = newChances(0,20,20,10,30,20);
        }else{
            initChances();
        }
    }

    public void randomizeWant() {
        refreshChances();
        if (finishedWant) { // jeżeli poprzednia potrzeba nie jest spełniona to nie losujemy następnej.
            int rand = MathUtils.random(0,100);
            int sumChance = 0;

            for(int i = 0;i< chances.size; i++){
                if(rand>=sumChance && rand < sumChance + chances.get(i)){
                    want = i;
                    finishedWant = false;
                    break;
                }else{
                    sumChance += chances.get(i);
                }
            }
            System.out.println(want);
        }
    }

    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

    private void animate() {
        animate++;
        if (animate == 13)
            animate = -12;
        if (animate % 3 == 0)
            image.rotateBy(animate);
    }

    public void move() {

//        float dt = Gdx.graphics.getDeltaTime();
//
//        x += speedX * dt* 15;
//        y += speedY * dt* 15;
//
//        if(x >= Statics.WIDTH){speedX *= -1;}
//        if(y >= Statics.HEIGHT){speedY *= -1;}
//        if(x <= 0.0f){speedX *= -1;}
//        if(y <= 0.0f){speedY *= -1;}
//

        animate();
        randomizeWant();
//
//        setPosition(x, y);
    }

    public void setAngle(Vector2 way) {
        double theta = Math.atan2(-way.y, way.x);
        theta += Math.PI / 2.0;
        prev_angle = angle;
        angle = (float) Math.toDegrees(theta);

        if (angle < 0) {
            angle += 360;
        }

        rotate = (int) prev_angle - (int) angle;
        if (rotate < 0) {
            rotate += 360;
        }
        image.rotateBy(rotate);
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
        image.setPosition(position.x-image.getWidth()/2,position.y-image.getHeight()/3);
        setAngle(body.body.getLinearVelocity());
        move();


    }

    public void applyForce(){
        math.random(0,8);
        body.body.applyForceToCenter(1000000f,3f,true);

    }
}
