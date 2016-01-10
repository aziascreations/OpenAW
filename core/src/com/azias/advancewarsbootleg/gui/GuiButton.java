package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GuiButton extends Gui {
	protected String actionId;
	protected TextureRegion texture = null;
	//protected String text;
	protected GlyphLayout glyphLayout;
	protected GuiStyle guiStyle;
	private final String defaultStyleKey = "styleButtonBasic";
	
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
		this.guiStyle = Assets.getGuiStyle(this.defaultStyleKey);
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
		//this.text = text;
		this.glyphLayout = new GlyphLayout();
		this.glyphLayout.setText(Assets.font48,text);
	}
	
	public GuiButton(int id, String actionId, int posX, int posY, int sizeX, int sizeY, TextureRegion tr, String styleKey) {
		this(id, actionId, posX, posY, sizeX, sizeY, tr);
		this.guiStyle = Assets.getGuiStyle(styleKey);
	}
	
	public GuiButton(int id, String actionId, int posX, int posY, int sizeX, int sizeY, String text, String styleKey) {
		this(id, actionId, posX, posY, sizeX, sizeY, text);
		this.guiStyle = Assets.getGuiStyle(styleKey);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if(this.texture!=null) {
			batch.draw(this.texture, this.position[0], this.position[1], this.size[0], this.size[1]);
		} else {
			/*batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], this.size[1]);
			int borderWidth = 3;
			batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], borderWidth, this.size[1]);
			batch.draw(Assets.guiDefaultBack, this.position[0]+this.size[0]-borderWidth, this.position[1], borderWidth, this.size[1]);
			batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1], this.size[0]-borderWidth*2, borderWidth);
			batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);*/
			
			//testing something
			/*int a = Assets.guiStyleButton[0].getRegionWidth();
			int b = Assets.guiStyleButton[0].getRegionHeight();
			batch.draw(Assets.guiStyleButton[0], this.position[0], this.position[1]+this.size[1]-b);
			batch.draw(Assets.guiStyleButton[1], this.position[0]+a, this.position[1]+this.size[1]-b,this.size[0]-2*a,b);
			batch.draw(Assets.guiStyleButton[2], this.position[0]+this.size[0]-a, this.position[1]-b+this.size[1]);
			batch.draw(Assets.guiStyleButton[3], this.position[0], this.position[1]+b, a, this.size[1]-b*2);
			batch.draw(Assets.guiStyleButton[4], this.position[0]+a, this.position[1]+b, this.size[0]-a*2, this.size[1]-b*2);
			batch.draw(Assets.guiStyleButton[5], this.position[0]+this.size[0]-a, this.position[1]+b, a, this.size[1]-b*2);
			batch.draw(Assets.guiStyleButton[6], this.position[0], this.position[1]);
			batch.draw(Assets.guiStyleButton[7], this.position[0]+a, this.position[1],this.size[0]-2*a,b);
			batch.draw(Assets.guiStyleButton[8], this.position[0]+this.size[0]-a, this.position[1]);*/
			
			this.guiStyle.render(batch, this.size, this.position);
			
			if(this.isEnabled) {
				Assets.font48.draw(batch, this.glyphLayout, this.position[0]+this.size[0]/2-this.glyphLayout.width/2, this.position[1]+this.size[1]/2+this.glyphLayout.height/2);
			} else {
				Color c = batch.getColor();
				batch.setColor(191, 191, 191, 255);
				Assets.font48.draw(batch, this.glyphLayout, this.position[0]+this.size[0]/2-this.glyphLayout.width/2, this.position[1]+this.size[1]/2+this.glyphLayout.height/2);
				batch.setColor(c);
			}
			
		}
	}
	
	public boolean isClicked(int posX, int posY) {
		if(new Rectangle(this.position[0], this.position[1], this.size[0], this.size[1]).contains(posX, posY)) {
			//Workaround the 0.0F bug.
			if(Datas.volumeEffects>0.0F) {
				Assets.soundButtonClick.play(Datas.volumeEffects);
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
