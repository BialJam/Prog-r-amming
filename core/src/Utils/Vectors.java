package Utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by sury on 21.05.16.
 */
public class Vectors {


    private static MathUtils math = new MathUtils();

    public static Vector2 getEat() {
        return new Vector2(500 + math.random(-120,120),500 + math.random(-120,120));
    }

    public static Vector2 getdance(){
        return new Vector2(565 + math.random(-282,282),500 + math.random(-70,70));
    }
    public static Vector2 getquit(){
        return new Vector2(530 ,560);
    }

    public static Vector2 getRandom(){
        return new Vector2(math.random(300,1000),math.random(100,650));
    }

    public static Vector2 goFight(){
        return new Vector2(math.random(500,700),math.random(250,350));
    }

    public float vectorLength(Vector2 start, Vector2 end){
        Vector2 newVewctor = new Vector2(end.x - start.x, end.y - start.y);
        return  newVewctor.len();
    }
}
