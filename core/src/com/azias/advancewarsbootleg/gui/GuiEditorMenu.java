package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiEditorMenu extends Gui {

	public GuiEditorMenu(int id) {
		super(-1, -1, 240, 240+100);
		this.id = id;
		this.isLockingMouseClick = true;
		this.addButtons();
	}

	private void addButtons() {
		this.buttonList.add(new GuiButton(-1, "menu.open", -1, Gdx.graphics.getHeight()/2-60/2+120, 200, 60, Utils.getTextFromLang("gui.openmap")));
		this.buttonList.add(new GuiButton(-1, "menu.save", -1, Gdx.graphics.getHeight()/2-60/2+40, 200, 60, Utils.getTextFromLang("gui.savemap")));
		this.buttonList.add(new GuiButton(-1, "menu.options", -1, Gdx.graphics.getHeight()/2-60/2-40, 200, 60, Utils.getTextFromLang("gui.options")));
		this.buttonList.add(new GuiButton(-1, "menu.exit", -1, Gdx.graphics.getHeight()/2-60/2-120, 200, 60, Utils.getTextFromLang("gui.exit")));
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
