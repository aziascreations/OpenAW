package com.azias.advancewarsbootleg.screens;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
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
		this.logo = Assets.loadTexture("gfx/gui/mainmenu/logo_2.png");
		Datas.coGui.createGui(1, new GuiMainMenu(1));
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
		
		game.batch.draw(this.logo,Gdx.graphics.getWidth()/2-this.logo.getWidth()*3/2,(float)(Gdx.graphics.getHeight()-this.logo.getHeight()*3),this.logo.getWidth()*3,this.logo.getHeight()*3);
		Assets.font48.draw(game.batch, "Indev 4", 10, 40);
		
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
		String actionID = Datas.coGui.processMouseClick(screenX, Gdx.graphics.getHeight()-screenY);
		if(actionID != null) {
			return this.actionPerformed(actionID);
		}
		return false;
	}

	private boolean actionPerformed(String actionID) {
		if(actionID.equals("newgame")) {
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenLobby(this.game));
			return true;
		}
		if(actionID.equals("editor")) {
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenMapEditor(this.game));
			return true;
		}
		if(actionID.equals("options")) {
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenOptions(this.game));
			return true;
		}
		if(actionID.equals("exit")) {
			Gdx.app.exit();
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
