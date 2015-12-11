package com.azias.advancewarsbootleg.enums;

public enum EnumCommandingOfficer {
	Andy("Andy", "andy", EnumFaction.OrangeStar),
	Max("Max", "max", EnumFaction.OrangeStar),
	Sami("Sami", "sami", EnumFaction.OrangeStar),
	Flak("Flak", "flak", EnumFaction.BlackHole);
	
	private final String name;
	private final String assetsName;
	private final EnumFaction faction;
    
	EnumCommandingOfficer(final String name, final String assetsName, final EnumFaction faction) {
        this.name = name;
        this.assetsName = assetsName;
        this.faction = faction;
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
}
