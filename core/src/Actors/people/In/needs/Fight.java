package Actors.people.In.needs;

import Actors.Smoke;
import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Fight extends Need {
    public Smoke ico;

    public Fight(AbstractInPerson person) {
        super(person);
        count = 5;
        ico = new Smoke(p.stageIBelongTo);
        p.stageIBelongTo.addActor(ico);
        ico.setVisible(false);
//        ico.target.setVisible(false);
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
                    if(ico.target == null) {
                        ico.target = p;
                        ico.secondTarget = targetPerson;
                        ico.image.setScale(0.57f);
                    }
                }
                if(p.health <= 0){
                    ico.setVisible(false);
                    ico.image.setVisible(false);
                }

                p.health -= 5;
                targetPerson.health -= 5;
                p.angry -= 5;
                for (AbstractInPerson person : InSide.persons) {
                    person.angry += 1;
                }
                if (count == 0) {
                    count = 6;
                    p.finishedWant = true;
                    ico.setVisible(false);
//                    ico.target.setVisible(false);
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
