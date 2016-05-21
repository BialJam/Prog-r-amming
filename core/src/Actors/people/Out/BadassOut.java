package Actors.people.Out;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-20.
 */
public class BadassOut extends AbstractOutPerson{

    public BadassOut(Stage stage){
        super(stage);
    }

    public void setImages(){
        atlas = Statics.assetManager.get("Characters_front/Dres/Dres_out.pack");
        skin = new Skin(atlas);
        int randomized = Math.abs(rand.nextInt())%3+1;
        image = new Image(skin.getDrawable("dres"+randomized));
        stageIBelongTo.addActor(image);

    }

         
}




