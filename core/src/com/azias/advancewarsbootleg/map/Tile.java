package com.azias.advancewarsbootleg.map;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.enums.EnumTerrainType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile extends Object {
	protected EnumTerrainType terrainType;
	//protected int[] position;
	protected int[] animationPosition;
	//protected boolean isAnimationCustom;
	
	public Tile(EnumTerrainType terrainType, int positionX, int positionY) {
		this.terrainType = terrainType;
		//this.position = new int[] {positionX, positionY};
		this.animationPosition = new int[] {0,0};
		//this.isAnimationCustom = false;
	}
	
	public void setAnimationPosition(int par1, int par2) {
		this.animationPosition[par1] = par2;
	}

	public void render(SpriteBatch batch, int x, int y) {
		//System.out.println("Rendering sprite at: "+x+"/"+y);
		if(Assets.tileGraphicsBooleans[this.terrainType.getAnimationID()]) {
			//Animation
		} else {
			//Sprite
			//System.out.println("It's a sprite");
			//System.out.println(this.position[0]+"/"+this.position[1]+" - "+this.animationPosition[0]+"/"+this.animationPosition[1]+" - "+this.terrainType.getTerrainLetter());
			batch.draw(((TextureRegion[][])Assets.tilesGraphics[this.terrainType.getAnimationID()])[animationPosition[0]][animationPosition[1]], x, y,Assets.tileRenderSize[Assets.tileRenderSizeIndex],Assets.tileRenderSize[Assets.tileRenderSizeIndex]);
		}
	}
	
	public EnumTerrainType getTerrainType() {
		return this.terrainType;
	}
	
	public void setTerrainType(EnumTerrainType par1) {
		this.terrainType = par1;
		this.animationPosition = new int[] {0,0};
	}
	
	@Override
	public String toString() {
	   return "DataObject [terrainType="+this.terrainType+",animationPosition="+this.animationPosition+"]";
	}
}
