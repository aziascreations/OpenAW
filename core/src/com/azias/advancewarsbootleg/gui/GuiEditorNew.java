package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiEditorNew extends Gui {
	private int sizeX = 10, sizeY = 10;
	
	public GuiEditorNew(int id, int posX, int posY) {
		super(posX, posY, 200, 200);
		this.isLockingMouseClick = true;
		this.addButtons();
		this.id = id;
	}

	private void addButtons() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(-1, "menu.new.cancel", -1, Gdx.graphics.getHeight()/2-60/2+160, 200, 60, Utils.getTextFromLang("gui.cancel")));
		this.buttonList.add(new GuiButton(-1, "menu.new.ok."+sizeX+"."+sizeY, -1, Gdx.graphics.getHeight()/2-60/2+160, 200, 60, Utils.getTextFromLang("gui.ok")));
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], this.size[1]);
		
		int borderWidth = 3;
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+this.size[0]-borderWidth, this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1], this.size[0]-borderWidth*2, borderWidth);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);
		
		this.renderButtons(batch);
	}
	
}
