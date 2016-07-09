package com.azias.openaw.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	protected transient Tile[][] tiles;
	
	protected String name = "!Default Map";
	protected String description = "!No Description";
	protected String[] authors = {"!Anon"};
	protected String website = "none";
	protected String[][] tilesIds;
	
	//Position ?
	protected Unit[] units;
	
	public Map() {
		this(10,10);
	}
	
	public Map(int width, int height) {
		this.tiles = new Tile[width][height];
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				//TODO: Remove this constructor
				//this.tiles[x][y] = new Tile();
				this.tiles[x][y] = new Tile("default");
			}
		}
	}
	
	@Deprecated
	public void render(SpriteBatch batch) {
		for(int y=0; y<this.tiles[0].length; y++) {
			for(int x=0; x<this.tiles.length; x++) {
				//System.out.println("Rendring: "+x+"/"+y);
				this.tiles[x][y].render(batch, x, y, 64);
			}
		}
	}
	
	public void render(SpriteBatch batch, int offsetX, int offsetY, int zoomFactor) {
		for(int y=0; y<this.tiles[0].length; y++) {
			for(int x=0; x<this.tiles.length; x++) {
				//System.out.println("Rendring: "+x+"/"+y);
				this.tiles[x][y].render(batch, offsetX, offsetY, zoomFactor);
			}
		}
	}
	
	public void addUnit(int x, int y, Unit unit) {
		
	}
	
	//GetEstimatedRenderedSize
	//Will be used to center the map if needed
	
	public void prepareExport() {
		//TODO: Add a special variable for building infos.
		this.tilesIds = new String[tiles.length][tiles[0].length];
		for(int y=0; y<tilesIds[0].length; y++) {
			for(int x=0; x<tilesIds.length; x++) {
				this.tilesIds[x][y] = this.tiles[x][y].getId();
			}
		}
	}

	public void test2() {
		for(int y=0; y<this.tiles[0].length; y++) {
			for(int x=0; x<this.tiles.length; x++) {
				this.tiles[x][y] = Tile.getTile("plains");
			}
		}
	}
}
