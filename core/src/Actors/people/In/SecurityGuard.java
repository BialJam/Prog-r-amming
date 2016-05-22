package Actors.people.In;

import Actors.Smoke;
import Actors.Vomit;
import Utils.Vectors;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Marcin on 2016-05-22.
 */
public class SecurityGuard extends AbstractPersonel {
    public SecurityGuard(Stage stage) {
        super(stage);
    }

    public void setImages(){
        setImages("Characters_in/Characters_in.pack", "dres1");
    }

    public void move(){
        animate();
        if(target != null){
            if(Vectors.vectorLength(new Vector2(image.getX(), image.getY()), new Vector2(target.image.getX(), target.image.getY()))<50){
                Smoke smoke = (Smoke) target;
                smoke.target.finishedWant = false;
                smoke.target.need = smoke.target.allNeeds.get(5);
                smoke.secondTarget.finishedWant = false;
                smoke.secondTarget.need = smoke.target.allNeeds.get(5);
                target.image.remove();
                target.remove();
                target = null;
                free = true;
            }else{
                moveTotarget(new Vector2(target.image.getX(), target.image.getY()));
            }
        }
    }
}
