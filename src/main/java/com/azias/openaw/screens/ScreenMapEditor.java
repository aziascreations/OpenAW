package com.azias.openaw.screens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azias.openaw.OpenAW;
import com.azias.openaw.map.Map;
import com.azias.openaw.mod.ModLoader;
import com.azias.openaw.tests.MapExportTest;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;

public class ScreenMapEditor extends ScreenAdapter implements ApplicationListener, InputProcessor {
	private final static Logger logger = LoggerFactory.getLogger(ModLoader.class);
	private OpenAW game;
	
	private Map map;
	
	private int[] offset = {0, 0};
	
	public ScreenMapEditor(OpenAW game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		this.create();
	}
	
	//Why isn't it called by default...
	@Override
	public void create() {
		//logger.info("- - - - - - - - - - - - - - - - - - - - - -");
		//TODO: Add menu on startup (new/load).
		this.map = new Map(10,15);
		this.map.test2();
		MapExportTest.test();
	}

	public void update () {
		
	}

	public void draw () {
		Gdx.gl30.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		this.game.batch.begin();
		this.map.render(this.game.batch, offset[0], offset[1], 1);
		this.game.renderGuis(this.game.batch);
		this.game.batch.end();
	}

	@Override
	public void render() {
		draw();
	}

	@Override
	public void render(float delta) {
		render();
		update();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		logger.info("keyDown: {}", keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		logger.info("keyTyped: {}", character);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		logger.info("touchDown: {}", button);
		//Clicking out of the window when it's focused seems to trigger it.
		//System.exit(0);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
