package Utils;

import Actors.people.AbstractPerson;
import Actors.people.Out.AbstractOutPerson;
import Actors.people.Out.BadassOut;
import Actors.people.Out.GirlOut;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    static Array<AbstractOutPerson> persons;
    static Array<AbstractOutPerson> inPersons = new Array<AbstractOutPerson>();

    public static Array<AbstractOutPerson> CreateQueue(Array<AbstractOutPerson> previousPersons,Stage stage){
        QueueCreator.persons = previousPersons;
        clearPreviousTable(previousPersons);
        QueueCreator.stage = stage;
        QueueCreator.persons = new Array<AbstractOutPerson>();

        createAndQueuePerson(persons);

        lvl+=1;
        return persons;
    }

    private static void createAndQueuePerson(Array<AbstractOutPerson> persons) {
        for(int i = 0; i < 10 + lvl * queueMultiplayer; i++){
            persons.add(createPerson());
        }
        int randx;

        for(int iteration = 0 ; iteration<persons.size; iteration++){
            randx = MathUtils.random(-70,70);
            persons.get(persons.size - 1 - iteration).setPosition(400 +randx,(iteration+1)*70);
            persons.get(persons.size - 1 - iteration).setScale(0.5f,0.5f);
        }
        for (AbstractOutPerson per : persons){
            per.createClickListener();
        }

    }

    private static AbstractOutPerson createPerson(){
        int randomed = math.random(0,100);
        AbstractOutPerson person;
        if (randomed<=25){
            return  returnExampleGirl();
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

    private static AbstractOutPerson returnExampleGirl(){
        return new GirlOut(stage);
    }
    // Czysci poprzednie dni z Ludzi
    private static void clearPreviousTable(Array<AbstractOutPerson> toClearPersons){
        for(AbstractOutPerson per : toClearPersons){
            per.remove();
            per.image.remove();

        }
    }

    public static void MoveQueue(){
        for(AbstractOutPerson per : persons){
            per.moveFront();
        }
    }

    public static void getPersonIn(AbstractOutPerson per){
        inPersons.add(per);
    }

}
