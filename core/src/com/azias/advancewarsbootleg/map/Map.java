package com.azias.advancewarsbootleg.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map extends Object {
	protected int[] mapSize = new int[2];
	protected Tile[][] mapTiles;
	
	public Map() {
		
	}
	
	public boolean loadMap(String path, String mapName) {
		try {
			@SuppressWarnings("resource")
			//This method doesn't work properly -> Bad final path.
			//String mapFile = new Scanner(Gdx.files.internal("maps/"+path+"/"+mapName+".awm").file()).useDelimiter("\\Z").next();
			String mapFile = new Scanner(new File("assets-desktop/maps/"+path+"/"+mapName+".awm")).useDelimiter("\\Z").next();
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
						//System.out.println(x+"/"+y);
						this.setTileSubType(x,y);
					}
				}
			} else {
				
			}
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
    protected boolean setTileSubType(int x, int y) {
    	this.mapTiles[x][y].animationPosition = new int[] {0,0};
    	//Sea
		if(this.mapTiles[x][y].getTerrainType()==TerrainType.Sea) {
			return true;
		}
    	
		//Plains
		if(this.mapTiles[x][y].getTerrainType()==TerrainType.Plain) {
			if(x-1 >= 0) {
				if(this.mapTiles[x-1][y].getTerrainType()==TerrainType.Mountain || this.mapTiles[x-1][y].getTerrainType()==TerrainType.Forest || this.mapTiles[x-1][y].getTerrainType()==TerrainType.Property || this.mapTiles[x-1][y].getTerrainType()==TerrainType.Port) {
					this.mapTiles[x][y].setAnimationPosition(0, 1);
				}
			}
			return true;
		}
		//Mountain - Need to be improved
		if(this.mapTiles[x][y].getTerrainType()==TerrainType.Mountain) {
			try {

				if(y-1>=0) {
					if(this.mapTiles[x][y-1].getTerrainType()==TerrainType.Mountain) {
						if(this.mapTiles[x][y+1].getTerrainType()==TerrainType.Mountain) {
							this.mapTiles[x][y].setAnimationPosition(0, 2);
						} else {
							this.mapTiles[x][y].setAnimationPosition(0, 3);
						}
					} else {
						if(this.mapTiles[x][y+1].getTerrainType()==TerrainType.Mountain) {
							this.mapTiles[x][y].setAnimationPosition(0, 1);
						}
					}
				} else {
					//The mountain is on the top edge
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				
			}
			return true;
		}
		//Forest
		if(this.mapTiles[x][y].getTerrainType()==TerrainType.Forest) {
			if(x-1 >= 0) {
				if(this.mapTiles[x-1][y].getTerrainType()==TerrainType.Mountain || this.mapTiles[x-1][y].getTerrainType()==TerrainType.Forest || this.mapTiles[x-1][y].getTerrainType()==TerrainType.Property || this.mapTiles[x-1][y].getTerrainType()==TerrainType.Port) {
					this.mapTiles[x][y].setAnimationPosition(0, 1);
				}
			}
			return true;
		}
		
		if(this.mapTiles[x][y].getTerrainType()==TerrainType.Road) {
			String surrounding = "";
			//Up
			if(y-1>=0) {
				if(this.mapTiles[x][y-1].getTerrainType()==TerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Right
			if(x+1<Datas.coMap.getMapSize()[0]) {
				if(this.mapTiles[x+1][y].getTerrainType()==TerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.mapTiles[x][y+1].getTerrainType()==TerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			//Left
			if(x-1>=0) {
				if(this.mapTiles[x-1][y].getTerrainType()==TerrainType.Road) {
					surrounding+="R";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="E";
			}
			this.mapTiles[x][y].animationPosition=TerrainType.getRoadSubType(surrounding);
			return true;
		}
		
		//Beach - Shoal
		if(this.mapTiles[x][y].getTerrainType()==TerrainType.Shoal) {
			String surrounding = "";
			//Up
			if(y-1>=0) {
				if(this.mapTiles[x][y-1].terrainType==TerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x][y-1].terrainType==TerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Right
			if(x+1<Datas.coMap.getMapSize()[0]) {
				if(this.mapTiles[x+1][y].terrainType==TerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x+1][y].terrainType==TerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Bottom
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				if(this.mapTiles[x][y+1].terrainType==TerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x][y+1].terrainType==TerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			//Left
			if(x-1>=0) {
				if(this.mapTiles[x-1][y].terrainType==TerrainType.Sea) {
					surrounding+="S";
				} else if (this.mapTiles[x-1][y].terrainType==TerrainType.Shoal) {
					surrounding+="B";
				} else {
					surrounding+="E";
				}
			} else {
				surrounding+="S";
			}
			this.mapTiles[x][y].animationPosition=TerrainType.getBeachSubType(surrounding);
			return true;
		}
		
		return false;
	}

	public void render(SpriteBatch batch) {
		int startingPoint = Gdx.graphics.getHeight()+Assets.renderOffset[0]-Assets.tileRenderSize[Assets.tileRenderSizeIndex]; //Used for Y axis
		for(int y=0; y<this.mapSize[1]; y++) {
			for(int x=0; x<this.mapSize[0] ;x++) {
				this.mapTiles[x][y].render(batch, 0+(x*Assets.tileRenderSize[Assets.tileRenderSizeIndex]), startingPoint-(y*Assets.tileRenderSize[Assets.tileRenderSizeIndex]));
			}
		}
	}

	private TerrainType getTerrainType(char terrainLetter) {
		switch(terrainLetter) {
		case '_':
			return TerrainType.Plain;
		case 'S':
			return TerrainType.Sea;
		case 'F':
			return TerrainType.Forest;
		case 'M':
			return TerrainType.Mountain;
		case 'B':
			return TerrainType.Shoal;
		case 'R':
			return TerrainType.Road;
		case 'r':
			return TerrainType.River;
		case 'C':
			return TerrainType.Property;
		case 'c':
			return TerrainType.Port;
		case 'P':
			//TODO Add a pipe terrain
			return TerrainType.Plain;
		default:
			return TerrainType.Plain;
		}
    }

	/**
	 * @param String path - The output folder's name
	 * @param String mapName - The map file's name
	 * @param String name - The map's name
	 * @param String author - The author's name
	 * @return boolean Unused - Might be used to cast an error and end the function
	 */
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
			File f = new File("assets-desktop/maps/"+path+"/"+mapName+".awm");
			if(f.exists() && !f.isDirectory()) {
				//TODO: ask to replace the file
				System.out.println("ERROR: A file named "+mapName+".awm already exist!");
				return false;
			} else {
				PrintWriter out = new PrintWriter("assets-desktop/maps/"+path+"/"+mapName+".awm");
				out.println(mapContent);
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
