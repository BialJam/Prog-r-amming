package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Statics;

/**
 * 0 - losowe chodzenie po planszy
 * Created by Marcin on 2016-05-21.
 */
public class MoveRandomly extends Need {
    AbstractInPerson p;

    public MoveRandomly(AbstractInPerson person) {
        super(person);
    }


    @Override
    public void doIt() {

    }
}
