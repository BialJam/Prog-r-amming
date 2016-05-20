package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.org.apache.xpath.internal.SourceTree;
import screens.Menu;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		Statics.createAssets();
		setScreen(new Menu());
	}


}
