package com.azias.openaw.map;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azias.openaw.Assets;
import com.azias.openaw.enums.EnumUnitMouvementClass;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//http://www.warscentral.com/aw2/terrain.shtml
//IDEA: combine the id and unlocalizedName ?
public class Tile {
	public enum TileCategory {
		TERRAIN,
		BUILDING,
		SPECIAL;
	}
	
	protected ArrayList<EnumUnitMouvementClass> allowedUnits = new ArrayList<EnumUnitMouvementClass>() {{
		add(EnumUnitMouvementClass.FOOT);
		add(EnumUnitMouvementClass.MECH);
	}};
	
	protected String id = "default";
	protected String unlocalizedName = "default";
	
	protected int defenseRating = 0;
	//protected int mouvementCost = 1; //Will be calculated differently
	/** Used for forests and reefs */
	protected boolean onlyVisibleToAdjacentUnits = false;
	
	protected TileCategory category = TileCategory.TERRAIN;
	
	//protected boolean isBuilding = false; //Redundant
	protected int buildingHitPoint = 20;
	
	//protected TextureRegion[] textures;
	
	protected int tileSubtype = 0;
	
	protected boolean isTall = false;
	
	@Deprecated
	protected Tile() {
		this("default");
	}
	
	public Tile(String id) {
		this.id = id;
		
		/*Texture a = Assets.getTexture(this.id);
		if(a.getWidth()==a.getHeight()) {
			this.textures = new TextureRegion[] {new TextureRegion(a)};
		} else {
			this.textures = new TextureRegion[a.getWidth()/(a.getHeight()/2)];
			for(int i=0; i<this.textures.length; i++) {
				this.textures[i] = new TextureRegion(a, (a.getHeight()/2)*i, 0, a.getHeight()/2, a.getHeight());
			}
		}*/
	}
	
	public void onNewTurn() {
		
	}
	
	public boolean onCaptured() {
		return false;
	}
	
	//unitmovein/out/trough - ?
	
	public void render(SpriteBatch batch) {
		
	}

	public void render(SpriteBatch batch, int x, int y, int spriteSize) {
		//TODO: check if "textures" doesn't use the default one to avoid Exceptions
		//batch.draw(this.textures[this.getSubtype], x*spriteSize, y*spriteSize, spriteSize, spriteSize*2);
		batch.draw(Assets.getTexture("terrain-"+this.id+"-"+this.tileSubtype+"-none"), x*spriteSize, y*spriteSize, spriteSize, spriteSize*2);
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getUnlocalizedName() {
		return "tile."+this.unlocalizedName;
	}
	
	public String getLocalizedName() {
		return Assets.localizeString("tile."+this.unlocalizedName);
	}
	
	public Tile setId(String id) {
		this.id = id;
		return this;
	}
	
	public Tile setUnlocalizedName(String name) {
		this.unlocalizedName = name;
		return this;
	}
	
	public Tile setcategory(TileCategory cat) {
		this.category = cat;
		return this;
	}
	
	/* Static stuff */
	private final static Logger logger = LoggerFactory.getLogger(Tile.class);
	
	protected static HashMap<String, Tile> tiles = new HashMap<String, Tile>();
	
	//Could cause problems with the texture.
	//No longer a problem
	public static void registerTile(String id, Tile tile) {
		tiles.put(id, tile.setId(id));
	}
	
	public static void registerTile(Tile tile) {
		if(tile.getId().equals("default")) {
			logger.warn("A tile has been registered with the default id !");
			logger.debug("Class: {}", tile.getClass().getName());
		}
		tiles.put(tile.getId(), tile);
	}

	public static Tile getTile(String id) {
		Tile tile = tiles.get(id);
		if(tile==null) {
			logger.warn("Unable to retrieve this tile: {}", id);
		}
		return tile;
	}
}
