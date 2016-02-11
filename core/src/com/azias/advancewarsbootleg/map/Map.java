package com.azias.advancewarsbootleg.map;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;
import com.azias.advancewarsbootleg.enums.EnumTerrainType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Map extends Object {
	protected int formatVersion = 1;
	protected String name, author;
	protected int[] mapSize = new int[2];
	protected Tile[][] tiles;
	protected ArrayList<Building> buildings;
	
	public Map() {
		
	}
	
	@Deprecated
	public boolean loadMap(String path, String mapName) {
		//Will be deleted when the new map format and editor will be "finished" and fully implemented.
		if(new File("./datas/maps/"+path+"/"+mapName+".awm").exists()) {
			Gdx.app.log(Utils.getFormatedTime(), "Loading "+path+"/"+mapName+".awm...");
			try {
				Gdx.app.log(Utils.getFormatedTime(), "Loading "+path+"/"+mapName+".awm...");
				String mapFile = Utils.fileToString("./datas/maps/"+path+"/"+mapName+".awm", StandardCharsets.UTF_8);
				mapFile = mapFile.replace("\n", "").replace("\r", "");
				String mapContent[] = mapFile.split("#_#");
				
				String tiles[] = mapContent[2].split(";");
				this.mapSize[0] = tiles[0].length();
				this.mapSize[1] = tiles.length;
				
				this.tiles = new Tile[this.mapSize[0]][this.mapSize[1]];
				for(int y = 0; y < this.mapSize[1]; y++) {
					for(int x = 0; x < this.mapSize[0]; x++) {
						this.tiles[x][y]=new Tile(this.getTerrainType(tiles[y].charAt(x)), x, y);
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
		return false;
	}
	
	protected boolean setTileSubType(int x, int y) {
		this.tiles[x][y].animationPosition = new int[] {0,0};
		//Sea
		if(this.tiles[x][y].getTerrainType()==EnumTerrainType.Sea) {
			String surroundingClose = "";
			String surroundingDistant = "";
			//Up
			if(y-1>=0) {
				if(this.tiles[x][y-1].getTerrainType()==EnumTerrainType.Sea||this.tiles[x][y-1].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x][y-1].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
				//Distant
				if(x-1>=0) {
					if(this.tiles[x-1][y-1].getTerrainType()==EnumTerrainType.Sea||this.tiles[x-1][y-1].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x-1][y-1].getTerrainType()==EnumTerrainType.Reef) {
						surroundingDistant+="S";
					} else {
						surroundingDistant+="E";
					}
				} else {
					surroundingDistant+="S";
				}
				if(x+1<Datas.coMap.getMapSize()[0]) {
					if(this.tiles[x+1][y-1].getTerrainType()==EnumTerrainType.Sea||this.tiles[x+1][y-1].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x+1][y-1].getTerrainType()==EnumTerrainType.Reef) {
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
				if(this.tiles[x+1][y].getTerrainType()==EnumTerrainType.Sea||this.tiles[x+1][y].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x+1][y].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
			} else {
				surroundingClose+="S";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.tiles[x][y+1].getTerrainType()==EnumTerrainType.Sea||this.tiles[x][y+1].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x][y+1].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
				//Distant
				if(x-1>=0) {
					if(this.tiles[x-1][y+1].getTerrainType()==EnumTerrainType.Sea||this.tiles[x-1][y+1].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x-1][y+1].getTerrainType()==EnumTerrainType.Reef) {
						surroundingDistant+="S";
					} else {
						surroundingDistant+="E";
					}
				} else {
					surroundingDistant+="S";
				}
				if(x+1<Datas.coMap.getMapSize()[0]) {
					if(this.tiles[x+1][y+1].getTerrainType()==EnumTerrainType.Sea||this.tiles[x+1][y+1].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x+1][y+1].getTerrainType()==EnumTerrainType.Reef) {
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
				if(this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Sea||this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Shoal||this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Reef) {
					surroundingClose+="S";
				} else {
					surroundingClose+="E";
				}
			} else {
				surroundingClose+="S";
			}
			this.tiles[x][y].animationPosition=EnumTerrainType.getSeaSubType(surroundingClose, surroundingDistant);
			return true;
		}
		
		//Plains
		if(this.tiles[x][y].getTerrainType()==EnumTerrainType.Plain) {
			if(x-1 >= 0) {
				if(this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Mountain || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Forest || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Property || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Port) {
					this.tiles[x][y].setAnimationPosition(0, 1);
				}
			}
			return true;
		}
		
		//Mountain
		if(this.tiles[x][y].getTerrainType()==EnumTerrainType.Mountain) {
			String surrounding = "";
			
			if(y-1>=0) {
				if(this.tiles[x][y-1].getTerrainType()==EnumTerrainType.Mountain) {
					surrounding+="M";
				} else {
					surrounding+="E";
				}
				if(y+1 < Datas.coMap.getMapSize()[1]) {
					if(this.tiles[x][y+1].getTerrainType()==EnumTerrainType.Mountain) {
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
					if(this.tiles[x][y+1].getTerrainType()==EnumTerrainType.Mountain) {
						surrounding+="M";
					} else {
						surrounding+="E";
					}
				} else {
					surrounding+="E";
				}
			}
			this.tiles[x][y].animationPosition=EnumTerrainType.getMountainSubType(surrounding);
			return true;
		}
		
		//Forest
		if(this.tiles[x][y].getTerrainType()==EnumTerrainType.Forest) {
			if(x-1 >= 0) {
				if(this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Mountain || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Forest || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Property || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Port) {
					this.tiles[x][y].setAnimationPosition(0, 1);
				}
			}
			return true;
		}
		
		//Road
		if(this.tiles[x][y].getTerrainType()==EnumTerrainType.Road) {
			String surrounding = "";
			//Up
			if(y-1>=0) {
				if(this.tiles[x][y-1].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Right
			if(x+1<Datas.coMap.getMapSize()[0]) {
				if(this.tiles[x+1][y].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.tiles[x][y+1].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Left
			if(x-1>=0) {
				if(this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			this.tiles[x][y].animationPosition=EnumTerrainType.getRoadSubType(surrounding);
			if(x-1 >= 0) {
				if(this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Mountain || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Forest || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Property || this.tiles[x-1][y].getTerrainType()==EnumTerrainType.Port) {
					this.tiles[x][y].animationPosition[1] = 1;
				}
			}
			return true;
		}
		
		//Beach - Shoal
		if(this.tiles[x][y].getTerrainType()==EnumTerrainType.Shoal) {
			String surrounding = "";
			//Up
			if(y-1>=0) {
				if(this.tiles[x][y-1].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.tiles[x][y-1].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Right
			if(x+1<Datas.coMap.getMapSize()[0]) {
				if(this.tiles[x+1][y].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.tiles[x+1][y].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.tiles[x][y+1].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.tiles[x][y+1].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Left
			if(x-1>=0) {
				if(this.tiles[x-1][y].terrainType==EnumTerrainType.Sea) {
					surrounding+="S";
				} else if (this.tiles[x-1][y].terrainType==EnumTerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			this.tiles[x][y].animationPosition=EnumTerrainType.getBeachSubType(surrounding);
			return true;
		}
		
		return false;
	}

	public void render(SpriteBatch batch) {
		int startingPointY = Gdx.graphics.getHeight()+Assets.renderOffset[1]-Assets.tileRenderSize[Assets.tileRenderSizeIndex]; //Used for Y axis
		for(int y=0; y<this.mapSize[1]; y++) {
			for(int x=0; x<this.mapSize[0] ;x++) {
				this.tiles[x][y].render(batch, 0+(x*Assets.tileRenderSize[Assets.tileRenderSizeIndex]), startingPointY-(y*Assets.tileRenderSize[Assets.tileRenderSizeIndex]));
			}
		}
		for(int i=0; i<this.buildings.size(); i++) {
			this.buildings.get(i).render(batch, 0+(buildings.get(i).position[0]*Assets.tileRenderSize[Assets.tileRenderSizeIndex]), startingPointY-(buildings.get(i).position[1]*Assets.tileRenderSize[Assets.tileRenderSizeIndex]));
		}
	}

	private EnumTerrainType getTerrainType(char terrainLetter) {
		EnumTerrainType[] terrains = EnumTerrainType.values();
		for(int i=0; i<terrains.length; i++) {
			if(terrains[i].getTerrainLetter().equals(String.valueOf(terrainLetter))) {
				return terrains[i];
			}
		}
		return EnumTerrainType.Plain;
	}

	/**
	 * @param String path - The output folder's name
	 * @param String fileName - The map file's name
	 * @return boolean - <b>true</b> if the file has been saved or <b>false</b> if not.
	 */
	public boolean exportMap(String path, String fileName) {
		this.name = fileName;
		try {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(this);
			//Note: the ".cawm" extension stands for "Compressed Advance Wars Map", it might change later.
			if(Utils.saveByteArray("./datas/maps/"+path+"/"+fileName+".cawm", Utils.compressString(json), true)) {
				Gdx.app.log(Utils.getFormatedTime(), "Your map as been saved as: "+fileName+".cawm");
				Gdx.app.log(Utils.getFormatedTime(), "File's size: "+json.getBytes("UTF-8").length+" -> "+Utils.compressString(json).length);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
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
	
	public void setBuilding(int x, int y, Building building) {
		//Checking if a HQ from the same faction already exists.
		if(building.buildingType.getId() == 5) {
			for(int i=0; i<this.buildings.size(); i++) {
				if(this.buildings.get(i).buildingType.getId()==5 && this.buildings.get(i).faction.getId()==building.faction.getId()) {
					this.tiles[this.buildings.get(i).position[0]][this.buildings.get(i).position[1]] = new Tile(EnumTerrainType.Plain, this.buildings.get(i).position[0], this.buildings.get(i).position[1]);
					this.buildings.remove(i);
				}
			}
		}
		this.buildings.add(new Building(building.buildingType.getId(), x, y, building.faction.getId()));
	}
	
	@Override
	public String toString() {
	   return "DataObject [formatVersion="+this.formatVersion+",name="+this.name+",author="+this.author+",mapSize="+this.mapSize+",tiles="+this.tiles+"]";
	}
	
	//The x and Y position must be the center of an area.
	public void renderPreview(SpriteBatch batch, int posX, int posY) {
		
	}
}
