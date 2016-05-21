package Utils;

import Actors.people.AbstractPerson;
import Actors.people.In.*;
import Actors.people.Out.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Statics;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


/**
 * Created by sury on 21.05.16.
 */
public class QueueCreator {

    static int lvl = 1;
    static int queueMultiplayer = 2;
    static MathUtils math = new MathUtils();
    static int classes = 4;
    static int desscount = 3;
    static int startQueueSize = 20;
    static Stage stage;
    // Ostroznie, uzyty w zlym miejscu wywali NullPointerException
    static MyGdxGame root;
    static Array<AbstractOutPerson> persons;



    static HashMap<Class<?>, Class<?>> inMap = new HashMap<Class<?>, Class<?>>(){{
        this.put(BadassOut.class, BadassIn.class);
        this.put(DudeOut.class, DudeIn.class);
        this.put(GirlOut.class, GirlIn.class);
        this.put(GeekOut.class, GeekIn.class);
    }};

    public static Array<AbstractOutPerson> CreateQueue(Array<AbstractOutPerson> previousPersons, Stage stage, MyGdxGame root) {
        QueueCreator.persons = previousPersons;
        clearPreviousTable(previousPersons);
        // Nie mozna tego stage pobrac przez root.outside.getGameStage() bo wywala NullPointer przy odpalaniu programu
        QueueCreator.stage = stage;
        QueueCreator.root = root;
        QueueCreator.persons = new Array<AbstractOutPerson>();

        createAndQueuePerson(persons);

        lvl += 1;
        return persons;
    }

    public static void shade() {
        int randx;
        for (int iteration = 0; iteration < persons.size; iteration++) {
            persons.get(persons.size - 1 - iteration).image.clearActions();
            randx = MathUtils.random(-35, 35);
            int Y = (int) (( Math.log((double)(iteration+1)) * 160) + 40);
            int X = 630 + randx - (iteration + 1) * (16);
            if (Y>360){
                if (Y<390) X -= 10;
                if (Y<400) X -= 10;
                if (Y<410) X -= 10;
                if (Y<420) X -= 10;
                if (Y<430) X -= 10;
                if (Y<440) X -= 10;
                if (Y<450) X -= 10;
                if (Y<460) X -= 10;
            }
            if (Y>460) X+=50;
            if (Y>490) X+=60;
            if (Y>520) X+=50;

            persons.get(persons.size - 1 - iteration).setPosition(X, Y);
            float val = ( 72 - ((persons.get(persons.size - 1 - iteration).image.getY())/8)) / (float)76;
            if (val<=0.1f) val = 0.1f;
            persons.get(persons.size - 1 - iteration).image.addAction(Actions.color(new Color(val, val, val, 1)));
            persons.get(persons.size - 1 - iteration).image.setScale(val*0.7f);
        }
    }

    private static void createAndQueuePerson(Array<AbstractOutPerson> persons) {
        for (int i = 0; i < startQueueSize + lvl * queueMultiplayer; i++) {
            persons.add(createPerson());
        }
        int randx;

        shade();

        persons.get(persons.size - 1).createClickListener();

    }

    private static AbstractOutPerson createPerson() {
        int randomed = math.random(0, 100);
        AbstractOutPerson person;
        if (randomed <= 25) {
            return returnExampleGirl();
        }
        if (randomed <= 50) {
            return returnExampleDress();
        }
        if (randomed <= 75) {
            return returnExampleDude();
        }
        return returnExampleGeek();
    }

    private static AbstractOutPerson returnExampleDress() {
        return new BadassOut(stage);
    }

    private static AbstractOutPerson returnExampleGirl() {
        return new GirlOut(stage);
    }

    private static AbstractOutPerson returnExampleDude() {
        return new DudeOut(stage);
    }

    private static AbstractOutPerson returnExampleGeek() {
        return new GeekOut(stage);
    }

    // Czysci poprzednie dni z Ludzi
    private static void clearPreviousTable(Array<AbstractOutPerson> toClearPersons) {
        for (AbstractOutPerson per : toClearPersons) {
            per.remove();
            per.image.remove();

        }
    }

    public static void MoveQueue() {
        root.outside.action-- ;
        for (AbstractOutPerson per : persons) {
            per.moveFront();
        }
        persons.removeIndex(persons.size - 1);
        if (persons.size >= 1) {
            persons.get(persons.size - 1).createClickListener();
        }
        if (persons.size > 5) {
            persons.get(persons.size - 5).image.setVisible(true);
        }
    }

    /**
     * Funkcja do dodawania ludzi do kolejki wejscia.
     * Kolejka ta jest potem przekazywana do screena InSide
     *
     * @param per Osoba ktora wpuscilismy
     **/
    public static void addPersonIn(AbstractOutPerson per) {
        Class<?> newPerson = inMap.get(per.getClass());
        java.lang.reflect.Constructor<?> con;
        try {
            con = newPerson.getConstructor(Stage.class);
            AbstractInPerson person = (AbstractInPerson) con.newInstance(root.inside.getGameStage());
            person.setImages(per.image.getDrawable().toString());
            root.inside.addPerson(person);
            root.setMoney(10);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
