package com.azias.openaw.map;

import java.util.HashMap;

import com.azias.openaw.enums.EnumUnitClass;
import com.azias.openaw.enums.EnumUnitMouvementClass;

public class Unit extends Object {
	protected String unlocalizedName = "unit.default";

	protected int gas = 99;
	protected int consumption = 0;
	protected int movement = 3;
	protected int vision = 3;
	protected int price = 1000;
	
	protected EnumUnitClass unitClass = EnumUnitClass.INFANTRY;
	protected EnumUnitMouvementClass mouvementClass = EnumUnitMouvementClass.FOOT;
	
	public Unit() {
		
	}
	
	public boolean onBirth(World world) {
		return true;
	}
	
	public boolean onDamaged(World world) {
		return true;
	}
	
	public boolean onDeath(World world) {
		return true;
	}
	
	public boolean onNewTurn(World world) {
		return true;
	}
	
	public String getUnlocalizedName() {
		return this.unlocalizedName;
	}
	
	//Static stuff
	private static HashMap<String, Unit> units = new HashMap<String, Unit>();
	
	public static void registerUnit(String id, Unit unit) {
		units.put(id, unit);
	}
	
	public static Unit getUnit(String id) {
		//TODO: Avoid null
		return units.get(id);
	}
}
