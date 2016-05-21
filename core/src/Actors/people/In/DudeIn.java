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
    public void initChances(){
        chances = newChances(30,30,30,0,0,10);
    }
    public void refreshChances(){
        if(happines < 10){
            chances = newChances(0,0,0,0,0,100);
        }else if(drunk > 75){
            chances = newChances(10,30,30,10,10,10);
        }else if(angry > 80){
            chances = newChances(0,40,5,5,0,50);
        }else{
            initChances();
        }
    }
}
