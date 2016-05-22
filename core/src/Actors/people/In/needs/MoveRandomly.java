package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Statics;
import screens.InSide;

/**
 * 0 - losowe chodzenie po planszy
 * Created by Marcin on 2016-05-21.
 */
public class MoveRandomly extends Need {

    public MoveRandomly(AbstractInPerson person) {
        super(person);
    }

    @Override
    public void doIt() {
        if(target == null || Vectors.vectorLength(new Vector2(p.image.getX(), p.image.getY()), target) < 40){
            target = Vectors.getRandom();
        }
        if(time != InSide.getTime()){
            count --;
            time = InSide.getTime();
            p.happines -= 1;
        }
        if(count == 0){
            count = 10;
            p.finishedWant = true;
        }
            p.moveTotarget(target);
    }

    public String toString(){
        return "move";
    }
}
