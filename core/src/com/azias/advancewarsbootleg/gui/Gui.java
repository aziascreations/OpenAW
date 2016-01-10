package com.azias.advancewarsbootleg.gui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gui extends Object {
	protected int id;
	protected int[] position = new int[2];
	protected int[] size = new int[2];
	protected ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();
	protected ArrayList<GuiTextField> textFieldList = new ArrayList<GuiTextField>();
	protected boolean isLockingMouseClick = false;
	protected boolean isGrabbingKeyboardInput = false;
	protected boolean isEnabled = true;

	/**
	 * Create a GUI at given size and location.
	 * @param posX - X position
	 * @param posY - Y position
	 * @param sizeX - Width
	 * @param sizeY - Height
	 */
	public Gui(int posX, int posY, int sizeX, int sizeY) {
		this.size[0] = sizeX;
		this.size[1] = sizeY;
		
		if(posX==-1) {
			this.position[0] = Gdx.graphics.getWidth()/2-this.size[0]/2;
		} else {
			this.position[0] = posX;
		}
		if(posY==-1) {
			this.position[1] = Gdx.graphics.getHeight()/2-this.size[1]/2;
		} else {
			this.position[1] = posY;
		}
	}
	
	/**
	 * Check if any button can be pressed at the given coordinates
	 * @param posX - Click's position on the X axis
	 * @param posY - Click's position on the Y axis
	 * @return *A <b>String</b> containing the pressed button's ID for further use.<br>*A <b>null String</b> to indicate that no further action needs to be done. 
	 */
	public String processMouseClick(int posX, int posY) {
		for(int i=0; i<this.buttonList.size(); i++) {
			if(this.buttonList.get(i).isClicked(posX, posY)) {
				if(!this.executeButtonAction(this.buttonList.get(i).getActionId())) {
					return this.buttonList.get(i).getActionId();
				}
			}
		}
		return null;
	}
	
	/**
	 * @param character - the letter, symbol or other, to add to the selected text field.
	 */
	public void processKeyboardInput(char character) {
		for(int i=0; i<this.textFieldList.size(); i++) {
			this.textFieldList.get(i).processKeyboardInput(character);
		}
	}
	
	/**
	 * Execute registered action(s) based on the given parameter.
	 * @param buttonId - Button's ID
	 * @return <b>True</b> if an action was executed<br><b>False</b> if no action was executed<br>Note that even if an action was executed you can get a <b>False</b> so a second action can be done later.
	 */
	public boolean executeButtonAction(String buttonId) {
		return false;
	}
	
	public void render(SpriteBatch batch) {
		this.renderButtons(batch);
		this.renderTextFields(batch);
	}
	
	protected void renderButtons(SpriteBatch batch) {
		for(int i=0; i<this.buttonList.size(); i++) {
			this.buttonList.get(i).render(batch);
		}
	}
	
	protected void renderTextFields(SpriteBatch batch) {
		for(int i=0; i<this.textFieldList.size(); i++) {
			this.textFieldList.get(i).render(batch);
		}
	}
	
	public final void tick() {
		this.tick(System.currentTimeMillis());
	}
	
	public void tick(long millis) {
		
	}
	
	public int[] getPosition() {
		return this.position;
	}
	
	public int[] getSize() {
		return this.size;
	}
	
	public boolean getIsLockingMouseClick() {
		return this.isLockingMouseClick;
	}
	
	public void changeID(int id) {
		this.id = id;
	}
	
	public String getTextFieldInput(int idTextField) {
		for(int i=0; i<this.textFieldList.size(); i++) {
			if(this.textFieldList.get(i).id == idTextField) {
				return this.textFieldList.get(i).getText();
			}
		}
		return null;
	}

	public boolean transmitInfo(String info) {
		return false;
	}
}
