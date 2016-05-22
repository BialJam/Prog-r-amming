package Actors;

/**
 * Created by Marcin on 2016-05-22.
 */

import Actors.people.In.AbstractInPerson;
import Actors.people.In.SecurityGuard;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-22.
 */
public class Smoke extends MyActor {
    private Smoke myInstance;
    public AbstractInPerson target;
    public AbstractInPerson secondTarget;

    public Smoke(Stage stage) {
        super(stage);
        myInstance = this;
        image.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SecurityGuard sg = InSide.getFreeGuard();
                if(sg!=null){
                    sg.free = false;
                    sg.target = myInstance;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void setImages() {
        super.setImages("Other/Other.pack", "wykrz_notify");
    }
    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(target!=null){
            image.setPosition(target.image.getX()+20,target.image.getY()+20);
        }

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }
}