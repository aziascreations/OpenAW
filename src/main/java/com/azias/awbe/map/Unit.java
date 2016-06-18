package com.azias.awbe.map;

import com.azias.awbe.enums.EnumUnitClass;
import com.azias.awbe.enums.EnumUnitMouvementClass;

public class Unit extends Object {
	protected String unlocalizedName = "unit.default";
	
	protected int gas = 99;
	protected int movement = 3;
	protected int vision = 3;
	protected int cost = 1000;
	
	protected EnumUnitClass unitClass = EnumUnitClass.INFANTRY;
	protected EnumUnitMouvementClass mouvementClass = EnumUnitMouvementClass.INFTRY;
	
	public Unit() {
		
	}
}
