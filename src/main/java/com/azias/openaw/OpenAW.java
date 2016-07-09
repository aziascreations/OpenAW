package com.azias.openaw;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azias.openaw.gui.Gui;
import com.azias.openaw.mod.ModLoader;
import com.azias.openaw.screens.ScreenLoading;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OpenAW extends Game {
	private final static Logger logger = LoggerFactory.getLogger(OpenAW.class);
	public SpriteBatch batch;
	public ModLoader modLoader;
	
	public OpenAW(CommandLine launchArgs) {
		this.modLoader = new ModLoader(launchArgs.getOptionValue("mods"), (launchArgs.hasOption("lang")) ? launchArgs.getOptionValue("lang") : "english");
		this.modLoader.preload();
	}
	
	@Override
	public void create() {
		logger.debug("Initializing SpriteBatch");
		batch = new SpriteBatch();
		
		logger.debug("Preparing other stuff...");
		this.openedGUIs = new ArrayList<Gui>();
		
		logger.debug("Switching screen to ScreenLoading");
		logger.info("- - - - - - - - - - - - - - - - - - - - - -");
		this.setScreen(new ScreenLoading(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	/* GUIs */
	protected ArrayList<Gui> openedGUIs = null;
	
	/**
	 * ???
	 * @param gui
	 * @return <b>True</b> if the GUI has been created or <b>False</b> if a registered GUI already uses the given id. 
	 */
	public boolean createGui(Gui gui) {
		return this.createGui(gui, gui.getId());
	}
	
	/**
	 * ???
	 * @param gui
	 * @param id
	 * @return <b>True</b> if the GUI has been created or <b>False</b> if a registered GUI already uses the given id. 
	 */
	public boolean createGui(Gui gui, String id) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).getId().equals(id)) {
				logger.warn("Unable to register the Gui using {} as an id.", id);
				return false;
			}
		}
		this.openedGUIs.add(gui);
		return true;
	}
	
	/**
	 * ???
	 * @param batch
	 */
	public void renderGuis(SpriteBatch batch) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			this.openedGUIs.get(i).render(batch);
		}
	}
	
	public void guiProcessKeyboardInput(char character) {
		for(Gui gui : this.openedGUIs) {
			gui.processKeyboardInput(character);
		}
	}
	
	//https://github.com/libgdx/libgdx/wiki/Event-handling#example-of-continuous-input-handle
	public void processKeyboardInput(int keycode) {
		for(Gui gui : this.openedGUIs) {
			gui.processKeyboardInput(keycode);
		}
	}

	/**
	 * ???
	 * @param String - GUI's ID
	 * @return <b>True</b> if a GUI with this id already exists or <b>False</b> otherwise.
	 */
	public boolean doesGuiExists(String id) {
		for(Gui gui : this.openedGUIs) {
			if(gui.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove/kill a specific GUI based on the given <b>id</b>.
	 * @param id - GUI's ID
	 * @return <b>True</b> if the specified GUI has been killed.<br><b>False</b> if no GUI with this id has been found.
	 */
	public boolean killGui(String id) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).getId().equals(id)) {
				this.openedGUIs.remove(i);
				return true;
			}
		}
		logger.warn("Unable to kill the Gui using {} as an id.", id);
		return false;
	}
	
	/**
	 * Remove/kill all the GUIs currently registered.
	 * @return <b>True</b>
	 */
	public boolean killAllGuis() {
		this.openedGUIs = new ArrayList<Gui>();
		return true;
	}
	
}
