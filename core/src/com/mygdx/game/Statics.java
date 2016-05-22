package com.mygdx.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
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
    public static AssetManager assetManager;
    public static int cleaner_count=1, guard_count=3, alco_count=10, food_count=10;
    public static boolean debug = false;


    public static Music menu;
    public static Array<Music> inside;
    public static Music end;
    public static Music now;

    public static void createAssets() {
        assetManager = new AssetManager();
        assetManager.load("buttons/MenueButton.atlas", TextureAtlas.class);
        assetManager.load("Characters_in/Characters_in.pack", TextureAtlas.class);
        assetManager.load("Characters_out/Characters_out.pack", TextureAtlas.class);
        assetManager.load("Other/Other.pack", TextureAtlas.class);
        assetManager.load("Other/Gui.pack", TextureAtlas.class);
        assetManager.load("Intro/bg.png", Texture.class);
        assetManager.load("buttons/menu_button.png", Texture.class);


        menu = Gdx.audio.newMusic(Gdx.files.getFileHandle("sound/menu.ogg", Files.FileType.Internal));
        end = Gdx.audio.newMusic(Gdx.files.getFileHandle("sound/ending.ogg", Files.FileType.Internal));

        inside = new Array<Music>();
        for(int i=0; i<5; i++){
            inside.add(Gdx.audio.newMusic(Gdx.files.getFileHandle("sound/techno"+i+".ogg", Files.FileType.Internal)));
        }
        now = menu;

        while (!assetManager.update()) {
            //System.out.println(assetManager.getProgress());
        }
    }

    public static void createWorld() {
        world = new World(new Vector2(0, 0), true);
        rayHandler = new RayHandler(world);
    }

    private static boolean insidePlay = false;

    public static void playMusic(String name){


        switch(name){
            case "menu":
                now.stop();
                now = menu;
                insidePlay = false;
                now.play();
                break;
            case "inside":
                if(!insidePlay) {
                    now.stop();
                    now = inside.get(MathUtils.random(0, 4));
                    now.play();
                    insidePlay = true;
                }
                break;
            case "end":
                now.stop();
                now = end;
                now.play();
                break;
            case "stop":
            default:
                now.stop();
                insidePlay = false;
                break;
        }
    }


}
