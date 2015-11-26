package com.azias.advancewarsbootleg.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GuiButton extends Gui {
	protected String actionId;
	protected TextureRegion texture = null;
	
	/**
	 * @param id - Button's actionId
	 * @param posX - Button's horizontal position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param posY - Button's vertical position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param sizeX - Button's width
	 * @param sizeY - Button's height
	 */
	public GuiButton(String id, int posX, int posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY);
		this.actionId = id;
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
	}
	
	/**
	 * @param id - Button's actionId
	 * @param posX - Button's horizontal position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param posY - Button's vertical position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param sizeX - Button's width
	 * @param sizeY - Button's height
	 * @param tr - Button's texture
	 */
	public GuiButton(String id, int posX, int posY, int sizeX, int sizeY, TextureRegion tr) {
		this(id, posX, posY, sizeX, sizeY);
		this.texture = tr;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if(this.texture!=null) {
			batch.draw(this.texture, this.position[0], this.position[1], this.size[0], this.size[1]);
		} else {
			
		}
	}
	
	public boolean isClicked(int posX, int posY) {
		if(new Rectangle(this.position[0], this.position[1], this.size[0], this.size[1]).contains(posX, posY)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getId() {
		return this.actionId;
	}
}
