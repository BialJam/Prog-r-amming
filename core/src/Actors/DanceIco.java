package Actors;

import Actors.people.In.AbstractInPerson;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by sury on 22.05.16.
 */
public class DanceIco extends MyActor {
    AbstractInPerson p;
    public DanceIco(Stage stage, AbstractInPerson per) {
        super(stage);
        p = per;

    }

    public void setImages() {
        super.setImages("Other/Other.pack", "taniec_notify");
        image.remove();
    }
    @Override
    public void setMyOwnClickListener(ClickListener listener) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        image.setPosition(p.image.getX()+20,p.image.getY()+20);
        image.draw(batch,parentAlpha);
    }

    @Override
    public void setPositionNotNormall(int x, int y) {

    }
}
