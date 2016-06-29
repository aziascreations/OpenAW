package com.azias.openaw.screens;

import com.azias.openaw.AdvanceWarsBootleg;
import com.azias.openaw.Assets;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ScreenLoading extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;
	private ShapeRenderer shapeRenderer;

	public ScreenLoading(AdvanceWarsBootleg game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		this.shapeRenderer = new ShapeRenderer();
		
		//Assets.init();
	}

	@Override
	public void create() {
		
	}

	public void update () {
		
	}

	public void draw () {
		Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		game.batch.end();
		
		if(this.shapeRenderer != null) {
			this.shapeRenderer.begin(ShapeType.Filled);
			this.shapeRenderer.setColor(1.0F-Assets.assetsManager.getProgress(), 0.0F+Assets.assetsManager.getProgress(), 0.0F, 1.0F);
			this.shapeRenderer.rect(0, 0, Gdx.graphics.getWidth()*Assets.assetsManager.getProgress(), 5);
			this.shapeRenderer.end();
		}
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
