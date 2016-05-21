package Utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Statics;

/**
 * Created by sury on 21.05.16.
 */
public class PersonBody {

    public Body body;

    public PersonBody(Image image) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(700, 400);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = Statics.world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        float width = image.getWidth()/3;
        float height = image.getHeight()/3;
        shape.setAsBox(width ,height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;//przypisanie ciala
        fixtureDef.density = 0.000000000001f;//gestosc
        fixtureDef.friction = 0.2f;//poziom tarcia
        fixtureDef.restitution = 0.7f; // Make it bounce a little bit
        fixtureDef.filter.categoryBits = (short)1;
        fixtureDef.filter.groupIndex = (short)1;
        fixtureDef.filter.maskBits = (short)3;
        body.createFixture(fixtureDef);
        body.setTransform(900,400,0);
    }
}
