package com.azias.advancewarsbootleg.enums;

public enum EnumCommandingOfficer {
	Andy(0, "Andy", "andy", EnumFaction.OrangeStar),
	Max(1, "Max", "max", EnumFaction.OrangeStar),
	Sami(2, "Sami", "sami", EnumFaction.OrangeStar),
	Flak(3, "Flak", "flak", EnumFaction.BlackHole);
	
	private final int id;
	private final String name;
	private final String assetsName;
	private final EnumFaction faction;
	
	EnumCommandingOfficer(final int id, final String name, final String assetsName, final EnumFaction faction) {
		this.id = id;
		this.name = name;
		this.assetsName = assetsName;
		this.faction = faction;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAssetsName() {
		return this.assetsName;
	}
	
	public EnumFaction getFaction() {
		return this.faction;
	}
	
	public static EnumCommandingOfficer getCOByAssetsName(String name) {
		EnumCommandingOfficer[] cos = values();
		for(int i=0; i<cos.length; i++) {
			if(cos[i].getAssetsName().equals(name)) {
				return cos[i];
			}
		}
		return null;
	}
}
