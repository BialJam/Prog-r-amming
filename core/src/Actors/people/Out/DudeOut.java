package Actors.people.Out;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class DudeOut extends AbstractOutPerson {
    public DudeOut(Stage stage) {
        super(stage);
    }

    public void setImages(){
        setImages("ziomek");
    }
}
