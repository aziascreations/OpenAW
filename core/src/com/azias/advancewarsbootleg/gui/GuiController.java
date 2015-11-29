package com.azias.advancewarsbootleg.gui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiController extends Object {
	//protected boolean isMouseClickLocked = false;
	
	protected ArrayList<Gui> openedGUIs = null;
	
	public GuiController() {
		this.openedGUIs = new ArrayList<Gui>();
	}
	
	@Deprecated //Until the next id thing is finished
	public boolean createGui(Gui gui) {
		return this.createGui(-1, gui);
	}
	
	public boolean createGui(int id, Gui gui) {
		if(gui.id==id) {
			this.openedGUIs.add(gui);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param id - GUI's ID
	 * @return<b>True</b> if a GUI with this id already exists.<br><b>False</b> if no GUI with this id has been found.
	 */
	public boolean doesGuiExists(int id) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).id == id) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * [Insert a desc. here]
	 * @param id - GUI's ID
	 * @return<b>True</b> if the specified GUI has been killed.<br><b>False</b> if no GUI with this id has been found.
	 */
	public boolean killGui(int id) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).id==id) {
				this.openedGUIs.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void killAll() {
		this.openedGUIs.clear();
	}
	
	public void render(SpriteBatch batch) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			this.openedGUIs.get(i).render(batch);
		}
	}

	public final void tick() {
		this.tick(System.currentTimeMillis());
	}
	
	public void tick(long millis) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			this.openedGUIs.get(i).tick(millis);
		}
	}
	
	public boolean isMouseClickLocked() {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).isLockingMouseClick) {
				return true;
			}
		}
		return false;
	}
	
	public String processMouseClick(int posX, int posY) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			String returnedID = this.openedGUIs.get(i).processMouseClick(posX, posY);
			if(returnedID != null) {
				return returnedID;
			}
		}
		return null;
	}
}
