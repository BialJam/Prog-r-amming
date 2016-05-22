package Actors.people.In.needs;

import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Statics;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Escape extends Need {

    public Escape(AbstractInPerson person) {
        super(person);
    }

    public Array<AbstractInPerson> list;
    private boolean step = false;
    private boolean remove = false;

    @Override
    public void doIt() {

            if (!step) {
                target = Vectors.getQuit();
            } else {
                p.clearNeeds();
                target = new Vector2(0, 210);
            }

            if (Vectors.vectorLength(p.getPersonVector(), target) < 100 && !step) {
                step = true;
                target = new Vector2(0, 210);
            }
            if (Vectors.vectorLength(p.getPersonVector(), target) < 100 && step) {
                remove = true;
            }
            ((Fight)p.allNeeds.get(3)).ico.setVisible(false);
            ((Fight)p.allNeeds.get(3)).ico.image.setVisible(false);
            p.moveTotarget(target);
            if (remove) {
                ((Fight)p.allNeeds.get(3)).ico.setVisible(false);
                ((Fight)p.allNeeds.get(3)).ico.image.setVisible(false);
                p.image.remove();
                p.remove();
//                InSide.persons.removeValue(p,true);
                Statics.world.destroyBody(p.body.body);
                p.body = null;
                p = null;
            }

    }


    public String toString() {
        return "escape";
    }
}
