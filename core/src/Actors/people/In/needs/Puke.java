package Actors.people.In.needs;

import Actors.Vomit;
import Actors.people.In.AbstractInPerson;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Puke extends Need {
    public Puke(AbstractInPerson person) {
        super(person);
    }


    @Override
    public void doIt() {

        if(time != InSide.getTime()) {
            time = InSide.getTime();
            if(time%3 == 0) {
                p.drunk -= 10;
                p.health -= 10;
                for (AbstractInPerson person : InSide.persons) {
                    person.happines -= 10;
                }
                p.finishedWant = true;
                Vomit vomit = new Vomit(p.stageIBelongTo);
                vomit.image.setScale(0.2f);
                vomit.image.setX(p.image.getX());
                vomit.image.setY(p.image.getY());
                p.stageIBelongTo.addActor(vomit);
            }
        }
    }

    public String toString(){
        return "puke";
    }
}
