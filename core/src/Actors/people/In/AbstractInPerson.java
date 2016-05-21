package Actors.people.In;

import Actors.MyActor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class AbstractInPerson extends MyActor {

    protected int health;
    protected int happines;
    protected int drunk;
    protected int angry;

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
        health = 100;
        happines = 50;
        angry = 0;
        drunk = 0;
        angle = 0;
        prev_angle = 0;
        maxSpeed=400;
        animate=0;
        image.setOrigin(image.getWidth()/2, image.getHeight()/2);
        randomize_direct();
    }

    public void randomize_direct(){
        System.out.println(Math.random()%20);
        speedX = (float)((Math.random() * (Math.sqrt(maxSpeed))*2) - (Math.sqrt(maxSpeed)));
        speedY = (float)Math.sqrt( (maxSpeed - speedX*speedX));
        System.out.println(maxSpeed + "+" + speedX + " " + speedY);
    }

    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

    private void animate(){
        animate++;
        if (animate == 13)
            animate = -12;
        if (animate % 3 == 0)
        image.rotateBy(animate);
    }
    
    public void move() {
        float dt = Gdx.graphics.getDeltaTime();

        x += speedX * dt* 15;
        y += speedY * dt* 15;

        if(x >= Statics.WIDTH){speedX *= -1;}
        if(y >= Statics.HEIGHT){speedY *= -1;}
        if(x <= 0.0f){speedX *= -1;}
        if(y <= 0.0f){speedY *= -1;}
    
        setAngle();
        animate();

        setPosition(x, y);
    }

    public void setAngle() {
        double theta = Math.atan2(-speedY, speedX);
        theta += Math.PI / 2.0;
        prev_angle = angle;
        angle = (float)Math.toDegrees(theta);
       
        if (angle < 0) {
            angle += 360;
        }
        
        rotate = (int)prev_angle - (int)angle;
        if (rotate<0){
            rotate += 360;
        }
        image.rotateBy(rotate);
    }

    public void setPosition(int x, int y) {
        image.setPosition(x, y);
    }

    public void setImages(String name){
        setImages("Characters_in/Characters_in.pack", name);
    }

}
