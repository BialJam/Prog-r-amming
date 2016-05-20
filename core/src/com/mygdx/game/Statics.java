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

    public static AssetManager assetManager = new AssetManager();


    public static void createAssets(){
//        assetManager.load("MenueButton.atlas", TextureAtlas.class);
        assetManager.load("badlogic.jpg", Texture.class);
        int x = 0;
    }
}
