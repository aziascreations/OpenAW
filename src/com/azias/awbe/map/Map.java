package com.azias.awbe.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	protected Tile[][] tiles;
	protected String name, description, author;
	
	public Map() {
		//Test stuff
		this.tiles = new Tile[10][10];
		for(int y=0; y<this.tiles[0].length; y++) {
			for(int x=0; x<this.tiles.length; x++) {
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
}
