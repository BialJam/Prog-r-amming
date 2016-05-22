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
        p.moveTotarget(p.getPersonVector());
    }

    public String toString(){
        return "injured";
    }
}
