package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-22.
 */
public class Injured extends Need {
    public Injured(AbstractInPerson person) {
        super(person);
    }

    public void doIt(){
        p.body.body.setLinearVelocity(0,0);
        p.isAnimate = false;
        if(time != InSide.getTime()){
            time = InSide.getTime();
            p.health += 5;
            if(p.health >= 0){
                p.need = p.allNeeds.get(5);
            }
        }
    }

    public String toString(){
        return "injured";
    }
}
