package com.azias.openaw.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	//@Expose(serialize = false, deserialize = false) //Doesn't work...
	protected transient Tile[][] tiles;
	
	protected String name = "!Default Map";
	protected String description = "!No Description";
	protected String[] authors = {"!Anon"};
	protected String website = "none";
	protected String[][] tilesIds;
	protected Building[] buildings;
	
	//Position ?
	protected Unit[] units;
	
	public Map() {
		this(10,10);
	}
	
	public Map(int width, int height) {
		this.tiles = new Tile[width][height];
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				this.tiles[x][y] = new Tile();
			}
		}
	}
	
	public void render(SpriteBatch batch) {
		for(int y=0; y<this.tiles[0].length; y++) {
			for(int x=0; x<this.tiles.length; x++) {
				this.tiles[x][y].render(batch);
			}
		}
	}
	
	public void addUnit(int x, int y, Unit unit) {
		
	}
	
	public void test() {
		this.tiles = new Tile[10][10];
		for(int y=0; y<this.tiles[0].length; y++) {
			for(int x=0; x<this.tiles.length; x++) {
				this.tiles[x][y] = new Tile();
			}
		}
	}
	
	public void prepareExport() {
		this.tilesIds = new String[tiles.length][tiles[0].length];
		for(int y=0; y<tilesIds[0].length; y++) {
			for(int x=0; x<tilesIds.length; x++) {
				this.tilesIds[x][y] = this.tiles[x][y].getId();
			}
		}
	}
}
