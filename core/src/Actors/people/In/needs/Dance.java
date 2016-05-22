package Actors.people.In.needs;

import Actors.DanceIco;
import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Dance extends Need{
    DanceIco ico;
    public Dance(AbstractInPerson person) {
        super(person);
        count = 5;
        ico = new DanceIco(p.stageIBelongTo,p);
        p.stageIBelongTo.addActor(ico);
        ico.setVisible(false);
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
        if(count == 5 && !ico.isVisible()){
            ico.setVisible(true);
        }
        if(count == 0){
            ico.setVisible(false);
            count = 5;
            p.finishedWant = true;
            p.happines += 20;
        }
    }

    public String toString(){
        return "dance";
    }
}
