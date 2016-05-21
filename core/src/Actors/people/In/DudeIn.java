package Actors.people.In;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class DudeIn extends AbstractInPerson {
    public DudeIn(Stage stage) {
        super(stage);
    }

    public void setImages(){
        setImages("dres1");
    }
}
