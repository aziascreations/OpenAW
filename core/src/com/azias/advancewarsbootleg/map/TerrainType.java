package com.azias.advancewarsbootleg.map;

//Based on: http://strategywiki.org/wiki/Advance_Wars_2:_Black_Hole_Rising/Units#Charts

public enum TerrainType {
	Plain(
		new int[]{1,1,2},//Infantry
		new int[]{1,1,1},//Mech
		new int[]{1,2,2},//Threads
		new int[]{2,3,3},//Wheels
		null,//Lander
		null,//Ship
		new int[]{1,1,2},
		0),//Air
	Forest(
		new int[]{1,1,2},
		new int[]{1,1,1},
		new int[]{2,3,3},
		new int[]{3,4,4},
		null,
		null,
		new int[]{1,1,2},
		1),
	Road(
		new int[]{1,1,1},
		new int[]{1,1,1},
		new int[]{1,1,1},
		new int[]{1,1,1},
		null,
		null,
		new int[]{1,1,2},
		2),
	Mountain(
		new int[]{2,2,4},
		new int[]{1,1,2},
		null,
		null,
		null,
		null,
		new int[]{1,1,2},
		3),
	//The Terrains below aren't correct
	River(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},4),
	Shoal(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},5),
	Sea(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},6),
	Reef(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},7),
	Property(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},8),
	Port(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},9);

	//The Arrays are based on the weather(clear, rain, snow)
	private final int[] moveInfantry;
	private final int[] moveMech;
    private final int[] moveThreads;
    private final int[] moveWheels;
    private final int[] moveLander;
    private final int[] moveShip;
    private final int[] moveAir;
    private final int terrainAnimationID;
    
    TerrainType(final int[] moveInfantry, final int[] moveMech, final int[] moveThreads, final int[] moveWheels, final int[] moveLander, final int[] moveShip, final int[] moveAir, final int terrainAnimationID) {
        this.moveInfantry = moveInfantry;
        this.moveMech = moveMech;
        this.moveThreads = moveThreads;
        this.moveWheels = moveWheels;
        this.moveLander = moveLander;
        this.moveShip = moveShip;
        this.moveAir = moveAir;
        this.terrainAnimationID = terrainAnimationID;
    }
    
    public int[] getMovementStats(int unitType) {
    	//TODO: Use UnitType Enum
    	//How can you get the weather in this ? - Use the MapController
    	switch(unitType) {
    	case 0:
    		return this.moveInfantry;
    	case 1:
    		return this.moveMech;
    	case 2:
    		return this.moveThreads;
    	case 3:
    		return this.moveWheels;
    	case 4:
    		return this.moveLander;
    	case 5:
    		return this.moveShip;
    	default:
    		return this.moveAir;
    	}
    }
    
    public int getAnimationID() {
    	return this.terrainAnimationID;
    }
}
