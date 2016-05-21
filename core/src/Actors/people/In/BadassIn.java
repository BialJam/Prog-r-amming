package Actors.people.In;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class BadassIn extends AbstractInPerson {

    public BadassIn(Stage stage) {
        super(stage);
    }

    public void setImages(){
        atlas = Statics.assetManager.get("Characters_in/Dresy_in.pack");
        skin = new Skin(atlas);
        image = new Image(skin.getDrawable("dres1"));
        stageIBelongTo.addActor(image);
    }
}
