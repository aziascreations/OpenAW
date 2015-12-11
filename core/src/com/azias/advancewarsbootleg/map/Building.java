package com.azias.advancewarsbootleg.map;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.enums.EnumBuildingType;
import com.azias.advancewarsbootleg.enums.EnumFaction;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Building extends Object {
	protected int[] position;
	protected EnumBuildingType buildingType;
	protected EnumFaction faction;
	protected int healthPoints;
	
	protected Building(int buildingId, int posX, int posY, int teamId) {
		this.buildingType = EnumBuildingType.getBuildingType(buildingId);
		this.position = new int[]{posX, posY};
		this.faction = EnumFaction.getTeam(teamId);
		this.healthPoints = 20;
	}
	
	protected Building(EnumBuildingType buildingType, int posX, int posY, int teamId) {
		this.buildingType = buildingType;
		this.position = new int[]{posX, posY};
		this.faction = EnumFaction.getTeam(teamId);
		this.healthPoints = 20;
	}

	//I had to create extended objects to handle the HQ and the rocket.
	public void render(SpriteBatch batch, int x, int y) {
		if(Assets.tileGraphicsBooleans[this.buildingType.getId()]) {
			//Animation
		} else {
			batch.draw(
					((TextureRegion[])Assets.buildingsGraphics[this.buildingType.getId()])[this.faction.getId()],
					x,
					y,
					Assets.tileRenderSize[Assets.tileRenderSizeIndex],
					Assets.tileRenderSize[Assets.tileRenderSizeIndex]*2);
		}
	}
	
	public boolean changeFaction(int id) {
		EnumFaction newFaction = EnumFaction.getTeam(id);
		if(newFaction != null) {
			this.faction = newFaction;
			return true;
		} else {
			return false;
		}
	}
	
	public void changeFaction(EnumFaction newFaction) {
		this.faction = newFaction;
	}
	
	public EnumFaction getFaction() {
		return this.faction;
	}
	
	public void setHeathPoints(int hp) {
		this.healthPoints = hp;
	}
	
	public int getHeathPoints() {
		return this.healthPoints;
	}
	
	//This is temporary, it will probably just be used for the HQ.
	protected void onCaptured() {
		
	}
}
