package Actors.people.Out;

import Actors.MyActor;
import Actors.buttons.AbstractButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
}
