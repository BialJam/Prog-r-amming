package Actors.people.In.needs;

import Actors.Smoke;
import Actors.Vomit;
import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Fight extends Need {
    Smoke smoke;

    public Fight(AbstractInPerson person) {
        super(person);
        count = 5;
    }

    public AbstractInPerson targetPerson;

    @Override
    public void doIt() {
        if (targetPerson == null) {
            targetPerson = InSide.persons.get(0);
            if(targetPerson==p){
                try {
                    targetPerson = InSide.persons.get(1);
                }catch (IndexOutOfBoundsException ex){
                    System.out.println("Pierdol sie");
                    p.finishedWant = true;
                }
            }
        }


        if (Vectors.vectorLength(p.getPersonVector(), targetPerson.getPersonVector()) < 100) {
            if (time != InSide.getTime()) {
                targetPerson.finishedWant = false;
                targetPerson.need = targetPerson.allNeeds.get(3);
                Fight f = (Fight) targetPerson.need;
                f.targetPerson = p;
                time = InSide.getTime();
                if (count == 5) {
                    System.out.println("count3");
                    if(smoke == null) {
                        System.out.println("smokenull");
                        smoke = new Smoke(p.stageIBelongTo);
                        smoke.target = p;
                        smoke.secondTarget = targetPerson;
                        smoke.image.setScale(0.5f);
                        smoke.image.setX(p.image.getX());
                        smoke.image.setY(p.image.getY());
                        p.stageIBelongTo.addActor(smoke);
                    }
                }

                p.health -= 5;
                targetPerson.health -= 5;
                p.angry -= 5;
                for (AbstractInPerson person : InSide.persons) {
                    person.happines -= 10;
                }
                if (count == 0) {
                    System.out.println("jestem");
                    count = 6;
                    p.finishedWant = true;
                    if (smoke != null) {
                        smoke.image.remove();
                        smoke.remove();
                        smoke = null;
                    }
                }

                count --;
            }
        } else {
            p.moveTotarget(targetPerson.getPersonVector());
        }
        if (p.health <= 0) {
            p.need = p.allNeeds.get(6);
        }
        //zmienia stan dopiero po interwencji ochrony
    }

    public String toString() {
        return "fight";
    }

}
