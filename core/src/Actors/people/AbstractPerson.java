package Actors.people;

import Actors.MyActor;
import Actors.buttons.AbstractButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Marcin on 2016-05-20.
 */
public class AbstractPerson extends MyActor {

    protected Image head;
    protected Image body;
    protected Image legs;

    public AbstractPerson(Image image, Stage stage) {
        super(image, stage);
    }


    public void setMyOwnClickListener(ClickListener listener){
        head.addListener(listener);
        body.addListener(listener);
        legs.addListener(listener);
    }

    public void setPosition(int x, int y){
        head.setPosition(x,y);
        body.setPosition(x,y);
        legs.setPosition(x,y);
    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

}
