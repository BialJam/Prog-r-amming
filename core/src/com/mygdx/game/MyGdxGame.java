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
	SpriteBatch batch;
	Texture img;
	public Menu menu;
	public OutSide outside;
	public InSide inside;

	
	@Override
	public void create () {
		Statics.createAssets();
		menu = new Menu(this);
		outside = new OutSide(this);
		inside = new InSide(this);
		setScreen(new Menu(this));

	}


}
