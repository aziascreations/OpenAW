package com.azias.openaw.screens;

import com.azias.openaw.OpenAW;
import com.azias.openaw.tests.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class ScreenMainMenu extends ScreenAdapter implements ApplicationListener, InputProcessor {
	OpenAW game;

	public ScreenMainMenu(OpenAW game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void create() {
		MapExportTest.test();
	}

	public void update () {
		
	}

	public void draw () {
		Gdx.gl.glClearColor(0.8F, 0.8F, 0.8F, 1.0F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.end();
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
