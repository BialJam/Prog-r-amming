package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Dance extends Need{
    public Dance(AbstractInPerson person) {
        super(person);
        count = 5;
    }

    @Override
    public void doIt() {
        target = Vectors.getDance();

        if(Vectors.vectorLength(p.getPersonVector(), target) < 100){
            if(time != InSide.getTime()) {
                count--;
                time = InSide.getTime();
            }
        }
            p.moveTotarget(target);

        if(count == 0){
            count = 5;
            p.finishedWant = true;
            p.happines += 20;
        }
    }

    public String toString(){
        return "dance";
    }
}
