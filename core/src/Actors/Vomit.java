package Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Marcin on 2016-05-22.
 */
public class Vomit extends MyActor {


    public Vomit(Stage stage) {
        super(stage);
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
