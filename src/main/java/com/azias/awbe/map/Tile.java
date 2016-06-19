package com.azias.awbe.map;

import java.util.HashMap;

import com.azias.awbe.enums.EnumUnitMouvementClass;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//http://www.warscentral.com/aw2/terrain.shtml
public class Tile extends Object {
	protected EnumUnitMouvementClass[] allowedUnits = new EnumUnitMouvementClass[]{
		EnumUnitMouvementClass.INFTRY,
		EnumUnitMouvementClass.MECH
	};
	protected String unlocalizedName = "tile.default";
	
	protected int defenseRating = 0;
	protected int mouvementCost = 1;
	/** Used for forests and reefs */
	protected boolean onlyVisibleToAdjacentUnits = false;
	
	protected boolean isBuilding = false;
	protected int buildingHitPoint = 20;
	
	public Tile() {
		
	}
	
	public void onNewTurn() {
		
	}
	
	public boolean onCaptured() {
		return false;
	}

	public void render(SpriteBatch batch) {
		
	}
	
	//public static Tile[] tiles = new Tile[64];
	public static HashMap<String, Tile> tiles = new HashMap<String, Tile>();
	
	public static boolean loadTiles() {
		
		return false;
	}
}
