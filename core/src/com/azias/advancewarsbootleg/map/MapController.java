package com.azias.advancewarsbootleg.map;

//import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.enums.EnumTerrainType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapController extends Object {
	protected Map map;
	
	public MapController() {
		this.map = new Map();
	}
	
	public boolean loadMap(String path, String mapName) {
		return this.map.loadMap(path, mapName);
	}
	
	public boolean exportMap(String path, String mapName, String name, String author) {
		return this.map.exportMap(path, mapName, name, author);
	}
	
	public void render(SpriteBatch batch) {
		this.map.render(batch);
	}
	
	public int[] getMapSize() {
		return this.map.mapSize;
	}
	
	public EnumTerrainType getTileTerrainType(int x, int y) {
		return this.map.mapTiles[x][y].terrainType;
	}
	
	public boolean setTileTerrainType(int x, int y, EnumTerrainType par3) {
		this.map.mapTiles[x][y].setTerrainType(par3);
		
		if(true/*par3==EnumTerrainType.Sea*/) {
			//Slow as hell
			for(int i=0; i<this.map.mapSize[1]; i++) {
				for(int j=0; j<this.map.mapSize[0]; j++) {
					this.map.setTileSubType(j, i);
				}
			}
		}/*else {
			//This part doesn't seems to work with water...
			//Not only with water, I'll check that later.
			if(y-1>=0) {
				//Update Top
				this.map.setTileSubType(x, y-1);
				if(x-1>=0) {
					//Update Top Left and Left
					this.map.setTileSubType(x-1, y-1);
					this.map.setTileSubType(x-1, y);
				}
				if(x+1 < Datas.coMap.getMapSize()[0]) {
					//Update Top Right and Right
					this.map.setTileSubType(x+1, y-1);
					this.map.setTileSubType(x+1, y);
				}
			}
			if(y+1 < Datas.coMap.getMapSize()[1]) {
				//Update Bottom
				this.map.setTileSubType(x, y+1);
				if(x-1>=0) {
					//Update Bottom Left
					this.map.setTileSubType(x-1, y+1);
				}
				if(x+1 < Datas.coMap.getMapSize()[0]-1) {
					//Update Bottom Right
					this.map.setTileSubType(x+1, y+1);
				}
			}
		}*/
		this.map.setTileSubType(x, y);
		return true; 
	}
}
