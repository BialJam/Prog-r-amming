package Utils;

import com.badlogic.gdx.math.MathUtils;
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
    private static MathUtils math = new MathUtils();

    public JustABodyWall(int width, int height, int posx, int posy,float rota ) {
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
        fixtureDef.restitution = 0.2f; // Make it bounce a little bit
        wallBody.createFixture(fixtureDef);
        setPosition(posx,posy,rota);
    }

    public void setPosition(float x,float y,float rota){
        rota = math.degreesToRadians * rota;
        wallBody.setTransform(new Vector2(x,y),rota);
    }

    public JustABodyWall(int width, int height, int posx, int posy,float rota,boolean q ) {
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
        fixtureDef.restitution = 0.2f; // Make it bounce a little bit
        fixtureDef.filter.categoryBits = (short)2;
        fixtureDef.filter.groupIndex = (short)2;
        fixtureDef.filter.maskBits = (short)3;
        wallBody.createFixture(fixtureDef);
        setPosition(posx,posy,rota);
    }

}
