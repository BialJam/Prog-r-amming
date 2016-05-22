package Actors.people.In;

import Actors.Smoke;
import Actors.Vomit;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Marcin on 2016-05-22.
 */
public class SecurityGuard extends AbstractPersonel {
    public SecurityGuard(Stage stage) {
        super(stage);
    }

    public void setImages(){
        setImages("Characters_in/Characters_in.pack", "dres1");
    }
}
