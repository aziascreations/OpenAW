package com.azias.advancewarsbootleg.enums;

//Based on: http://strategywiki.org/wiki/Advance_Wars_2:_Black_Hole_Rising/Units#Charts

public enum EnumTerrainType {
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
	
	EnumTerrainType(final int[] moveInfantry, final int[] moveMech, final int[] moveThreads, final int[] moveWheels, final int[] moveLander, final int[] moveShip, final int[] moveAir, final int terrainAnimationID, final String letter) {
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
	
	//i am so so sorry for what you are about to witness, but I had no choice...
	public static int[] getBeachSubType(String par1) {
		//Leave this like that if you want a Java 6 support because switches are shit in this version.
		//I switched to Java 7, It came out in 2011... I won't use an older version from 2007.
		switch(par1) {
		case "SEBS":
			return new int[] {0,0};
		case "BESS":
			return new int[] {0,1};
		case "SSBE":
			return new int[] {1,0};
		case "BSSE":
			return new int[] {1,1};
		case "SBES":
			return new int[] {2,0};
		case "EBSS":
			return new int[] {2,1};
		case "SSEB":
			return new int[] {3,0};
		case "ESSB":
			return new int[] {3,1};
		case "SESS":
			return new int[] {4,0};
		case "SSES":
			return new int[] {4,1};
		case "SSSE":
			return new int[] {5,0};
		case "ESSS":
			return new int[] {5,1};
		case "SBEB":
			return new int[] {6,0};
		case "EBSB":
			return new int[] {6,1};
		case "BSBE":
			return new int[] {7,0};
		case "BEBS":
			return new int[] {7,1};
		case "BEEB":
			return new int[] {8,0};
		case "EEBB":
			return new int[] {8,1};
		case "BBEE":
			return new int[] {9,0};
		case "EBBE":
			return new int[] {9,1};
		case "SEES":
			return new int[] {10,0};
		case "EESS":
			return new int[] {10,1};
		case "SSEE":
			return new int[] {11,0};
		case "ESSE":
			return new int[] {11,1};
		case "EEES":
			return new int[] {12,0};
		case "ESEE":
			return new int[] {12,1};
		case "SEEE":
			return new int[] {13,0};
		case "EESE":
			return new int[] {13,1};
		case "SBEE":
			return new int[] {14,0};
		case "EBSE":
			return new int[] {14,1};
		case "SEEB":
			return new int[] {15,0};
		case "EESB":
			return new int[] {15,1};
		case "EEBS":
			return new int[] {16,0};
		case "BEES":
			return new int[] {16,1};
		case "ESBE":
			return new int[] {17,0};
		case "BSEE":
			return new int[] {17,1};
		default:
			return new int[] {0,0};
		}
	}

	public static int[] getRoadSubType(String par1) {
		switch(par1) {
		case "RERE":
		case "REEE":
		case "EERE":
			return new int[] {5,0};
		case "ERER":
		case "EEER":
		case "EREE":
			return new int[] {4,0};
		case "ERRE":
			return new int[] {0,0};
		case "RREE":
			return new int[] {1,0};
		case "REER":
			return new int[] {2,0};
		case "EERR":
			return new int[] {3,0};
		case "RRRE":
			return new int[] {6,0};
		case "ERRR":
			return new int[] {7,0};
		case "RRER":
			return new int[] {8,0};
		case "RERR":
			return new int[] {9,0};
		case "RRRR":
			return new int[] {10,0};
		default:
			return new int[] {4,0};
		}
	}

	public static int[] getMountainSubType(String par1) {
		switch(par1) {
		case "MM":
			return new int[] {2,0};
		case "EM":
			return new int[] {1,0};
		case "ME":
			return new int[] {3,0};
		default:
			return new int[] {0,0};
		}
	}
	
	public static int[] getSeaSubType(String par1, String par2) {
		switch(par1) {
		case "SSSS":
			if(par2.equals("SSSS")) {
				return new int[] {7,2};
			} else if(par2.equals("EEEE")) {
				return new int[] {14,2};
			} else if(par2.equals("SSSE")) {
				return new int[] {13,1};
			} else if(par2.equals("SSES")) {
				return new int[] {12,1};
			} else if(par2.equals("SESS")) {
				return new int[] {13,0};
			} else if(par2.equals("ESSS")) {
				return new int[] {12,0};
			} else if(par2.equals("SEES")) {
				return new int[] {8,2};
			} else if(par2.equals("ESSE")) {
				return new int[] {11,2};
			} else if(par2.equals("EESS")) {
				return new int[] {14,0};
			} else if(par2.equals("SESE")) {
				return new int[] {15,0};
			} else if(par2.equals("SSEE")) {
				return new int[] {15,1};
			} else if(par2.equals("ESES")) {
				return new int[] {14,1};
			} else if(par2.equals("SEEE")) {
				return new int[] {8,2};
			} else if(par2.equals("ESEE")) {
				return new int[] {9,2};
			} else if(par2.equals("EEES")) {
				return new int[] {12,2};
			} else if(par2.equals("EESE")) {
				return new int[] {13,2};
			} else {
				return new int[] {15,2};
			}
		case "EEEE":
			return new int[] {5,1};
		case "ESEE":
			return new int[] {10,0};
		case "EESE":
			return new int[] {11,0};
		case "EEES":
			return new int[] {11,1};
		case "SEEE":
			return new int[] {10,1};
		case "SESE":
			return new int[] {9,0};
		case "ESES":
			return new int[] {9,1};
		case "ESSS":
			if(par2.charAt(2)=='S' && par2.charAt(3)=='S') {
				return new int[] {5,0};
			} else if(par2.charAt(2)=='E' && par2.charAt(3)=='S') {
				return new int[] {3,0};
			} else if(par2.charAt(2)=='S' && par2.charAt(3)=='E') {
				return new int[] {3,1};
			} else if(par2.charAt(2)=='E' && par2.charAt(3)=='E') {
				return new int[] {3,2};
			} else {
				return new int[] {15,2};
			}
		case "SESS":
			if(par2.charAt(0)=='S' && par2.charAt(2)=='S') {
				return new int[] {6,1};
			} else if(par2.charAt(0)=='E' && par2.charAt(2)=='S') {
				return new int[] {2,0};
			} else if(par2.charAt(0)=='S' && par2.charAt(2)=='E') {
				return new int[] {2,1};
			} else if(par2.charAt(0)=='E' && par2.charAt(2)=='E') {
				return new int[] {2,2};
			} else {
				return new int[] {15,2};
			}
		case "SSES":
			if(par2.charAt(0)=='S' && par2.charAt(1)=='S') {
				return new int[] {5,2};
			} else if(par2.charAt(0)=='E' && par2.charAt(1)=='S') {
				return new int[] {1,0};
			} else if(par2.charAt(0)=='S' && par2.charAt(1)=='E') {
				return new int[] {1,1};
			} else if(par2.charAt(0)=='E' && par2.charAt(1)=='E') {
				return new int[] {1,2};
			} else {
				return new int[] {15,2};
			}
		case "SSSE":
			if(par2.charAt(1)=='S' && par2.charAt(3)=='S') {
				return new int[] {4,1};
			} else if(par2.charAt(1)=='E' && par2.charAt(3)=='S') {
				return new int[] {0,0};
			} else if(par2.charAt(1)=='S' && par2.charAt(3)=='E') {
				return new int[] {0,1};
			} else if(par2.charAt(1)=='E' && par2.charAt(3)=='E') {
				return new int[] {0,2};
			} else {
				return new int[] {15,2};
			}

		case "ESSE":
			if(par2.charAt(3)=='E') {
				return new int[] {7,0};
			} else {
				return new int[] {4,0};
			}
		case "EESS":
			if(par2.charAt(2)=='E') {
				return new int[] {8,0};
			} else {
				return new int[] {6,0};
			}
		case "SEES":
			if(par2.charAt(0)=='E') {
				return new int[] {8,1};
			} else {
				return new int[] {6,2};
			}
		case "SSEE":
			if(par2.charAt(1)=='E') {
				return new int[] {7,1};
			} else {
				return new int[] {4,2};
			}
		default:
			return new int[] {15,2};
		}
	}
}
