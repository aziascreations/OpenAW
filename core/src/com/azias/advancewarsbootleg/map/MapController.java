package com.azias.advancewarsbootleg.map;

import java.io.File;
import java.io.IOException;

import com.azias.advancewarsbootleg.Utils;
//import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.enums.EnumTerrainType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.Gson;

public class MapController extends Object {
	protected Map map = null;
	
	public MapController() {
		this.map = null;
	}
	
	public boolean loadMap(String path, String mapName) {
		this.map = new Map();
		if(new File("./datas/maps/"+path+"/"+mapName+".cawm").exists()) {
			Gdx.app.log(Utils.getFormatedTime(), "Loading "+path+"/"+mapName+".cawm...");
			Gson gson = new Gson();
			try {
				String mapContent = new String(Utils.uncompressByteArray(Utils.fileToByteArray("./datas/maps/"+path+"/"+mapName+".cawm")));
				this.map = gson.fromJson(mapContent, Map.class);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//this.map = null;
		//TODO: Remove this after implementing the new file format.
		this.map = new Map();
		return this.map.loadMap(path, mapName);
		//return false;
	}
	
	public boolean exportMap(String path, String fileName) {
		return this.map.exportMap(path, fileName);
	}
	
	public void render(SpriteBatch batch) {
		if(this.isMapLoaded()) {
			this.map.render(batch);
		}
	}
	
	public void renderPreview(SpriteBatch batch, int posX, int posY) {
		if(this.isMapLoaded()) {
			this.map.renderPreview(batch, posX, posY);
		}
	}
	
	public int[] getMapSize() {
		return this.map.mapSize;
	}
	
	public EnumTerrainType getTileTerrainType(int x, int y) {
		return this.map.tiles[x][y].terrainType;
	}
	
	public boolean setTileTerrainType(int x, int y, EnumTerrainType par3, Building par4) {
		if(this.map.tiles[x][y].getTerrainType() == EnumTerrainType.Property || this.map.tiles[x][y].getTerrainType() == EnumTerrainType.Port) {
			this.map.removeBuiding(x, y);
		}
		
		this.map.tiles[x][y].setTerrainType(par3);
		
		if(par4 != null) {
			this.map.setBuilding(x, y, par4);
		}
		
		//I had to move it again as it sometimes does nothing when in the if(true).
		for(int i=0; i<this.map.mapSize[1]; i++) {
			for(int j=0; j<this.map.mapSize[0]; j++) {
				this.map.setTileSubType(j, i);
			}
		}
		
		//My eyes, they bleed...
		/*if(true/*par3==EnumTerrainType.Sea) {
			//Slow as hell, but at least it's working
			/*for(int i=0; i<this.map.mapSize[1]; i++) {
				for(int j=0; j<this.map.mapSize[0]; j++) {
					this.map.setTileSubType(j, i);
				}
			}
		}*//*else {
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

	public Building getBuidingType(int x, int y) {
		for(int i = 0; i<this.map.buildings.size(); i++) {
			if(this.map.buildings.get(i).position[0] == x && this.map.buildings.get(i).position[1] == y) {
				return this.map.buildings.get(i);
			}
		}
		return null;
	}
	
	public boolean isMapLoaded() {
		if(this.map == null) {
			return false;
		} else {
			return true;
		}
	}
}
