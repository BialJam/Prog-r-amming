package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import com.badlogic.gdx.math.Vector2;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Drink extends Need {
    public Drink(AbstractInPerson person) {
        super(person);
        count = 5;
    }



    @Override
    public void doIt() {
        target = Vectors.getEat();

        if(Vectors.vectorLength(p.getPersonVector(), target) < 100){
            if(time != InSide.getTime()) {
                count--;
                time = InSide.getTime();
            }
        }else{
            p.moveTotarget(target);
        }
        if(count == 0){
            count = 5;
            p.finishedWant = true;
            p.drunk += 20;
            p.happines += 20;
        }
    }
}
