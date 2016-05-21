package Actors.people.In;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-21.
 */
public class GirlIn extends AbstractInPerson {
    public GirlIn(Stage stage) {
        super(stage);
    }

    public void setImages(){
        setImages("dres1");
    }
    public void initChances(){
        chances = newChances(15,20,45,0,0,20);
    }
    public void refreshChances(){
        if(happines < 10){
            chances = newChances(0,10,0,0,0,90);
        }else if(drunk > 75){
            chances = newChances(0,10,10,0,60,20);
        }else if(angry > 80){
            chances = newChances(0,20,0,0,0,80);
        }else{
            initChances();
        }
    }
}
