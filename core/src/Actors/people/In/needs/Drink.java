package Actors.people.In.needs;

import Actors.DrinkIco;
import Actors.people.In.AbstractInPerson;
import Utils.Vectors;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import screens.InSide;

/**
 * Created by Marcin on 2016-05-21.
 */
public class Drink extends Need {
    DrinkIco ico;
    public Drink(AbstractInPerson person) {
        super(person);
        count = 5;
        ico = new DrinkIco(p.stageIBelongTo,p);
        p.stageIBelongTo.addActor(ico);
        ico.setVisible(false);
    }



    @Override
    public void doIt() {
        target = Vectors.getEat();

        if(Vectors.vectorLength(p.getPersonVector(), target) < 100){
            if(time != InSide.getTime()) {
                count--;
                time = InSide.getTime();
            }
        }else{
            p.moveTotarget(target);
        }
        if(count == 5 && !ico.isVisible()){
            ico.setVisible(true);
        }
        if(count == 0){
            ico.setVisible(false);
            count = 5;
            p.finishedWant = true;
            if (MyGdxGame.food>0 && MyGdxGame.alco>0){
                p.drunk += 20;
                p.happines += 20;
                MyGdxGame.alco--;
                MyGdxGame.food--;
            }else if(MyGdxGame.alco > 0){
                p.drunk += 30;
                MyGdxGame.alco--;
            }else{
                p.happines -= 20;
                p.angry += 40;
            }
        }
    }


    public void clearNeed(){
        ico.setVisible(false);
    }


    public String toString(){
        return "drink";
    }
}
