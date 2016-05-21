package Actors.people.Out;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class GirlOut extends AbstractOutPerson {
    public GirlOut(Stage stage) {
        super(stage);

    }

    public void setImages(){
        atlas = Statics.assetManager.get("Characters_out/Characters_out.pack");
        skin = new Skin(atlas);
        int randomized = Math.abs(rand.nextInt())%2+1;
        image = new Image(skin.getDrawable("loszka"+randomized));
        stageIBelongTo.addActor(image);

    }
}
