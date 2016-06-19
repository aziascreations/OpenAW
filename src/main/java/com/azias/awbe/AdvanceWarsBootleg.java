package com.azias.awbe;

import com.azias.awbe.screens.ScreenSplash;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdvanceWarsBootleg extends Game {
	public SpriteBatch batch;
	
	public AdvanceWarsBootleg() {
		
	}
	
	@Override
	public void create() {
		//System.out.println("Hello World!");
		//Map map = new Map();
		//GsonBuilder gson = new GsonBuilder().setPrettyPrinting();
		//System.out.println(gson.create().toJson(map));
		this.setScreen(new ScreenSplash(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
}
