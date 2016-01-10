package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiStyle extends Object {
	private TextureRegion[] texture;
	private String textureKey, styleKey;
	private boolean isStretched = true, isInternal = true;
	
	public GuiStyle(String styleKey, String textureKey) {
		this.styleKey = styleKey;
		this.textureKey = textureKey;
		this.load();
	}
	
	public void load() {
		this.texture = new TextureRegion[9];
		Texture tempTexture = Assets.loadTexture(this.textureKey);
		for(int i=0; i<3; i++) {
			this.texture[i*3] = new TextureRegion(tempTexture, 0, tempTexture.getWidth()/3*i, tempTexture.getWidth()/3, tempTexture.getHeight()/3);
			this.texture[i*3+1] = new TextureRegion(tempTexture, tempTexture.getWidth()/3, tempTexture.getWidth()/3*i, tempTexture.getWidth()/3, tempTexture.getHeight()/3);
			this.texture[i*3+2] = new TextureRegion(tempTexture, tempTexture.getWidth()/3*2, tempTexture.getWidth()/3*i, tempTexture.getWidth()/3, tempTexture.getHeight()/3);
		}
	}
	
	public void render(SpriteBatch batch, int[] size, int[] position) {
		int a = this.texture[0].getRegionWidth();
		int b = this.texture[0].getRegionHeight();
		batch.draw(this.texture[0], position[0], position[1]+size[1]-b);
		batch.draw(this.texture[1], position[0]+a, position[1]+size[1]-b,size[0]-2*a,b);
		batch.draw(this.texture[2], position[0]+size[0]-a, position[1]-b+size[1]);
		batch.draw(this.texture[3], position[0], position[1]+b, a, size[1]-b*2);
		batch.draw(this.texture[4], position[0]+a, position[1]+b, size[0]-a*2, size[1]-b*2);
		batch.draw(this.texture[5], position[0]+size[0]-a, position[1]+b, a, size[1]-b*2);
		batch.draw(this.texture[6], position[0], position[1]);
		batch.draw(this.texture[7], position[0]+a, position[1],size[0]-2*a,b);
		batch.draw(this.texture[8], position[0]+size[0]-a, position[1]);
	}
	
	public String getTextureKey() {
		return this.textureKey;
	}
	
	public String getStyleKey() {
		return this.styleKey;
	}
	
	@Override
	public String toString() {
	   return "DataObject [textureKey="+this.textureKey+", styleKey="+this.styleKey+",isInternal="+isInternal+", isStretched="+isStretched+"]";
	}
}
