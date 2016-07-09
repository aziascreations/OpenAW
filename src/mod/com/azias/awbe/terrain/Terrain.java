package com.azias.awbe.terrain;

import com.azias.openaw.map.Tile;
import com.azias.openaw.map.Tile.TileCategory;

public class Terrain {
	public static void registerUnits() {
		Tile.registerTile(new TilePlains("plains").setUnlocalizedName("plains").setcategory(TileCategory.TERRAIN));
	}
}
