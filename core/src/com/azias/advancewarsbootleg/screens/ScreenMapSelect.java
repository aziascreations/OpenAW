package com.azias.advancewarsbootleg.screens;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenMapSelect extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;

	public ScreenMapSelect(AdvanceWarsBootleg game) {
		this.game = game;
	}

	@Override
	public void create() {
        Gdx.input.setInputProcessor(this);
	}

	public void update () {
		//TODO: Calculate the delta here ?
	}

	public void draw () {
		game.batch.begin();
		//Background
		game.batch.draw(Assets.background,Gdx.graphics.getWidth()/2-Assets.background.getWidth()/2,Gdx.graphics.getHeight()/2-Assets.background.getHeight()/2);
		//Title Parts
		game.batch.draw(Assets.mapSelectParts[0],0,Gdx.graphics.getHeight()-Assets.mapSelectParts[0].getHeight(),Gdx.graphics.getWidth()-Assets.mapSelectParts[2].getWidth(),Assets.mapSelectParts[0].getHeight());
		game.batch.draw(Assets.mapSelectParts[2],Gdx.graphics.getWidth()-Assets.mapSelectParts[2].getWidth(),Gdx.graphics.getHeight()-Assets.mapSelectParts[2].getHeight());
		game.batch.draw(Assets.guiTitles[0], 7, Gdx.graphics.getHeight()-7-Assets.guiTitles[0].getHeight()*2, Assets.guiTitles[0].getWidth()*2, Assets.guiTitles[0].getHeight()*2);
		//Map List Parts
		game.batch.draw(Assets.mapSelectParts[1],0,0,Assets.mapSelectParts[1].getWidth(),Gdx.graphics.getHeight()-Assets.mapSelectParts[0].getHeight());
		
		//Map Infos Parts
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
