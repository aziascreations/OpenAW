package com.azias.advancewarsbootleg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenMainMenu extends ScreenAdapter {
	AdvanceWarsBootleg game;

	public ScreenMainMenu(AdvanceWarsBootleg game) {
		this.game = game;
	}

	public void update () {
		
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
		//game.batch.
		//game.batch.
		
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
}
