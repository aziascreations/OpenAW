package com.azias.advancewarsbootleg.map;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile extends Object {
	protected TerrainType terrainType;
	protected int[] position;
	protected int[] animationPosition;
	//protected int animationSubtype;
	//protected int animationHasShadow;
	
	public Tile(TerrainType terrainType, int positionX, int positionY) {
		this.terrainType = terrainType;
		this.position = new int[] {positionX, positionY};
		//this.animationHasShadow = 0;
		//this.animationSubtype = 0;
		this.animationPosition = new int[] {0,0};
	}

	public void setAnimationPosition(int par1, int par2) {
		this.animationPosition[par1] = par2;
	}

	public void render(SpriteBatch batch, int x, int y) {
		//System.out.println("Rendering sprite at: "+x+"/"+y);
		if(Assets.tileGraphicsBooleans[this.terrainType.getAnimationID()]) {
			//Animation
		} else {
			//System.out.println("It's a sprite");
			batch.draw(((TextureRegion[][])Assets.tilesGraphics[this.terrainType.getAnimationID()])[animationPosition[0]][animationPosition[1]], x, y,Assets.tileRenderSize[Assets.tileRenderSizeIndex],Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
		}
	}

	@Deprecated
	public void setAnimationSubtype(int par1) {
		//this.animationSubtype = par1;
	}
	
	@Deprecated
	public void setAnimationHasShadow(int par1) {
		//this.animationHasShadow = par1;
	}
	
	public TerrainType getTerrainType() {
		return this.terrainType;
	}
}
