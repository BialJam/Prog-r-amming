package Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
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
    protected int animate = 0;
    public float speedX = 0.5f, speedY = 0.5f, angle = 0.0f, prev_angle, maxSpeed;
    private int rotate = 1;

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
    public void setImages(String name){}

    public void setStatistics(){

    }

    public void setImages(String path, String name){
        atlas = Statics.assetManager.get(path);
        skin = new Skin(atlas);
        image = new Image(skin.getDrawable(name));
        stageIBelongTo.addActor(image);
    }

    public void setScale(float x, float y){
        image.setScale(x,y);
    }

// if you need some stragne position setting
    public abstract void setPositionNotNormall(int x,int y);

    protected void animate() {
        animate++;

        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);

        if (animate == 13)
            animate = -12;
        if (animate % 3 == 0)
            image.rotateBy(animate);
    }

    public void setAngle(Vector2 way) {
        double theta = Math.atan2(-way.y, way.x);
        theta += Math.PI / 2.0;
        prev_angle = angle;
        angle = (float) Math.toDegrees(theta);

        if (angle < 0) {
            angle += 360;
        }

        rotate = (int) prev_angle - (int) angle;
        if (rotate < 0) {
            rotate += 360;
        }
        image.rotateBy(rotate);
    }


}
