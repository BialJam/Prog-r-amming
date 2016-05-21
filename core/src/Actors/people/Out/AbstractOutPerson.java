package Actors.people.Out;

import Actors.MyActor;
import Actors.buttons.AbstractButton;
import Utils.QueueCreator;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marcin on 2016-05-20.
 */
public class AbstractOutPerson extends MyActor {
    public Random rand;
    public boolean isAllowedIn = false;

    public AbstractOutPerson(Stage stage){
        super(stage);
    }

    public void initOtherFields(){
        rand = new Random();
    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

    public void setMyOwnClickListener(ClickListener listener){
        image.addListener(listener);
    }

    public void setPosition(int x, int y){
        image.setPosition(x,y);
    }

    public void moveToRightLeft(boolean allowed){
        if (image.getActions().size == 0){
            if (allowed){
                isAllowedIn = true;
                image.addAction(Actions.sequence(Actions.fadeOut(0.4f),Actions.moveBy(1500,0.05f)));
                QueueCreator.getPersonIn(this);
            }else{
                MathUtils math = new MathUtils();
                if(math.random(0,1) == 0)
                    image.addAction(Actions.parallel(Actions.fadeOut(0.8f),Actions.moveBy(400,200,1f)));

                else
                    image.addAction(Actions.parallel(Actions.fadeOut(0.8f),Actions.moveBy(-400,200,1f)));
            }
            QueueCreator.MoveQueue();
        }

    }

    public void createClickListener(){
        image.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean allow = false;
                if (button == Input.Buttons.LEFT)
                    allow = true;
                else
                    allow = false;
                AbstractOutPerson.this.moveToRightLeft(allow);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void moveFront(){
        image.addAction(Actions.moveBy(0,-70,0.2f));
    }
}
