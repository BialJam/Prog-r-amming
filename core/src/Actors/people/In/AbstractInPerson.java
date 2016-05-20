package Actors.people.In;

import Actors.MyActor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Marcin on 2016-05-21.
 */
public class AbstractInPerson extends MyActor {
    protected Image image;
    protected int health;
    protected int happines;
    protected int drunk;
    protected int angry;



    public AbstractInPerson(Stage stage){
        super(stage);
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

    public void move(){
        setPosition(x+10, y+10);
    }
    public void setPosition(int x, int y){
        image.setPosition(x,y);
    }
}
