package Actors;

import Actors.people.In.AbstractInPerson;
import Actors.people.In.SecurityGuard;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import screens.InSide;

/**
 * Created by sury on 22.05.16.
 */
public class DrinkIco extends MyActor {
    AbstractInPerson p;
    public DrinkIco(Stage stage,AbstractInPerson per) {
        super(stage);
        p = per;

    }

    public void setImages() {
        super.setImages("Other/Other.pack", "vodka_notify");
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