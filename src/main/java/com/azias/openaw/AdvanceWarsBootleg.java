package com.azias.openaw;

import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azias.openaw.mod.ModLoader;
import com.azias.openaw.screens.ScreenLoading;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdvanceWarsBootleg extends Game {
	private final static Logger logger = LoggerFactory.getLogger(AdvanceWarsBootleg.class);
	public SpriteBatch batch;
	public ModLoader modLoader;
	
	public AdvanceWarsBootleg(CommandLine launchArgs) {
		this.modLoader = new ModLoader(launchArgs.getOptionValue("m"));
		this.modLoader.preload();
	}
	
	@Override
	public void create() {
		logger.debug("Initializing SpriteBatch");
		batch = new SpriteBatch();
		
		logger.debug("Switching screen to ScreenLoading");
		logger.info("- - - - - - - - - - - - - - - - - - - - - -");
		this.setScreen(new ScreenLoading(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
}
