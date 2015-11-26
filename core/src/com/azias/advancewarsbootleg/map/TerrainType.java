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
		0,
		"_"),//Air
	Forest(
		new int[]{1,1,2},
		new int[]{1,1,1},
		new int[]{2,3,3},
		new int[]{3,4,4},
		null,
		null,
		new int[]{1,1,2},
		1,
		"F"),
	Road(
		new int[]{1,1,1},
		new int[]{1,1,1},
		new int[]{1,1,1},
		new int[]{1,1,1},
		null,
		null,
		new int[]{1,1,2},
		2,
		"R"),
	Mountain(
		new int[]{2,2,4},
		new int[]{1,1,2},
		null,
		null,
		null,
		null,
		new int[]{1,1,2},
		3,
		"M"),
	//The Terrains below aren't correct (stats)
	River(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},4,"r"),
	Shoal(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},5,"B"),
	Sea(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},6,"S"),
	Reef(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},7,"?"),
	Property(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},8,"C"),
	Port(new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},new int[]{1,1,1},9,"c");

	//The Arrays are based on the weather(clear, rain, snow)
	private final int[] moveInfantry;
	private final int[] moveMech;
    private final int[] moveThreads;
    private final int[] moveWheels;
    private final int[] moveLander;
    private final int[] moveShip;
    private final int[] moveAir;
    private final int terrainAnimationID;
    private final String letter;
    
    TerrainType(final int[] moveInfantry, final int[] moveMech, final int[] moveThreads, final int[] moveWheels, final int[] moveLander, final int[] moveShip, final int[] moveAir, final int terrainAnimationID, final String letter) {
        this.moveInfantry = moveInfantry;
        this.moveMech = moveMech;
        this.moveThreads = moveThreads;
        this.moveWheels = moveWheels;
        this.moveLander = moveLander;
        this.moveShip = moveShip;
        this.moveAir = moveAir;
        this.terrainAnimationID = terrainAnimationID;
        this.letter = letter;
    }
    
    public int[] getMovementStats(int unitType) {
    	//TODO: Use UnitType Enum
    	//How can you get the weather in this ? - Use the MapController
    	//Use another parameter ? - like: int foo = TT.getMovementStats(???, Map.getWeatherID) ?
    	//Nope. Use the WeatherID in the Unit class/function after getting the int[] from here.
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
    
    public String getTerrainLetter() {
    	return this.letter;
    }

	protected static int[] getBeachSubType(String par1) {
		//23-11-15 Leave this like that if you want a 1.6 support as switches are shit in 1.6.
		if(par1.equals("SEBS")) {
			return new int[] {0,0};
		} else if(par1.equals("BESS")) {
			return new int[] {0,1};
		} else if(par1.equals("SSBE")) {
			return new int[] {1,0};
		} else if(par1.equals("BSSE")) {
			return new int[] {1,1};
		} else if(par1.equals("SBES")) {
			return new int[] {2,0};
		} else if(par1.equals("EBSS")) {
			return new int[] {2,1};
		} else if(par1.equals("SSEB")) {
			return new int[] {3,0};
		} else if(par1.equals("ESSB")) {
			return new int[] {3,1};
		} else if(par1.equals("SESS")) {
			return new int[] {4,0};
		} else if(par1.equals("SSES")) {
			return new int[] {4,1};
		} else if(par1.equals("SSSE")) {
			return new int[] {5,0};
		} else if(par1.equals("ESSS")) {
			return new int[] {5,1};
		} else if(par1.equals("SBEB")) {
			return new int[] {6,0};
		} else if(par1.equals("EBSB")) {
			return new int[] {6,1};
		} else if(par1.equals("BSBE")) {
			return new int[] {7,0};
		} else if(par1.equals("BEBS")) {
			return new int[] {7,1};
		} else if(par1.equals("BEEB")) {
			return new int[] {8,0};
		} else if(par1.equals("EEBB")) {
			return new int[] {8,1};
		} else if(par1.equals("BBEE")) {
			return new int[] {9,0};
		} else if(par1.equals("EBBE")) {
			return new int[] {9,1};
		} else if(par1.equals("SEES")) {
			return new int[] {10,0};
		} else if(par1.equals("EESS")) {
			return new int[] {10,1};
		} else if(par1.equals("SSEE")) {
			return new int[] {11,0};
		} else if(par1.equals("ESSE")) {
			return new int[] {11,1};
		} else if(par1.equals("EEES")) {
			return new int[] {12,0};
		} else if(par1.equals("ESEE")) {
			return new int[] {12,1};
		} else if(par1.equals("SEEE")) {
			return new int[] {13,0};
		} else if(par1.equals("EESE")) {
			return new int[] {13,1};
		} else if(par1.equals("SBEE")) {
			return new int[] {14,0};
		} else if(par1.equals("EBSE")) {
			return new int[] {14,1};
		} else if(par1.equals("SEEB")) {
			return new int[] {15,0};
		} else if(par1.equals("EESB")) {
			return new int[] {15,1};
		} else if(par1.equals("EEBS")) {
			return new int[] {16,0};
		} else if(par1.equals("BEES")) {
			return new int[] {16,1};
		} else if(par1.equals("ESBE")) {
			return new int[] {17,0};
		} else if(par1.equals("BSEE")) {
			return new int[] {17,1};
		} else {
			return new int[] {0,0};
		}
	}
}
