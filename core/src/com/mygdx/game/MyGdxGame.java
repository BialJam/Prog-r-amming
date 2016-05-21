package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.org.apache.xpath.internal.SourceTree;
import screens.InSide;
import screens.Menu;
import screens.OutSide;

public class MyGdxGame extends Game {
    public Menu menu;
    public OutSide outside;
    public InSide inside;

    static public int money = 0;
    static public int alco = 0, food = 0, security = 0, cleaner = 0;

    @Override
    public void create() {
        Statics.createAssets();
        Statics.createWorld();
        menu = new Menu(this);
        outside = new OutSide(this);
        inside = new InSide(this);
        setScreen(outside);
    }


    public String getMoney() {
        return money + "$";
    }

    public String getCleaner() {
        return "Ammount: " + cleaner + "   cost: " + ((cleaner+1) * 20) + " $";
    }

    public String getAlco() {
        return "Ammount: " + alco + "   cost: " + 50 + " $";
    }

    public String getFood() {
        return "Ammount: " + food + "   cost: " + 50 + " $";
    }

    public String getSecurity() {
        return "Ammount: " + security + "   cost: " + ((security+1) * 25) + " $";
    }


    public int getMoneyInt(){return money;}
    public int getAlcoInt(){return alco;}
    public int getFoodInt(){return food;}
    public int getSecurityInt(){return security;}
    public int getCleanerInt(){return cleaner;}

    public void setMoney(int change) {
        money += change;
    }
    public void setAlco(int change) {alco += change;}
    public void setFood(int change) {food += change;}
    public void setSecurity(int change) {
        security += change;
    }
    public void setCleaner(int change) {
        cleaner += change;
    }


}
