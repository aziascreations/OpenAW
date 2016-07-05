package com.azias.awbe.terrain;

import com.azias.openaw.map.Tile;

public class Terrain {
	public static void registerUnits() {
		Tile.registerTile("plains", new TilePlains());
	}
}
