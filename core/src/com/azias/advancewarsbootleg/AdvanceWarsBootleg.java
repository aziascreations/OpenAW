package com.azias.advancewarsbootleg;

import com.azias.advancewarsbootleg.screens.ScreenMainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdvanceWarsBootleg extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Assets.load();
		//Datas.loadConfigFile();
		setScreen(new ScreenMainMenu(this));
		//setScreen(new ScreenMapSelect(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
