package Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Marcin on 2016-05-20.
 */
public class MyActor extends Actor {

    protected TextureAtlas atlas;
    protected Skin skin;
    protected Stage stageIBelongTo;
    protected Image image;
    protected int x, y;

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

    public void setStatistics(){

    }
}
