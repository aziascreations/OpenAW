package com.azias.advancewarsbootleg.screens;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.gui.GuiLobbyMain;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenLobby extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;

	public ScreenLobby(AdvanceWarsBootleg game) {
		this.game = game;
	}

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
		Datas.coGui.createGui(1, new GuiLobbyMain(1));
	}

	public void update () {
		
	}

	public void draw () {
		game.batch.begin();
		//Background
		game.batch.draw(Assets.background,Gdx.graphics.getWidth()/2-Assets.background.getWidth()/2,Gdx.graphics.getHeight()/2-Assets.background.getHeight()/2);
		
		Datas.coGui.render(game.batch);
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
	
	private boolean actionPerformed(String actionID) {
		if(actionID.equals("exit")) {
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenMainMenu(this.game));
			return true;
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
