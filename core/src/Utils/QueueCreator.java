package Utils;

import Actors.people.AbstractPerson;
import Actors.people.Out.AbstractOutPerson;
import Actors.people.Out.BadassOut;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;


/**
 * Created by sury on 21.05.16.
 */
public class QueueCreator {

    static int lvl = 1;
    static int queueMultiplayer = 2;
    static MathUtils math =  new MathUtils();
    static int classes = 4;
    static int desscount = 3;
    static Stage stage;

    public static Array<AbstractOutPerson> CreateQueue(Stage stage){

        QueueCreator.stage = stage;
        Array<AbstractOutPerson> persons = new Array<AbstractOutPerson>();
        for(int i = 0; i < 10 + lvl * i * queueMultiplayer; i++){
            persons.add(createPerson());
        }
        lvl+=1;
        return persons;
    }

    private static AbstractOutPerson createPerson(){
        int randomed = math.random(0,100);
        AbstractOutPerson person;
        if (randomed<=25){
            //TODO LASKA
            return  returnExampleDress();
        }
        if (randomed <= 50){
            //TODO DRESS
            return returnExampleDress();
        }
        if (randomed<= 75){
//            ToDO Ziom
            return returnExampleDress();
        }
//            TODO Geek
        return returnExampleDress();
    }

    private static AbstractOutPerson returnExampleDress(){
        return new BadassOut(stage);
    }

    private static void clearPreviousTable(Array<AbstractOutPerson> toClearPersons){
        for(AbstractOutPerson per : toClearPersons){
            per.remove();
            for(Image img : per.images){
                img.remove();
            }
        }
    }

}
