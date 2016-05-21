package Utils;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Statics;

/**
 * Created by sury on 21.05.16.
 */
public class JustLights {
    public Body lightBody;
    public PointLight light;

    public JustLights(int posx, int posy,int radius, Color colo) {
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        bodyDef.position.set(700, 700);
//        lightBody = Statics.world.createBody(bodyDef);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(5, 5);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;//przypisanie ciala
//        fixtureDef.density = 1f;//gestosc
//        fixtureDef.friction = 0.9f;//poziom tarcia
//        fixtureDef.restitution = 0.2f; // Make it bounce a little bit
//        lightBody.createFixture(fixtureDef);
//        setPosition(posx,posy);

        light =  new PointLight(Statics.rayHandler,50,colo,radius,posx,posy);
        light.getColor().a=0.75f;
        light.setColor(light.getColor());
        light.setContactFilter((short)1,(short)1,(short)1);
//1

    }

    public void setPosition(float x,float y){
        lightBody.setTransform(new Vector2(x,y),0);
    }
}
