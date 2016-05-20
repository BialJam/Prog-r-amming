package Actors.buttons;

import Actors.MyActor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-20.
 */
public class AbstractButton extends MyActor {

    Image imagePressed;


    public AbstractButton(Image image, Stage stage) {
        this.image = image;
        this.stageIBelongTo =  stage;
        stage.addActor(image);
        stage.addActor(this);
    }

    @Override
    public void setMyOwnClickListener(ClickListener listener){
        image.addListener(listener);
    }

    @Override
    public void act(float delta) {}

    public void setPosition(int x,int y){
        image.setPosition(x,y);
    }
}
