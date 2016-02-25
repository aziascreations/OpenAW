package com.azias.advancewarsbootleg.tests;

import java.util.Random;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;

/**
 * This screen will be used to test the impact of animations on fps.
 * @author Azias
 */
public class ScreenAnimSlowdownTest extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;
	private long lastFpsOutput;
	private int animationsStarted = 0;
	private Random rand = new Random();

	public ScreenAnimSlowdownTest(AdvanceWarsBootleg game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		this.lastFpsOutput = System.currentTimeMillis();
	}

	@Override
	public void create() {
		
	}

	public void update() {
		if(this.lastFpsOutput+2000 < System.currentTimeMillis()) {
			this.lastFpsOutput = System.currentTimeMillis();
			Gdx.app.log(Utils.getFormatedTime(), "Fps: "+Gdx.graphics.getFramesPerSecond());
		}
		Datas.coAnim.tick();
	}

	public void draw() {
		//Gdx.gl.glClearColor(0.255F, 0.255F, 0.255F, 1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		
		game.batch.begin();
		Datas.coAnim.render(game.batch);
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
		int x = this.rand.nextInt(Gdx.graphics.getWidth()-20)+10;
		int y = this.rand.nextInt(Gdx.graphics.getHeight()-20)+10;
		
		switch (keycode) {
		case Keys.A:
			if(Datas.coAnim.loadAnimation("doomEye", true, "doomEye_"+this.animationsStarted, x, y)) {
				Datas.coAnim.startAnimation("doomEye_"+this.animationsStarted);
			}
			break;
		case Keys.Z:
			if(Datas.coAnim.loadAnimation("explosion_land", true, "explosion_land_"+this.animationsStarted, x, y)) {
				Datas.coAnim.startAnimation("explosion_land_"+this.animationsStarted);
			}
			break;
		}
		this.animationsStarted++;
		return true;
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
