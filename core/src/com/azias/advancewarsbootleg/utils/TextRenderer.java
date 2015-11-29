package com.azias.advancewarsbootleg.utils;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

@Deprecated
public class TextRenderer extends Object {
	//private int[][] lettersTexturePsition;
	private TextureRegion[][] lettersTextures;
	
	@SuppressWarnings("unused")
	private float fontSize = 2.0F;
	
	public TextRenderer() {
		this.lettersTextures = new TextureRegion[26][4];
		Texture lettersTileset = Assets.loadTexture("gfx/fonts/fontPaletteDev.png");
		
		for(int i = 0; i<26; i++) {
			this.lettersTextures[i][0] = new TextureRegion(lettersTileset, i*7+i, 0, 7, 10);
			this.lettersTextures[i][1] = new TextureRegion(lettersTileset, i*7+i, 11, 7, 10);
		}
		for(int i = 0; i<10; i++) {
			this.lettersTextures[i][2] = new TextureRegion(lettersTileset, i*7+i, 22, 7, 10);
		}
		for(int i = 0; i<8; i++) {
			this.lettersTextures[i][3] = new TextureRegion(lettersTileset, i*7+i, 33, 7, 10);
		}
		//String[] fontLetters = new String[] {"ABCDEFGHIJKLMNOPQRSTUVWXYZ","abcdefghijklmnopqrstuvwxyz","0123456789","!?:/&-,."};
		lettersTileset.dispose();
	}
	
	public void changeFontSize(float par1) {
		this.fontSize = par1;
	}
}
