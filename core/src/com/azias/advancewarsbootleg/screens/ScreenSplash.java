package com.azias.advancewarsbootleg.screens;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Datas;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenSplash extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;

	public ScreenSplash(AdvanceWarsBootleg game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void create() {
		
	}

	public void update () {
		//TODO: Calculate the delta here ?
	}

	public void draw () {
		//Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
		Gdx.gl.glClearColor(0.255F, 0.255F, 0.255F, 1);
		game.batch.begin();
		
		//game.batch.
		
		game.batch.end();
	}

	@Override
	public void render() {
		draw();
	}

	@Override
	public void render(float delta) {
		update();
		render();
	}

	@Override
	public void pause() {
		
	}

	private boolean actionPerformed(String actionID) {
		if(actionID.equals("save")) {
			
			return true;
		}
		if(actionID.equals("exit")) {
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenMainMenu(this.game));
			return true;
		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		String actionID = Datas.coGui.processMouseClick(screenX, Gdx.graphics.getHeight()-screenY);
		if(actionID != null) {
			return this.actionPerformed(actionID);
		}
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
