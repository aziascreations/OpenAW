package com.azias.advancewarsbootleg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdvanceWarsBootleg extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Assets.load();
		setScreen(new ScreenMainMenu(this));
		//setScreen(new ScreenMapSelect(this));
		//setScreen(new ScreenOptions(this));
		//setScreen(new ScreenMapEditor(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
