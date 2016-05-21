package Actors.people.In;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class GeekIn extends AbstractInPerson {
    public GeekIn(Stage stage) {
        super(stage);
    }

    public void setImages(){
        setImages("dres1");
    }
    public void initChances(){
        chances = newChances(40,10,10,0,0,40);
    }
    public void refreshChances(){
        if(happines < 10){
            chances = newChances(0,0,0,0,0,100);
        }else if(drunk > 75){
            chances = newChances(0,10,40,0,30,10);
        }else if(angry > 80){
            chances = newChances(0,0,0,0,0,100);
        }else{
            initChances();
        }
    }
}
