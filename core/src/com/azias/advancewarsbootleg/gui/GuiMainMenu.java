package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;

public class GuiMainMenu extends Gui {

	public GuiMainMenu(int id) {
		super(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		this.id = id;
		this.addButtons();
		
	}

	private void addButtons() {
		this.buttonList.add(new GuiButton(1, "newgame", this.size[0]/2-300/2, this.size[1]/2-80/2+60, 300, 80, Utils.getTextFromLang("gui.newgame")));
		this.buttonList.add(new GuiButton(2, "editor", this.size[0]/2-300/2, this.size[1]/2-80/2-60, 300, 80, Utils.getTextFromLang("gui.editor")));
		this.buttonList.add(new GuiButton(3, "options", this.size[0]/2-300/2, this.size[1]/2-80/2-180, 300, 80, Utils.getTextFromLang("gui.options")));
		this.buttonList.add(new GuiButton(4, "exit", this.size[0]/2-300/2, this.size[1]/2-80/2-180-120, 300, 80, Utils.getTextFromLang("gui.exit")));
	}

}
