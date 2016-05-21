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
        float dt = Gdx.graphics.getDeltaTime();

        p.x += p.speedX * dt* 15;
        p.y += p.speedY * dt* 15;

        if(p.x >= Statics.WIDTH){p.speedX *= -1;}
        if(p.y >= Statics.HEIGHT){p.speedY *= -1;}
        if(p.x <= 0.0f){p.speedX *= -1;}
        if(p.y <= 0.0f){p.speedY *= -1;}
    }
}
