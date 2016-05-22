package Actors.people.In;

import Actors.ActorString;
import Actors.MyActor;
import Actors.Smoke;
import Actors.Vomit;
import Actors.people.AbstractPerson;
import Utils.PersonBody;
import Utils.Vectors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-22.
 */
public class AbstractPersonel extends MyActor {
    public boolean free = true;
    public PersonBody body;
    public float speedX = 0.2f, speedY = 0.2f, angle = 0.0f, prev_angle, maxSpeed;
    private int rotate = 1;
    public MyActor target;




    public AbstractPersonel(Stage stage){
        super(stage);
        body = new PersonBody(image);
    }




    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Vector2 position = body.body.getPosition();
        image.setPosition(position.x - image.getWidth() / 2, position.y - image.getHeight() / 3);
        setAngle(body.body.getLinearVelocity());
        move();


    }

    public void move() {
        animate();
        if(target != null){
            if(Vectors.vectorLength(new Vector2(image.getX(), image.getY()), new Vector2(target.image.getX(), target.image.getY()))<50){
                Smoke smoke = (Smoke) target;
                smoke.target.finishedWant = false;
                smoke.target.need = smoke.target.allNeeds.get(5);
                smoke.secondTarget.finishedWant = false;
                smoke.secondTarget.need = smoke.target.allNeeds.get(5);
                target.image.remove();
                target.remove();
                target = null;
                free = true;
            }else{
                moveTotarget(new Vector2(target.image.getX(), target.image.getY()));
            }
        }

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

    public void moveTotarget(Vector2 target){
        Vector2 start = body.body.getPosition();
        Vector2 vector = new Vector2(target.x - start.x,target.y - start.y);
        vector.nor();
        vector.x = vector.x * 10000000;
        vector.y = vector.y * 10000000;
        body.body.setLinearVelocity(vector);
    }




}
