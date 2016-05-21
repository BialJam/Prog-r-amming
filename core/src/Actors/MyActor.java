package Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
public abstract class MyActor extends Actor {


    public TextureAtlas atlas;
    public Skin skin;
    public Stage stageIBelongTo;
    public Image image;
    public int x=0, y=0;

//      set image and stage
//      add this actor and his image to stage
    public MyActor(Image image, Stage stage) {
        this.image = image;
        this.stageIBelongTo =  stage;
        stageIBelongTo.addActor(image);
        stageIBelongTo.addActor(this);
    }

//    write new click listener and add it by this metod
    public abstract void setMyOwnClickListener(ClickListener listener);


    public void setPosition(int x, int y){
        image.setPosition(x, y);
    }

    public MyActor() {

    }
    public MyActor(Stage stage){
        this.stageIBelongTo = stage;
        initOtherFields();
        setImages();
        setStatistics();
    }

    public void initOtherFields(){

    }

    public void setImages(){

    }
    public void setImages(String path, String name){
        atlas = Statics.assetManager.get(path);
        skin = new Skin(atlas);
        image = new Image(skin.getDrawable(name));
        stageIBelongTo.addActor(image);
    }

    public void setStatistics(){

    }

    public void setScale(float x, float y){
        image.setScale(x,y);
    }

// if you need some stragne position setting
    public abstract void setPositionNotNormall(int x,int y);


}
