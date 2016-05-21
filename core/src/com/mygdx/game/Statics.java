package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Marcin on 2016-05-20.
 */
public class Statics {
    static public int WIDTH = 1366;
    static public int HEIGHT = 768;
    static public int ACTION_COUNT = 10;

    public static AssetManager assetManager ;


    public static void createAssets(){
        assetManager = new AssetManager();
        assetManager.load("buttons/MenueButton.atlas", TextureAtlas.class);
        assetManager.load("Characters_front/Dres/Dres_out.pack", TextureAtlas.class);

        while(!assetManager.update()){
            System.out.println(assetManager.getProgress());
        }
    }
}
