package com.azias.advancewarsbootleg.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapController extends Object {
	protected Map map;
	
	public MapController() {
		this.map = new Map();
	}
	
	public boolean loadMap(String path, String mapName) {
		return this.map.loadMap(path, mapName);
	}
	
	//public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	//	this.map.render(container, game, g);
	//}
	
	public void render(SpriteBatch batch) {
		this.map.render(batch);
	}
	
	public int[] getMapSize() {
		return this.map.mapSize;
	}
}
