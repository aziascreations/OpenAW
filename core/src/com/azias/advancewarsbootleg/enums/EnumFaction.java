package com.azias.advancewarsbootleg.enums;

public enum EnumFaction {
	OrangeStar(0,"Orange Star"),
	BlueMoon(1,"Blue Moon"),
	YellowComet(2,"Yellow Comet"),
	GreenEarth(3,"Green Earth"),
	BlackHole(4,"Black Hole"),
	None(5,"None");
	
	private final int id;
	private final String name;
	
	EnumFaction(final int id, final String name) {
		this.id = id;
		this.name = name;
	}
	
	public static EnumFaction getTeam(int id) {
		switch(id) {
		case 0:
			return OrangeStar;
		case 1:
			return BlueMoon;
		case 2:
			return YellowComet;
		case 3:
			return GreenEarth;
		case 4:
			return BlackHole;
		case 5:
			return None;
		default:
			return null;
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

}
