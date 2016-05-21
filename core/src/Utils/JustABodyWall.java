package Utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Statics;

/**
 * Created by sury on 21.05.16.
 */
public class JustABodyWall {
    private Body wallBody;


    public JustABodyWall(int width, int height, int posx, int posy ) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(700, 700);
        wallBody = Statics.world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;//przypisanie ciala
        fixtureDef.density = 1f;//gestosc
        fixtureDef.friction = 0.9f;//poziom tarcia
        fixtureDef.restitution = 0.0f; // Make it bounce a little bit
        wallBody.createFixture(fixtureDef);
        setPosition(posx,posy);
    }

    public void setPosition(float x,float y){
        wallBody.setTransform(new Vector2(x,y),0);
    }
}
