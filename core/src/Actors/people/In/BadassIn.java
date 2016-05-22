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
        setImages("dres1");
    }
    public void initChances(){
        chances = newChances(30,40,25,5,0,0);
    }
    public void refreshChances(){
        if(happines < 10){
            chances = newChances(0,30,0,60,0,10);
        }else if(drunk > 75){
            chances = newChances(0,30,0,30,30,10);
        }else if(angry > 80){
            chances = newChances(0,30,0,60,0,10);
        }else{
            initChances();
        }
    }
}
