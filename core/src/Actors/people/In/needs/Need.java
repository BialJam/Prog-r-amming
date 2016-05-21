package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import com.badlogic.gdx.math.Vector2;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Need {
    AbstractInPerson p;
    protected int count = 10;
    protected int time = -1;
    protected Vector2 target;

    public Need(AbstractInPerson person){
        this.p = person;
    }
    public void doIt(){
        if(time != InSide.getTime()){
            time = InSide.getTime();
            p.happines -= 5;
        }
    }
}
