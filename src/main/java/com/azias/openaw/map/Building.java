package com.azias.openaw.map;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Building extends Object {
	
	/* Non-static */
	public int[] position = {0,0};
	
	public Building() {}
	
	public boolean onCaptured(World world) {
		return true;
	}
	
	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}
	
	/* Static */
	private final static Logger logger = LoggerFactory.getLogger(Building.class);
	private static HashMap<String, Building> buildings = new HashMap<String, Building>();
	
	public static void registerUnit(String id, Building building) {
		buildings.put(id, building);
	}
	
	public static Building getBuilding(String id) {
		Building building = buildings.get(id);
		if(building==null) {
			logger.warn("Unable to find the building with this id: {}", id);
		}
		//TODO: Return a default building
		return building;
	}
}
