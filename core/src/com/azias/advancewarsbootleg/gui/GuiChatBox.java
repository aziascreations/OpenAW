package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiChatBox extends Gui {

	public GuiChatBox(int id, int posX, int posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY);
		this.id = id;
		this.addButtons();
	}
	
	private void addButtons() {
		this.buttonList.add(new GuiButton(-1, "chat.send", this.position[0]+this.size[0]-48-10+2, this.position[1]+10, 48, 48, Assets.guiIcons16[2][5]));
		this.textFieldList.add(new GuiTextField(0, this.position[0]+10, this.position[0]+10, this.size[0]-20-48, 48));
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
	
	@Override
	public boolean executeButtonAction(String buttonId) {
		if(buttonId.equals("chat.send")) {
			Datas.coClient.sendInput(this.getTextFieldInput(0));
			this.setTextFieldInput(0, "");
			return true;
		}
		return false;
	}
	
	@Override
	public String processMouseClick(int posX, int posY) {
		for(int i=0; i<this.buttonList.size(); i++) {
			if(this.buttonList.get(i).isClicked(posX, posY)) {
				if(!this.executeButtonAction(this.buttonList.get(i).getActionId())) {
					return this.buttonList.get(i).getActionId();
				}
			}
		}
		for(int i=0; i<this.textFieldList.size(); i++) {
			this.textFieldList.get(i).isClicked(posX, posY);
		}
		return null;
	}
}
