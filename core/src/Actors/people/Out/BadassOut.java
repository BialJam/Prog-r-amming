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
        atlas = Statics.assetManager.get("Characters_front/Dres/Paczka1.atlas");
        skin = new Skin(atlas);
        images.add(new Image(skin.getDrawable("Dres_head"+Math.abs(rand.nextInt())%2)));
        images.add(new Image(skin.getDrawable("Dres_torso"+Math.abs(rand.nextInt())%2)));
        images.add(new Image(skin.getDrawable("Dres_legs"+Math.abs(rand.nextInt())%2)));
        int i = Math.abs(rand.nextInt()%500);
        int j = Math.abs(rand.nextInt()%500);
        for(Image img: images){
            img.setPosition(i,j);
            stageIBelongTo.addActor(img);
        }

         
    }



}
