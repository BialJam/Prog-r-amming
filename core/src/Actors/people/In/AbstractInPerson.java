package Actors.people.In;

import Actors.MyActor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class AbstractInPerson extends MyActor {
    protected Image image;
    protected int health;
    protected int happines;
    protected int drunk;
    protected int angry;

    private float deltatime;
    private boolean tick = false;
    private int rotate = 1;


    protected boolean moveRotate = true;
    protected float speedX = 0.5f, speedY = 0.5f, angle = 0.0f, rotationSpeed = 0.5f;


    public AbstractInPerson(Stage stage) {
        super(stage);
        x = 100;
        y = 100;
        health = 100;
        happines = 50;
        angry = 0;
        drunk = 0;
    }

    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

    public void move() {
        float dt = Gdx.graphics.getDeltaTime();

        x += speedX * dt;
        y += speedY * dt;
        angle += rotationSpeed * dt;
        while(angle > 360.0f){
            angle -= 360.0f;
        }
        if(x > Statics.WIDTH){speedX = -.5f;}
        if(y > Statics.HEIGHT){speedY = -.5f;}
        if(x < 0.0f){speedX = .5f;}
        if(y < 0.0f){speedY = .5f;}
        setPosition(x,y);
    }

    public void setPosition(int x, int y) {
        image.setPosition(x, y);
    }
}
