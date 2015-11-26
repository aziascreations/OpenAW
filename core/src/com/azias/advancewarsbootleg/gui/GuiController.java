package com.azias.advancewarsbootleg.gui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiController extends Object {
	protected boolean isMouseClickLocked = false;
	
	protected ArrayList<Gui> openedGUIs = null;
	
	public GuiController() {
		this.openedGUIs = new ArrayList<Gui>();
	}
	
	public boolean createGui(Gui gui) {
		return this.createGui(-1, gui);
	}
	
	public boolean createGui(int id, Gui gui) {
		//TODO: check if id==gui's id
		this.openedGUIs.add(gui);
		return false;
	}
	
	public void killGui(int id) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			
		}
	}
	
	public void render(SpriteBatch batch) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			this.openedGUIs.get(i).render(batch);
		}
	}
	
	public void tick(long millis) {
		for(int i=0; i<this.openedGUIs.size(); i++) {
			this.openedGUIs.get(i).tick(millis);
		}
	}
}
