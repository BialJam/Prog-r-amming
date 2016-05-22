package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Statics;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import screens.InSide;

/**
 * Created by Michalina on 2016-05-21.
 */
public class Clock extends MyActor{

    private Image arrow;
    public Clock(Stage stage){
        super(stage);
        setPosition(90,50);
    }

    public void setImages(){
        atlas = Statics.assetManager.get("Other/Other.pack");
        skin = new Skin(atlas);
        image = new Image(skin.getDrawable("clockBody"));
        arrow = new Image(skin.getDrawable("clockArrow"));
        arrow.setOrigin(arrow.getWidth()/2, arrow.getHeight()/2);
        arrow.setPosition(90,50);
        stageIBelongTo.addActor(image);
        stageIBelongTo.addActor(arrow);
    }

    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

    public void setPosition(int x, int y) {
        image.setPosition(x, y);
    }

    @Override
    public void act (float delta) {
        float degre =  360/ InSide.maxtime;
        arrow.rotateBy(-degre);
    }
}
