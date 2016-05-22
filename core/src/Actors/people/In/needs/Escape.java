package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import com.badlogic.gdx.math.Vector2;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Escape extends Need {
    public Escape(AbstractInPerson person) {
        super(person);
    }

    private boolean step = false;

    @Override
    public void doIt() {
        if(!step){
            target = Vectors.getQuit();
        }else{
            target = new Vector2(0,210);
        }

        if(Vectors.vectorLength(p.getPersonVector(), target) < 100 && !step){
            step = true;
            target = new Vector2(0,210);
            System.out.println("lazi se");
        }
        if(Vectors.vectorLength(p.getPersonVector(), target) < 100 && step){
            System.out.println("wukurwiaj");
            p.image.remove();
            InSide.persons.removeValue(p, true);
            p.remove();
        }
        p.moveTotarget(target);
    }

    public String toString(){
        return "escape";
    }
}
