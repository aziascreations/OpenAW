package com.azias.advancewarsbootleg.gui;

import com.badlogic.gdx.Gdx;

public class GuiMainMenu extends Gui {

	public GuiMainMenu(int id) {
		super(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		this.addButtons();
		this.id = id;
		
	}

	private void addButtons() {
		this.buttonList.add(new GuiButton(1, "newgame", this.size[0]/2-300/2, this.size[1]/2-80/2+60, 300, 80, "New Game"));
		this.buttonList.add(new GuiButton(2, "editor", this.size[0]/2-300/2, this.size[1]/2-80/2-60, 300, 80, "Map Editor"));
		this.buttonList.add(new GuiButton(3, "options", this.size[0]/2-300/2, this.size[1]/2-80/2-180, 300, 80, "Options"));
	}

}
