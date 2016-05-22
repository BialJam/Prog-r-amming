package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;

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
    }

    public String toString(){
        return "injured";
    }
}
