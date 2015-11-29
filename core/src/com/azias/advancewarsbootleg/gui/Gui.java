package com.azias.advancewarsbootleg.gui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gui extends Object {
	protected int id;
	//protected int childsIDs; //Used when closing the GUI so the childs are closed too.
	protected int[] position = new int[2];
	protected int[] size = new int[2];
	protected ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();
	protected boolean isLockingMouseClick = false;

	/**
	 * Create a GUI at given size and location.
	 * @param posX - X position
	 * @param posY - Y position
	 * @param sizeX - Width
	 * @param sizeY - Height
	 */
	public Gui(int posX, int posY, int sizeX, int sizeY) {
		if(posX==-1) {
			this.position[0] = Gdx.graphics.getWidth()/2-this.size[0]/2;
		} else {
			this.position[0] = posX;
		}
		if(posX==-1) {
			this.position[1] = Gdx.graphics.getHeight()/2-this.size[1]/2;
		} else {
			this.position[1] = posY;
		}
		this.size[0] = sizeX;
		this.size[1] = sizeY;
	}
	
	/**
	 * Create a centered GUI with given size.
	 * @param sizeX = Width
	 * @param sizeY = Height
	 */
	@Deprecated //Until the nextID() function is working
	public Gui(int sizeX, int sizeY) {
		this(-1,-1,sizeX,sizeY);
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
				if(!this.executeButtonAction(this.buttonList.get(i).getId())) {
					return this.buttonList.get(i).getId();
				}
			}
		}
		return null;
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
	}
	
	protected void renderButtons(SpriteBatch batch) {
		for(int i=0; i<this.buttonList.size(); i++) {
			this.buttonList.get(i).render(batch);
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
	
	public void changeID(int id) {
		this.id = id;
	}
	
	public boolean getIsLockingMouseClick() {
		return this.isLockingMouseClick;
	}
}
