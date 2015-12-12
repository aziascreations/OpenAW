package com.azias.advancewarsbootleg;

import com.azias.advancewarsbootleg.screens.ScreenMainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdvanceWarsBootleg extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.app.log(Utils.getFormatedTime(), "Loading Assets...");
		Assets.load();
		Gdx.app.log(Utils.getFormatedTime(), "Loading Configs...");
		Datas.loadConfigFile();

		Gdx.app.log(Utils.getFormatedTime(), "Session Key: "+Datas.sessionKey);
		
		setScreen(new ScreenMainMenu(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
