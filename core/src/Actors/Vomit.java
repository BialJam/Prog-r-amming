package Actors;

import Actors.people.In.CleanWoman;
import Actors.people.In.SecurityGuard;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-22.
 */
public class Vomit extends MyActor {
    private Vomit myInstance;

    public Vomit(Stage stage) {
        super(stage);
        myInstance = this;
        image.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                CleanWoman cw = InSide.getFreeCleanWoman();
                if(cw!=null){
                    cw.free = false;
                    cw.target = myInstance;

                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }

    @Override
    public void setImages() {
        super.setImages("Other/Other.pack", "table1");
    }


}
