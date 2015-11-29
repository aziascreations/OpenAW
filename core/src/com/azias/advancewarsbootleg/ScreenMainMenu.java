package com.azias.advancewarsbootleg;

import com.azias.advancewarsbootleg.gui.GuiMainMenu;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

public class ScreenMainMenu extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;
	private Texture logo;

	public ScreenMainMenu(AdvanceWarsBootleg game) {
		this.game = game;
		this.logo = Assets.loadTexture("gfx/gui/mainmenu/tempLogo.png");
		Datas.coGui.createGui(2, new GuiMainMenu(2));
		Gdx.input.setInputProcessor(this);
	}

	public void update () {
		
	}

	@Override
	public void create() {
		
	}

	@Override
	public void render() {
		
	}

	public void draw () {
		/*GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);

		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
		game.batcher.end();

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
		game.batcher.draw(Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
		game.batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
		game.batcher.end();	*/
		game.batch.begin();
		game.batch.draw(Assets.background,Gdx.graphics.getWidth()/2-Assets.background.getWidth()/2,Gdx.graphics.getHeight()/2-Assets.background.getHeight()/2);
		
		game.batch.draw(this.logo,Gdx.graphics.getWidth()/2-this.logo.getWidth()/2,(float)(Gdx.graphics.getHeight()-this.logo.getHeight()*1.25));
		
		Datas.coGui.render(game.batch);
		
		game.batch.end();
	}

	@Override
	public void render(float delta) {
		update();
		draw();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		//System.out.println("keyDown");
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		//System.out.println("keyUp");
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		//System.out.println("keyTyped");
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//System.out.println("touchDown");
		String actionID = Datas.coGui.processMouseClick(screenX, Gdx.graphics.getHeight()-screenY);
		if(actionID != null) {
			return this.actionPerformed(actionID);
		}
		return false;
	}

	private boolean actionPerformed(String actionID) {
		if(actionID.equals("editor")) {
			
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenMapEditor(this.game));
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//System.out.println("touchUp");
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
