package com.azias.advancewarsbootleg.enums;

public enum EnumBuildingType {
	Town(0,"town","Town"),
	Factory(1,"factory","Factory"),
	Airport(2,"airport","Airport"),
	Antena(3,"antena","Antena"),
	Port(4,"","Port"),
	HQ(5,"","HQ");
	
	private final int id;
	private final String fileName;
	private final String name;
	
	EnumBuildingType(final int id, final String fileName, final String name/*Units that can be healed*/) {
		this.id = id;
		this.fileName = fileName;
		this.name = name;
	}
	
	public static EnumBuildingType getBuildingType(int id) {
		switch(id) {
		case 0:
			return Town;
		case 1:
			return Factory;
		case 2:
			return Airport;
		case 3:
			return Antena;
		case 4:
			return Port;
		case 5:
			return HQ;
		default:
			return Town;
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	//Is it really useful ?
	public String getFileName() {
		return this.fileName;
	}
}
