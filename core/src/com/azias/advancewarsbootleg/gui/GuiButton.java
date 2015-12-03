package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GuiButton extends Gui {
	protected String actionId;
	protected TextureRegion texture = null;
	protected String text;
	protected GlyphLayout glyphLayout;
	
	/**
	 * @param id - Button's actionId
	 * @param posX - Button's horizontal position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param posY - Button's vertical position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param sizeX - Button's width
	 * @param sizeY - Button's height
	 */
	private GuiButton(int id, String actionId, int posX, int posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY);
		this.id = id;
		this.actionId = actionId;
	}
	
	/**
	 * @param id - Button's id
	 * @param actionId - Button's actionId
	 * @param posX - Button's horizontal position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param posY - Button's vertical position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param sizeX - Button's width
	 * @param sizeY - Button's height
	 * @param tr - Button's texture
	 */
	public GuiButton(int id, String actionId, int posX, int posY, int sizeX, int sizeY, TextureRegion tr) {
		this(id, actionId, posX, posY, sizeX, sizeY);
		this.texture = tr;
	}
	
	/**
	 * @param id - Button's id
	 * @param actionId - Button's actionId
	 * @param posX - Button's horizontal position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param posY - Button's vertical position<br>&nbsp;* If you enter -1, the button will be centered on this axis.
	 * @param sizeX - Button's width
	 * @param sizeY - Button's height
	 * @param text - Button's text
	 */
	public GuiButton(int id, String actionId, int posX, int posY, int sizeX, int sizeY, String text) {
		this(id, actionId, posX, posY, sizeX, sizeY);
		this.text = text;
		this.glyphLayout = new GlyphLayout();
		this.glyphLayout.setText(Assets.font48,text);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if(this.texture!=null) {
			batch.draw(this.texture, this.position[0], this.position[1], this.size[0], this.size[1]);
		} else {
			batch.draw(Assets.arrowFiller, this.position[0], this.position[1], this.size[0], this.size[1]);
			
			int borderWidth = 3;
			batch.draw(Assets.arrowFiller, this.position[0], this.position[1], borderWidth, this.size[1]);
			batch.draw(Assets.arrowFiller, this.position[0]+this.size[0]-borderWidth, this.position[1], borderWidth, this.size[1]);
			batch.draw(Assets.arrowFiller, this.position[0]+borderWidth, this.position[1], this.size[0]-borderWidth*2, borderWidth);
			batch.draw(Assets.arrowFiller, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);
			
			//Not working properly, will fix this one day, maybe.
			//Turns out I just can't do math correctly
			Assets.font48.draw(batch, this.glyphLayout, this.position[0]+this.size[0]/2-this.glyphLayout.width/2, this.position[1]+this.size[1]/2+this.glyphLayout.height/2);
		}
	}
	
	public boolean isClicked(int posX, int posY) {
		if(new Rectangle(this.position[0], this.position[1], this.size[0], this.size[1]).contains(posX, posY)) {
			//Workaround the 0.0F bug.
			if(Datas.volumeEffects>0.0F) {
				Assets.buttonClick.play(Datas.volumeEffects);
			}
			//System.out.println("Button "+this.id+"/"+this.actionId+" pressed");
			return true;
		} else {
			return false;
		}
	}
	
	public String getActionId() {
		return this.actionId;
	}
	
	//Doesn't work when using it in addButton()
	/*public void setOffset(int offsetX, int offsetY) {
		
	}*/
}
