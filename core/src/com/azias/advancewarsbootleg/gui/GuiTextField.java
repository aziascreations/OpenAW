package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GuiTextField extends Gui {
	protected String text;
	protected GlyphLayout glyphLayout;
	protected boolean isSelected = false;
	
	//Temporary workaround for the special characters showing up when they shouldn't(Shift/Up/...)
	protected final String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz1234567890/! .:-+*";
	
	@Deprecated
	private GuiTextField(int posX, int posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY);
		this.text = "";
		this.glyphLayout = new GlyphLayout();
		this.glyphLayout.setText(Assets.font36,text);
	}
	
	public GuiTextField(int id, int posX, int posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY);
		this.id = id;
		this.text = "";
		this.glyphLayout = new GlyphLayout();
		this.glyphLayout.setText(Assets.font36,text);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], this.size[1]);
		
		int borderWidth = 3;
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+this.size[0]-borderWidth, this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1], this.size[0]-borderWidth*2, borderWidth);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);
		
		Assets.font36.draw(batch, this.glyphLayout, this.position[0]+this.size[0]/2-this.glyphLayout.width/2, this.position[1]+this.size[1]/2+this.glyphLayout.height/2);
	}
	
	public boolean isClicked(int posX, int posY) {
		if(new Rectangle(this.position[0], this.position[1], this.size[0], this.size[1]).contains(posX, posY)) {
			this.isSelected = true;
			return true;
		} else {
			this.isSelected = false;
			return false;
		}
	}
	
	@Override
	public void processKeyboardInput(char character) {
		if(this.isSelected) {
			//System.out.println("Added character: "+character+"/"+(int)character);
			if((int)character == 8) {
				if(this.text.length() > 0) {
					this.text = this.text.substring(0, this.text.length() -1);
				}
			} else if((int)character == 13) {
				this.isSelected=false;
			} else if(this.allowedCharacters.contains(String.valueOf(character))){
				this.text += character;
			}
			/*if((int)character != 8 || (int)character != 13) {
				this.text += character;
			} else if((int)character == 8 || this.text.length() > 0) {
				this.text = this.text.substring(0, this.text.length() -1);
			} else if((int)character == 13) {
				this.isSelected=false;
			}*/
			this.glyphLayout.setText(Assets.font36,text);
		}
	}
	
	protected String getText() {
		if(this.text.length() > 0) {
			return this.text;
		} else {
			return null;
		}
	}
}
