package com.azias.advancewarsbootleg.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map  extends Object {
	protected int[] mapSize = new int[2];
	protected Tile[][] mapTiles;
	
	public Map() {
		
	}
	
	public boolean loadMap(String path, String mapName) {
		try {
			@SuppressWarnings("resource")
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
	
    private void setTileSubType(int x, int y) {
    	
		
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
}
