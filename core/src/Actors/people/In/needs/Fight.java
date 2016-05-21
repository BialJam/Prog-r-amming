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

    private AbstractInPerson targetPerson;

    @Override
    public void doIt() {
        if (targetPerson == null) {
            targetPerson = InSide.persons.first();
        }

        if (Vectors.vectorLength(p.getPersonVector(), targetPerson.getPersonVector()) < 100) {
            if (time != InSide.getTime()) {
                time = InSide.getTime();
                if (time % 3 == 0) {
                    p.health -= 20;
                    targetPerson.health -= 40;
                    p.angry -= 10;
                    for (AbstractInPerson person : InSide.persons) {
                        person.happines -= 10;
                    }
                    p.finishedWant = true;
                }
            }
        } else {
            p.moveTotarget(targetPerson.getPersonVector());
        }
        if(p.health<=0){
            p.need = p.allNeeds.get(6);
        }
        //zmienia stan dopiero po interwencji ochrony
    }

    public String toString() {
        return "fight";
    }

}
