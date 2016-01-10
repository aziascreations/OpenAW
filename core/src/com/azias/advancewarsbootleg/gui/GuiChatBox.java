package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiChatBox extends Gui {

	public GuiChatBox(int id, int posX, int posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY);
		this.id = id;
		this.addButtons();
	}
	
	private void addButtons() {
		this.buttonList.add(new GuiButton(-1, "chat.send", this.position[0]+this.size[0]/2-200/2, this.position[1]+5/*this.position[1]+this.size[1]/2-40/2*/, 200, 50, "gui.sendmsg"));
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], this.size[1]);
		
		int borderWidth = 3;
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+this.size[0]-borderWidth, this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1], this.size[0]-borderWidth*2, borderWidth);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);
		
		//batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);
		

		this.renderButtons(batch);
		this.renderTextFields(batch);
	}
	
	@Override
	public void tick(long millis) {
		
	}

}
