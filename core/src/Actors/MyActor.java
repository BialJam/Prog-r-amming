package Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Marcin on 2016-05-20.
 */
public abstract class MyActor extends Actor {

    protected TextureAtlas atlas;
    protected Skin skin;
    protected Stage stageIBelongTo;
    protected Image image;




    public abstract void setMyOwnClickListener(ClickListener listener);


}