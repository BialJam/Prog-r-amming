package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Vomit extends Need {
    public Vomit(AbstractInPerson person) {
        super(person);
    }


    @Override
    public void doIt() {

        if(time != InSide.getTime()) {
            time = InSide.getTime();
            if(time%3 == 0) {
                for (AbstractInPerson person : InSide.persons) {
                    person.happines -= 10;
                }
            }
        }

        //Zmienia stan dopiero po posprzataniu
    }

    public String toString(){
        return "vomit";
    }
}
