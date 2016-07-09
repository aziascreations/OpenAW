package com.azias.openaw.screens;

import com.azias.openaw.OpenAW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azias.openaw.Assets;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ScreenLoading extends ScreenAdapter implements ApplicationListener, InputProcessor {
	private final static Logger logger = LoggerFactory.getLogger(ScreenLoading.class);
	private OpenAW game;
	private ShapeRenderer shapeRenderer;

	public ScreenLoading(OpenAW game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		this.shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void create() {
		
	}

	public void update () {
		if(this.game.modLoader.update()) {
			logger.info("ModLoader has finished loading.");
			logger.info("- - - - - - - - - - - - - - - - - - - - - -");
			//this.game.setScreen(new ScreenControls(this.game));
			this.game.setScreen(new ScreenMapEditor(this.game));
		}
	}

	public void draw () {
		Gdx.gl30.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		/*this.game.batch.begin();
		
		this.game.batch.end();*/
		
		if(this.shapeRenderer != null) {
			this.shapeRenderer.begin(ShapeType.Filled);
			this.shapeRenderer.setColor(1.0F-Assets.assetsManager.getProgress(), 0.0F+this.game.modLoader.getProgress(), 0.0F, 1.0F);
			this.shapeRenderer.rect(0, 0, Gdx.graphics.getWidth()*this.game.modLoader.getProgress(), 5);
			this.shapeRenderer.end();
		}
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
