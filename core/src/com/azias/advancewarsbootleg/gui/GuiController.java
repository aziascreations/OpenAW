package com.azias.advancewarsbootleg.gui;

import java.util.ArrayList;

import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiController extends Object {
	protected ArrayList<Gui> openedGUIs = null;
	
	public GuiController() {
		this.openedGUIs = new ArrayList<Gui>();
	}
	
	@Deprecated //Until the next id thing is finished
	public boolean createGui(Gui gui) {
		return this.createGui(-1, gui);
	}
	
	//TODO: Remove the id parameter and check if a GUI with this exist
	public boolean createGui(int id, Gui gui) {
		if(gui.id==id) {
			this.openedGUIs.add(gui);
			return true;
		} else {
			Gdx.app.log(Utils.getFormatedTime(), "Error: Unable to create GUI n°"+id);
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
	 * Remove/kill a specific GUI based on the given <b>id</b>.
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
		Gdx.app.log(Utils.getFormatedTime(), "Error: Unable to kill GUI n°"+id);
		return false;
	}
	
	/**
	 * Remove/kill all GUIs in the openedGUIs ArrayList.
	 * It works as a sort of reset.
	 */
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
		//Going trough all opened GUIs to see if a button has been clicked.
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).isEnabled) {
				String returnedID = this.openedGUIs.get(i).processMouseClick(posX, posY);
				if(returnedID != null) {
					return returnedID;
				}
			}
		}
		//Going trough all opened GUIs to see if any instance of GuiButton is clicked.
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i) instanceof GuiButton) {
				if(((GuiButton)this.openedGUIs.get(i)).isClicked(posX, posY)) {
					return ((GuiButton)this.openedGUIs.get(i)).actionId;
				}
			}
		}
		return null;
	}
	
	/**
	 * Enable or disable a GUI based on the given <b>id</b> and <b>boolean</b>.
	 * @param id - GUI's ID
	 * @param state - Specified GUI's isEnabled boolean.
	 * @return<b>True</b> if the GUI was found and set to the desired parameter.<br><b>False</b> if the desired GUI wasn't found.
	 */
	public boolean setEnableGuiState(int id, boolean state) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).id == id) {
				this.openedGUIs.get(i).isEnabled = state;
				return true;
			}
		}
		return false;
	}
	
	public boolean processKeyboardInput(char character) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			this.openedGUIs.get(i).processKeyboardInput(character);
		}
		return true;
	}
	
	public String getTextFieldInput(int idTextField) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			String text = this.openedGUIs.get(i).getTextFieldInput(idTextField);
			if(text!=null) {
				return text;
			}
		}
		return null;
	}
	
	@Deprecated
	public boolean transmitInfo(String info) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).transmitInfo(info)) {
				return true;
			}
		}
		return false;
	}
	
	@Deprecated
	public boolean transmitInfo(int id, String info) {
		return this.openedGUIs.get(id).transmitInfo(info);
	}
	
	public Gui getGui(int id) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			if(this.openedGUIs.get(i).id==id) {
				return this.openedGUIs.get(i);
			}
		}
		return null;
	}
}
