package com.azias.advancewarsbootleg;

import com.azias.advancewarsbootleg.screens.ScreenMainMenu;
import com.azias.advancewarsbootleg.screens.ScreenMapEditor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdvanceWarsBootleg extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Datas.side = "client";
		Gdx.app.log(Utils.getFormatedTime(), "Loading Assets...");
		Assets.loadAssets();
		Gdx.app.log(Utils.getFormatedTime(), "Loading Configs...");
		Datas.loadConfigFile();
		Gdx.app.log(Utils.getFormatedTime(), "Loading Lang file...");
		Datas.loadLangFile("english");
		Gdx.app.log(Utils.getFormatedTime(), "Starting JavaScript engine...");
		Datas.startJSEngine();
		Gdx.app.log(Utils.getFormatedTime(), "Loading JavaScript scripts...");
		Datas.loadJSScripts();
		Gdx.app.log(Utils.getFormatedTime(), "Session Key: "+Datas.sessionKey);
		
		if(System.getProperty("awbe.onlyeditor").equals("1")) {
			setScreen(new ScreenMapEditor(this));
		} else {
			setScreen(new ScreenMainMenu(this));
		}
	}

	@Override
	public void render() {
		super.render();
	}
}
