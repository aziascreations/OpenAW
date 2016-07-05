package com.azias.awbe.units;

import com.azias.openaw.enums.EnumUnitClass;
import com.azias.openaw.enums.EnumUnitMouvementClass;
import com.azias.openaw.map.Unit;

public class UnitInfantry extends Unit {
	public UnitInfantry () {
		this.unlocalizedName = "unit.infantry";
		this.gas = 99;
		this.movement = 3;
		this.vision = 2;
		this.price = 1000;
		this.consumption = 0;
	}
	
	
	/*private String unlocalizedName = "unit.infantry";
	
	private int gas = 99;
	private int movement = 3;
	private int vision = 2;
	private int price = 1000;
	
	private EnumUnitClass unitClass = EnumUnitClass.INFANTRY;
	private EnumUnitMouvementClass mouvementClass = EnumUnitMouvementClass.FOOT;*/
}
