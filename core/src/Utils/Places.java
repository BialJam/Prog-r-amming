package Utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by sury on 21.05.16.
 */
public class Places {

    
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
}
