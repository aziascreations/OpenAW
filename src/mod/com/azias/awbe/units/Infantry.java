package com.azias.awbe.units;

import com.azias.awbe.enums.EnumUnitClass;
import com.azias.awbe.enums.EnumUnitMouvementClass;
import com.azias.awbe.map.Unit;

public class Infantry extends Unit {
	public String unlocalizedName = "unit.infantry";
	
	protected int gas = 99;
	protected int movement = 3;
	protected int vision = 2;
	protected int cost = 1000;
	
	protected EnumUnitClass unitClass = EnumUnitClass.INFANTRY;
	protected EnumUnitMouvementClass mouvementClass = EnumUnitMouvementClass.INFTRY;
}
