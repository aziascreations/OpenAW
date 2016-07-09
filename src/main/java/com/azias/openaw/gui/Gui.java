package com.azias.openaw.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gui {
	protected String id;
	
	public Gui(String id) {
		this.id = id;
	}
	
	/**
	 * Check if any button can be pressed at the given coordinates
	 * @param posX - Click's position on the X axis
	 * @param posY - Click's position on the Y axis
	 * @return *A <b>String</b> containing the pressed button's ID for further use.<br>*A <b>null String</b> to indicate that no further action needs to be done. 
	 */
	public String processMouseClick(int posX, int posY) {
		/*for(int i=0; i<this.buttonList.size(); i++) {
			if(this.buttonList.get(i).isClicked(posX, posY)) {
				if(!this.executeButtonAction(this.buttonList.get(i).getActionId())) {
					return this.buttonList.get(i).getActionId();
				}
			}
		}
		return null;*/
		return null;
	}
	
	public void processKeyboardInput(char character) {
		/*for(int i=0; i<this.textFieldList.size(); i++) {
			this.textFieldList.get(i).processKeyboardInput(character);
		}*/
	}
	
	public void processKeyboardInput(int keycode) {
		/*for(int i=0; i<this.textFieldList.size(); i++) {
			this.textFieldList.get(i).processKeyboardInput(character);
		}*/
	}
	
	public void render(SpriteBatch batch) {
		//this.renderButtons(batch);
		//this.renderTextFields(batch);
	}
	
	public String getId() {
		return this.id;
	}
}
