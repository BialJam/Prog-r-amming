package screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Statics;

/**
 * Created by Marcin on 2016-05-20.
 */
public class Menu extends ApplicationAdapter implements  AbstractScreen {


    TextureAtlas atlas = null;
    Skin skin = null;

    public Menu() {
        super();


        atlas = Statics.assetManager.get("buttons/MenueButton.atlas");
        skin = new Skin(atlas);
        int x;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
