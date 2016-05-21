package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Fight extends Need {
    public Fight(AbstractInPerson person) {
        super(person);
        count = 5;
    }

    @Override
    public void doIt() {
        target = Vectors.goFight();

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
        }
    }

}
