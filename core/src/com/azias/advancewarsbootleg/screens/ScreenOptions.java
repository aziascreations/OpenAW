package com.azias.advancewarsbootleg.screens;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.gui.GuiOptionsMenu;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenOptions extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;

	public ScreenOptions(AdvanceWarsBootleg game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		Datas.coGui.createGui(1, new GuiOptionsMenu(1));
	}

	@Override
	public void create() {
		
	}

	public void update () {
		//TODO: Calculate the delta here ?
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
