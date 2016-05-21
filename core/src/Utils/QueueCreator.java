package Utils;

import Actors.people.AbstractPerson;
import Actors.people.In.*;
import Actors.people.Out.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.mygdx.game.MyGdxGame;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


/**
 * Created by sury on 21.05.16.
 */
public class QueueCreator {

    static int lvl = 1;
    static int queueMultiplayer = 2;
    static MathUtils math =  new MathUtils();
    static int classes = 4;
    static int desscount = 3;
    static int startQueueSize = 10;
    static Stage stage;
    // Ostroznie, uzyty w zlym miejscu wywali NullPointerException
    static MyGdxGame root;
    static Array<AbstractOutPerson> persons;
    static Array<AbstractInPerson> inPersons = new Array<AbstractInPerson>();

    static HashMap<Class<?>, Class<?>> inMap = new HashMap<Class<?>, Class<?>>(){{
        this.put(BadassOut.class, BadassIn.class);
        this.put(DudeOut.class, DudeIn.class);
        this.put(GirlOut.class, GirlIn.class);
        this.put(GeekOut.class, GeekIn.class);
    }};

    public static Array<AbstractOutPerson> CreateQueue(Array<AbstractOutPerson> previousPersons,Stage stage, MyGdxGame root){
        QueueCreator.persons = previousPersons;
        clearPreviousTable(previousPersons);
        // Nie mozna tego stage pobrac przez root.outside.getGameStage() bo wywala NullPointer przy odpalaniu programu
        QueueCreator.stage = stage;
        QueueCreator.root = root;
        QueueCreator.persons = new Array<AbstractOutPerson>();

        createAndQueuePerson(persons);

        lvl+=1;
        return persons;
    }

    private static void createAndQueuePerson(Array<AbstractOutPerson> persons) {
        for(int i = 0; i < startQueueSize + lvl * queueMultiplayer; i++){
            persons.add(createPerson());
        }
        int randx;

        for(int iteration = 0 ; iteration<persons.size; iteration++){
            randx = MathUtils.random(-70,70);
            persons.get(persons.size - 1 - iteration).setPosition(600 +randx - (iteration+1) *(30),(iteration+1)*70);
            persons.get(persons.size - 1 - iteration).setScale(0.5f,0.5f);
        }

        persons.get(persons.size-1).createClickListener();
        for(int iterator = 0;iterator < persons.size; iterator++){
            if (iterator<startQueueSize - 3){
                persons.get(iterator).image.setVisible(false);
            }
        }

    }

    private static AbstractOutPerson createPerson(){
        int randomed = math.random(0,100);
        AbstractOutPerson person;
        if (randomed<=25){
            return  returnExampleGirl();
        }
        if (randomed <= 50){
            return returnExampleDress();
        }
        if (randomed<= 75){
           return returnExampleDude();
        }
        return returnExampleGeek();
    }

    private static AbstractOutPerson returnExampleDress(){
        return new BadassOut(stage);
    }
    private static AbstractOutPerson returnExampleGirl(){
        return new GirlOut(stage);
    }
    private static AbstractOutPerson returnExampleDude(){
        return new DudeOut(stage);
    }
    private static AbstractOutPerson returnExampleGeek(){
        return new GeekOut(stage);
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
        persons.removeIndex(persons.size-1);
        if (persons.size>=1){
            persons.get(persons.size-1).createClickListener();
        }
        if (persons.size>5){
            persons.get(persons.size-5).image.setVisible(true);
        }
    }

    /**
     * Funkcja do dodawania ludzi do kolejki wejscia.
     * Kolejka ta jest potem przekazywana do screena InSide
     *
     * @param per Osoba ktora wpuscilismy
     **/
    public static void addPersonIn(AbstractOutPerson per){
        Class<?> newPerson = inMap.get(per.getClass());
        java.lang.reflect.Constructor<?> con;
        try {
            con = newPerson.getConstructor(Stage.class);
            root.inside.addPerson((AbstractInPerson) con.newInstance(root.inside.getGameStage()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
