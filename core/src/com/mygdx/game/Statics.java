package com.mygdx.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import screens.InSide;
import screens.Menu;
import screens.OutSide;

/**
 * Created by Marcin on 2016-05-20.
 */
public class Statics {
    static public int WIDTH = 1366;
    static public int HEIGHT = 768;
    static public int ACTION_COUNT = 10;
    static public World world;
    static public RayHandler rayHandler;
    public static AssetManager assetManager ;


    public static void createAssets(){
        assetManager = new AssetManager();
        assetManager.load("buttons/MenueButton.atlas", TextureAtlas.class);
        assetManager.load("Characters_in/Characters_in.pack", TextureAtlas.class);
        assetManager.load("Characters_out/Characters_out.pack", TextureAtlas.class);


        while(!assetManager.update()){
            //System.out.println(assetManager.getProgress());
        }
    }

    public static void createWorld(){
        world =new World(new Vector2(0, 0), true);
        rayHandler = new RayHandler(world);
        }


}
