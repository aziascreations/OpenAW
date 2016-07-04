package com.azias.openaw.map;

public class Power {
	protected String unlocalizedName = "power.default";
	
	protected EnumPowerType powerType = EnumPowerType.SIMPLE;
	
	public enum EnumPowerType {
		SIMPLE, SUPER;
	}
	
	public Power() {
		
	}
	
	public boolean onActivated(World world) {
		return true;
	}
	
	public boolean onTurnEnd(World world) {
		return true;
	}
	
	public boolean onDayEnd(World world) {
		return true;
	}
}
