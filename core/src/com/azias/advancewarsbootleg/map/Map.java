package com.azias.advancewarsbootleg.map;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
//import java.util.Scanner;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;
import com.azias.advancewarsbootleg.enums.EnumTerrainType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map extends Object {
	protected int[] mapSize = new int[2];
	protected Tile[][] mapTiles;
	protected ArrayList<Building> buildings;
	
	public Map() {
		
	}
	
	public boolean loadBasicMap(String path, String mapName) {
		Gdx.app.log(Utils.getFormatedTime(), "Loading "+path+"/"+mapName+".awm ...");
		try {
			String mapFile = Utils.readFile("datas/maps/"+path+"/"+mapName+".awm", StandardCharsets.UTF_8);
			mapFile = mapFile.replace("\n", "").replace("\r", "");
			String mapContent[] = mapFile.split("#_#");
			
			String mapTiles[] = mapContent[2].split(";");
			this.mapSize[0] = mapTiles[0].length();
			this.mapSize[1] = mapTiles.length;
			
			this.mapTiles = new Tile[this.mapSize[0]][this.mapSize[1]];
			for(int y = 0; y < this.mapSize[1]; y++) {
				for(int x = 0; x < this.mapSize[0]; x++) {
					this.mapTiles[x][y]=new Tile(this.getTerrainType(mapTiles[y].charAt(x)), x, y);
				}
			}
			
			buildings = new ArrayList<Building>();
			if(!mapContent[4].equals("null")) {
				String[] buildingsInfos = mapContent[4].split(";");
				for(int i = 0; i<buildingsInfos.length; i++) {
					if(!(buildingsInfos[i].indexOf('#')==0)) {
						//buildingType, x, y, teamId
						String[] a = buildingsInfos[i].split("§");
						this.buildings.add(new BuildingGeneric(
									Integer.parseInt(a[0]),
									Integer.parseInt(a[1]),
									Integer.parseInt(a[2]),
									Integer.parseInt(a[3])));
					}
				}
			}
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean loadMap(String path, String mapName) {
		Gdx.app.log(Utils.getFormatedTime(), "Loading "+path+"/"+mapName+".awm ...");
		try {
			//String mapFile = new Scanner(new File("datas/maps/"+path+"/"+mapName+".awm")).useDelimiter("\\Z").next();
			//I changed switched to this method so I do not longer have any memory leak problems.
			String mapFile = Utils.readFile("datas/maps/"+path+"/"+mapName+".awm", StandardCharsets.UTF_8);
			mapFile = mapFile.replace("\n", "").replace("\r", "");
			String mapContent[] = mapFile.split("#_#");
			
			String mapTiles[] = mapContent[2].split(";");
			this.mapSize[0] = mapTiles[0].length();
			this.mapSize[1] = mapTiles.length;
			
			this.mapTiles = new Tile[this.mapSize[0]][this.mapSize[1]];
			for(int y = 0; y < this.mapSize[1]; y++) {
				for(int x = 0; x < this.mapSize[0]; x++) {
					this.mapTiles[x][y]=new Tile(this.getTerrainType(mapTiles[y].charAt(x)), x, y);
				}
			}
			
			if(mapContent[3].equals("null")) {
				for(int y = 0; y < this.mapSize[1]; y++) {
					for(int x = 0; x < this.mapSize[0]; x++) {
						this.setTileSubType(x,y);
					}
				}
			} else {
				
			}
			
			buildings = new ArrayList<Building>();
			if(!mapContent[4].equals("null")) {
				String[] buildingsInfos = mapContent[4].split(";");
				for(int i = 0; i<buildingsInfos.length; i++) {
					if(!(buildingsInfos[i].indexOf('#')==0)) {
						//buildingType, x, y, teamId
						String[] a = buildingsInfos[i].split("§");
						this.buildings.add(new BuildingGeneric(
									Integer.parseInt(a[0]),
									Integer.parseInt(a[1]),
									Integer.parseInt(a[2]),
									Integer.parseInt(a[3])));
					}
				}
			}
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected boolean setTileSubType(int x, int y) {
		this.mapTiles[x][y].animationPosition = new int[] {0,0};
		//Sea
		if(this.mapTiles[x][y].getTerrainType()==EnumTerrainType.Sea) {
			String surroundingClose = "";
			String surroundingDistant = "";
			//Up
			if(y-1>=0) {
				if(this.mapTiles[x][y-1].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x][y-1].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x][y-1].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
				//Distant
				if(x-1>=0) {
					if(this.mapTiles[x-1][y-1].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x-1][y-1].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x-1][y-1].getTerrainType()==EnumTerrainType.Reef) {
						surroundingDistant+="S";
					} else {
						surroundingDistant+="E";
					}
				} else {
					surroundingDistant+="S";
				}
				if(x+1<Datas.coMap.getMapSize()[0]) {
					if(this.mapTiles[x+1][y-1].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x+1][y-1].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x+1][y-1].getTerrainType()==EnumTerrainType.Reef) {
						surroundingDistant+="S";
					} else {
						surroundingDistant+="E";
					}
				} else {
					surroundingDistant+="S";
				}
			} else {
				surroundingClose+="S";
				surroundingDistant+="SS";
			}
			//Right
			if(x+1<Datas.coMap.getMapSize()[0]) {
				if(this.mapTiles[x+1][y].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x+1][y].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x+1][y].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
			} else {
				surroundingClose+="S";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.mapTiles[x][y+1].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x][y+1].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x][y+1].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
				//Distant
				if(x-1>=0) {
					if(this.mapTiles[x-1][y+1].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x-1][y+1].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x-1][y+1].getTerrainType()==EnumTerrainType.Reef) {
						surroundingDistant+="S";
					} else {
						surroundingDistant+="E";
					}
				} else {
					surroundingDistant+="S";
				}
				if(x+1<Datas.coMap.getMapSize()[0]) {
					if(this.mapTiles[x+1][y+1].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x+1][y+1].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x+1][y+1].getTerrainType()==EnumTerrainType.Reef) {
						surroundingDistant+="S";
					} else {
						surroundingDistant+="E";
					}
				} else {
					surroundingDistant+="S";
				}
			} else {
				surroundingClose+="S";
				surroundingDistant+="SS";
			}
			//Left
			if(x-1>=0) {
				if(this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Sea||this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Shoal||this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
			} else {
				surroundingClose+="S";
			}
			this.mapTiles[x][y].animationPosition=EnumTerrainType.getSeaSubType(surroundingClose, surroundingDistant);
			return true;
		}
		
		//Plains
		if(this.mapTiles[x][y].getTerrainType()==EnumTerrainType.Plain) {
			if(x-1 >= 0) {
				if(this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Mountain || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Forest || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Property || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Port) {
					this.mapTiles[x][y].setAnimationPosition(0, 1);
				}
			}
			return true;
		}
		
		//Mountain
		if(this.mapTiles[x][y].getTerrainType()==EnumTerrainType.Mountain) {
			String surrounding = "";
			
			if(y-1>=0) {
				if(this.mapTiles[x][y-1].getTerrainType()==EnumTerrainType.Mountain) {
					surrounding+="M";
				} else {
					surrounding+="E";
				}
				if(y+1 < Datas.coMap.getMapSize()[1]) {
					if(this.mapTiles[x][y+1].getTerrainType()==EnumTerrainType.Mountain) {
						surrounding+="M";
					} else {
						surrounding+="E";
					}
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
				if(y+1 < Datas.coMap.getMapSize()[1]) {
					if(this.mapTiles[x][y+1].getTerrainType()==EnumTerrainType.Mountain) {
						surrounding+="M";
					} else {
						surrounding+="E";
					}
				} else {
					surrounding+="E";
				}
			}
			this.mapTiles[x][y].animationPosition=EnumTerrainType.getMountainSubType(surrounding);
			return true;
		}
		
		//Forest
		if(this.mapTiles[x][y].getTerrainType()==EnumTerrainType.Forest) {
			if(x-1 >= 0) {
				if(this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Mountain || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Forest || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Property || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Port) {
					this.mapTiles[x][y].setAnimationPosition(0, 1);
				}
			}
			return true;
		}
		
		//Road
		if(this.mapTiles[x][y].getTerrainType()==EnumTerrainType.Road) {
			String surrounding = "";
			//Up
			if(y-1>=0) {
				if(this.mapTiles[x][y-1].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Right
			if(x+1<Datas.coMap.getMapSize()[0]) {
				if(this.mapTiles[x+1][y].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.mapTiles[x][y+1].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Left
			if(x-1>=0) {
				if(this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			this.mapTiles[x][y].animationPosition=EnumTerrainType.getRoadSubType(surrounding);
			if(x-1 >= 0) {
				if(this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Mountain || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Forest || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Property || this.mapTiles[x-1][y].getTerrainType()==EnumTerrainType.Port) {
					this.mapTiles[x][y].animationPosition[1] = 1;
				}
			}
			return true;
		}
		
		//Beach - Shoal
		if(this.mapTiles[x][y].getTerrainType()==EnumTerrainType.Shoal) {
			String surrounding = "";
			//Up
			if(y-1>=0) {
				if(this.mapTiles[x][y-1].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x][y-1].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Right
			if(x+1<Datas.coMap.getMapSize()[0]) {
				if(this.mapTiles[x+1][y].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x+1][y].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.mapTiles[x][y+1].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x][y+1].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Left
			if(x-1>=0) {
				if(this.mapTiles[x-1][y].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x-1][y].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			this.mapTiles[x][y].animationPosition=EnumTerrainType.getBeachSubType(surrounding);
			return true;
		}
		
		return false;
	}

	public void render(SpriteBatch batch) {
		int startingPointY = Gdx.graphics.getHeight()+Assets.renderOffset[1]-Assets.tileRenderSize[Assets.tileRenderSizeIndex]; //Used for Y axis
		for(int y=0; y<this.mapSize[1]; y++) {
			for(int x=0; x<this.mapSize[0] ;x++) {
				this.mapTiles[x][y].render(batch, 0+(x*Assets.tileRenderSize[Assets.tileRenderSizeIndex]), startingPointY-(y*Assets.tileRenderSize[Assets.tileRenderSizeIndex]));
			}
		}
		for(int i=0; i<this.buildings.size(); i++) {
			this.buildings.get(i).render(batch, 0+(buildings.get(i).position[0]*Assets.tileRenderSize[Assets.tileRenderSizeIndex]), startingPointY-(buildings.get(i).position[1]*Assets.tileRenderSize[Assets.tileRenderSizeIndex]));
		}
	}

	private EnumTerrainType getTerrainType(char terrainLetter) {
		switch(terrainLetter) {
		case '_':
			return EnumTerrainType.Plain;
		case 'S':
			return EnumTerrainType.Sea;
		case 'F':
			return EnumTerrainType.Forest;
		case 'M':
			return EnumTerrainType.Mountain;
		case 'B':
			return EnumTerrainType.Shoal;
		case 'R':
			return EnumTerrainType.Road;
		case 'b':
			//TODO Add a bridge terrain
			return EnumTerrainType.Plain;
		case 'r':
			return EnumTerrainType.River;
		case 'C':
			return EnumTerrainType.Property;
		case 'c':
			return EnumTerrainType.Port;
		case 'P':
			//TODO Add a pipe terrain
			return EnumTerrainType.Plain;
		default:
			return EnumTerrainType.Plain;
		}
	}

	/**
	 * @param String path - The output folder's name
	 * @param String mapName - The map file's name
	 * @param String name - The map's name
	 * @param String author - The author's name
	 * @return boolean Unused - Might be used to cast an error and end the function
	 */
	//Buildings aren't exported for the moment.
	public boolean exportMap(String path, String mapName, String name, String author) {
		//Setting up everything
		String mapLetters = "";
		for(int y = 0; y < this.mapSize[1]; y++) {
			for(int x = 0; x < this.mapSize[0]; x++) {
				mapLetters += this.mapTiles[x][y].getTerrainType().getTerrainLetter();
			}
			mapLetters += ";";
		}
		//System.out.println(mapLetters);
		
		//Saving file
		String mapContent = name+"#_#"+author+"#_#"+mapLetters+"#_#";
		try {
			File f = new File("datas/maps/"+path+"/"+mapName+".awm");
			if(f.exists() && !f.isDirectory()) {
				//TODO: ask to replace the file
				Gdx.app.error(Utils.getFormatedTime(), "Error: A file named "+mapName+".awm already exist!");
				return false;
			} else {
				PrintWriter out = new PrintWriter("datas/maps/"+path+"/"+mapName+".awm");
				out.println(mapContent);
				out.close();
				Gdx.app.log(Utils.getFormatedTime(), "Exported map as "+mapName+".awm");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeBuiding(int x, int y) {
		for(int i = 0; i<this.buildings.size(); i++) {
			if(this.buildings.get(i).position[0] == x && this.buildings.get(i).position[1] == y) {
				this.buildings.remove(i);
				return true;
			}
		}
		return false;
	}

	public void setBuilding(int x, int y, Building par4) {
		this.buildings.add(new Building(par4.buildingType.getId(), x, y, par4.faction.getId()));
	}
}
