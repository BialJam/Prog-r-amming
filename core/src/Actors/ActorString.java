package Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Michalina on 2016-05-20.
 */
public class ActorString extends Actor {

    Stage stage;

    BitmapFont font;
    String toWrite;
    int x,y;

    /**
     *
     * @param font
     * @param toWrite
     * @param x
     * @param y
     */
    public ActorString(BitmapFont font, String toWrite, int x, int y,Stage stage) {
        this.font = font;
        this.toWrite = toWrite;
        this.x = x;
        this.y = y;
        this.stage = stage;
    }
    public void changeString(String string){
        toWrite = string;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector3 newPoints = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
        font.draw(batch,toWrite,x,y);

        // Test pozycji kursora
        newPoints = stage.getViewport().unproject(newPoints);
        toWrite = "X:" + newPoints.x + " Y: " + newPoints.y;
        font.draw(batch,toWrite,newPoints.x,newPoints.y);
    }
}
