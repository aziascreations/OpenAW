package com.azias.advancewarsbootleg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ScreenMapEditor extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;
	private BitmapFont font;

	public ScreenMapEditor(AdvanceWarsBootleg game) {
		this.game = game;	
		this.font = new BitmapFont();
		this.font.setColor(Color.RED);
		//TODO: Change the place where the loadMap function is casted.
		Datas.coMap.loadMap("custom", "develop01");
	}

	@Override
	public void create() {
		Gdx.input.setInputProcessor(this);
	}

	public void update() {
		//TODO: Calculate the delta here ?
	}

	public void draw() {
		game.batch.begin();
		//Background
		game.batch.draw(Assets.background,Gdx.graphics.getWidth()/2-Assets.background.getWidth()/2,Gdx.graphics.getHeight()/2-Assets.background.getHeight()/2);
		
		//Map
		Datas.coMap.render(game.batch);
		
		//Tests
		//font.draw(game.batch, "Hello World", 200, 100);
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
