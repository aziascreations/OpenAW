package com.azias.advancewarsbootleg;

import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.azias.advancewarsbootleg.enums.EnumBuildingType;
import com.azias.advancewarsbootleg.enums.EnumCommandingOfficer;
import com.azias.advancewarsbootleg.gui.GuiStyle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Assets {
	public static Map<String, String> internalAssetsIndex;
	
	public static Map<String, GuiStyle> guiStylesIndex;
	
	//Fonts
	public static BitmapFont font24, font36, font48, font72;
	
	//Gui
	public static Texture guiDefaultBack, guiEditorSelectorTabs;
	public static TextureRegion[] guiEditorSelectTileImages;
	public static TextureRegion[][] guiEditorSelectBuildingImages;
	public static TextureRegion[][] guiIcons16;
	public static TextureRegion[][] guiIcons32;
	//public static TextureRegion[] guiStyleButton;
	
	//Buildings
	public static Object[] buildingsGraphics;
	//public static boolean[] buildingsGraphicsBooleans;
	
	//Cos
	public static TextureRegion[] cosNull;
	public static TextureRegion[] cosHeads;
	
	//Others Textures
	public static Texture background;
	public static Texture pointer;
	public static Object[] tilesGraphics;
	public static boolean[] tileGraphicsBooleans;
	
	//Sounds
	public static Sound soundButtonClick, soundMenuIn, soundMenuOut;
	
	//Misc
	public static int tileRenderSizeIndex = 1;
	public static int[] tileRenderSize = new int[] {16,32,48,64,80,96};
	public static int[] renderOffset = new int[] {0,0};
	
	/**
	 * Loads the specified texture and returns it.
	 * @param key - Asset's key
	 * @return The selected Texture if found.
	 */
	public static Texture loadTexture(String key) {
		//Note: The new system only works with internal files. It will be changed to handle external files later.
		//Maybe when the texture/mods packs will be added and handled.
		String value = internalAssetsIndex.get(key);
		if(value != null) {
			return new Texture(Gdx.files.internal(value));
		} else {
			Gdx.app.error(Utils.getFormatedTime(), "Error: Unable to find the asset linked to \""+key+"\"");
			System.exit(1);
			return null;
		}
	}
	
	public static Sound loadSound(String filePath) {
		return Gdx.audio.newSound(Gdx.files.internal(filePath));
	}
	
	public static Music loadMusic(String key) {
		String value = internalAssetsIndex.get(key);
		if(value != null) {
			return Gdx.audio.newMusic(Gdx.files.local(value));
		} else {
			Gdx.app.error(Utils.getFormatedTime(), "Error: Unable to find the asset linked to \""+key+"\"");
			System.exit(1);
			return null;
		}
		//return Gdx.audio.newMusic(Gdx.files.internal("data/mymusic.mp3"));
		//return Gdx.audio.newSound(Gdx.files.internal(filePath));
	}
	
	public static GuiStyle getGuiStyle(String key) {
		GuiStyle style = guiStylesIndex.get(key);
		if(style != null) {
			return style;
		} else {
			Gdx.app.error(Utils.getFormatedTime(), "Error: Unable to find the GuiStyle linked to \""+key+"\"");
			System.exit(1);
			return null;
		}
	}
	
	/**
	 * @param key - Asset's key
	 * @return The file's internal path
	 */
	public static String getAssetPath(String key) {
		String value;
		value = internalAssetsIndex.get(key);
		if(value != null) {
			return value;
		} else {
			Gdx.app.error(Utils.getFormatedTime(), "Error: Unable to find the asset linked to \""+key+"\"");
			System.exit(1);
			return null;
		}
	}
	
	public static void loadInternalAssetsIndex() {
		try {
			//Loading internal assets.
			internalAssetsIndex = new Hashtable<String, String>();
			JsonParser parser = new JsonParser();
			JsonObject jObj = (JsonObject)parser.parse(new String(Gdx.files.internal("assetsIndex.json").readBytes(), StandardCharsets.UTF_8));
			for(Entry<String, JsonElement> e : jObj.entrySet()) {
				internalAssetsIndex.put(e.getKey(), e.getValue().getAsString());
			}
			//TODO: Load external assets.
			
			//Loading internal styles.
			guiStylesIndex = new Hashtable<String, GuiStyle>();
			Set<String> keys = internalAssetsIndex.keySet();
			for(String key: keys){
				if(key.contains("style_")) {
					Gson gson = new Gson();
					String jsonContent = new String(Gdx.files.internal(internalAssetsIndex.get(key)).readBytes());
					GuiStyle style = gson.fromJson(jsonContent, GuiStyle.class);
					guiStylesIndex.put(style.getStyleKey(), style);
					//Gdx.app.log(Utils.getFormatedTime(), "Detected "+key+" as a GuiStyle.");
				}
				//Gdx.app.log(Utils.getFormatedTime(), key+" links to: "+internalAssetsIndex.get(key));
        	}
			//TODO: Load external styles.
			
			//Loading styles textures.
			keys = guiStylesIndex.keySet();
			for(String key: keys){
				GuiStyle style = guiStylesIndex.get(key);
				style.load();
				guiStylesIndex.put(key, style);
        	}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadAssets() {
		loadInternalAssetsIndex();
		Texture tempTexture;
		
		//Fonts
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(getAssetPath("font")));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		font24 = generator.generateFont(parameter);
		parameter.size = 36;
		font36 = generator.generateFont(parameter);
		parameter.size = 48;
		font48 = generator.generateFont(parameter);
		parameter.size = 72;
		font72 = generator.generateFont(parameter);
		generator.dispose();
		
		//Misc
		background = loadTexture("background");
		
		//Gui
		guiDefaultBack = loadTexture("guiDefaultBack");
		guiEditorSelectorTabs = loadTexture("guiEditorSelectorTabs");
		pointer = loadTexture("guiEditorPointer");
		
		/*guiStyleButton = new TextureRegion[9];
		tempTexture = loadTexture("guiStyleButton0");
		for(int i=0; i<3; i++) {
			guiStyleButton[i*3] = new TextureRegion(tempTexture, 0, tempTexture.getWidth()/3*i, tempTexture.getWidth()/3, tempTexture.getHeight()/3);
			guiStyleButton[i*3+1] = new TextureRegion(tempTexture, tempTexture.getWidth()/3, tempTexture.getWidth()/3*i, tempTexture.getWidth()/3, tempTexture.getHeight()/3);
			guiStyleButton[i*3+2] = new TextureRegion(tempTexture, tempTexture.getWidth()/3*2, tempTexture.getWidth()/3*i, tempTexture.getWidth()/3, tempTexture.getHeight()/3);
		}*/
		
		guiEditorSelectTileImages = new TextureRegion[11];
		tempTexture = loadTexture("guiEditorSelectTileImages");
		for(int i=0; i<guiEditorSelectTileImages.length; i++) {
			guiEditorSelectTileImages[i] = new TextureRegion(tempTexture, i*64, 0, 64, 64);
		}
		guiEditorSelectBuildingImages = new TextureRegion[6][6];
		tempTexture = loadTexture("guiEditorSelectBuildingImages");
		for(int i=0; i<6; i++) {
			for(int j=0; j<6; j++) {
				guiEditorSelectBuildingImages[j][i] = new TextureRegion(tempTexture, j*64, i*64, 64, 64);
			}
		}
		
		//Icons
		tempTexture = loadTexture("guiIcons16");
		guiIcons16 = new TextureRegion[tempTexture.getWidth()/16][tempTexture.getHeight()/16];
		for(int i=0; i<tempTexture.getHeight()/16; i++) {
			for(int j=0; j<tempTexture.getWidth()/16; j++) {
				guiIcons16[j][i] = new TextureRegion(tempTexture, j*16, i*16, 16, 16);
			}
		}
		tempTexture = loadTexture("guiIcons32");
		guiIcons32 = new TextureRegion[tempTexture.getWidth()/32][tempTexture.getHeight()/32];
		for(int i=0; i<tempTexture.getHeight()/32; i++) {
			for(int j=0; j<tempTexture.getWidth()/32; j++) {
				guiIcons32[j][i] = new TextureRegion(tempTexture, j*32, i*32, 32, 32);
			}
		}
		
		//Cos
		cosNull = new TextureRegion[1];
		cosNull[0] = new TextureRegion(loadTexture("coNullHead"));
		
		EnumCommandingOfficer[] cos = EnumCommandingOfficer.values();
		cosHeads = new TextureRegion[cos.length];
		for(int i=0; i<cos.length; i++) {
			cosHeads[i] = new TextureRegion(loadTexture("co"+cos[i].getAssetsName()+"0"),0,350,48,48);
		}
		
		//Sounds
		soundButtonClick = loadSound("sfx/sounds/menu/soundMenuMoving.ogg");
		soundMenuIn = loadSound("sfx/sounds/menu/soundMenuIn.ogg");
		soundMenuOut = loadSound("sfx/sounds/menu/soundMenuOut.ogg");
		
		//Tiles
		TextureRegion[][] a = null;
		tilesGraphics = new Object[10];
		tileGraphicsBooleans = new boolean[10];
		//Plains
		a = new TextureRegion[2][1];
		tempTexture = loadTexture("terrainPlains");
		a[0][0] = new TextureRegion(tempTexture,0,0,16,16);
		a[1][0] = new TextureRegion(tempTexture,0,16,16,16);
		tilesGraphics[0] = a;
		tileGraphicsBooleans[0] = false;
		//Forest
		a = new TextureRegion[2][1];
		tempTexture = loadTexture("terrainForest");
		a[0][0] = new TextureRegion(tempTexture,0,0,16,16);
		a[1][0] = new TextureRegion(tempTexture,16,0,16,16);
		tilesGraphics[1] = a;
		tileGraphicsBooleans[1] = false;
		//Roads
		a = new TextureRegion[11][2];
		tempTexture = loadTexture("terrainRoads");
		for(int i = 0; i<11; i++) {
			a[i][0] = new TextureRegion(tempTexture,0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(tempTexture,0+(i*16),16,16,16);
		}
		tilesGraphics[2] = a;
		tileGraphicsBooleans[2] = false;
		//Mountains
		a = new TextureRegion[4][1];
		tempTexture = loadTexture("terrainMountains");
		for(int i = 0; i<a.length; i++) {
			a[i][0] = new TextureRegion(tempTexture,0+(i*16),0,16,16);
		}
		tilesGraphics[3] = a;
		tileGraphicsBooleans[3] = false;
		//River
		a = new TextureRegion[1][1];
		tempTexture = loadTexture("terrainRiver");
		a[0][0] = new TextureRegion(tempTexture,0,0,16,16);
		tilesGraphics[4] = a;
		tileGraphicsBooleans[4] = false;
		//Beach - 18
		a = new TextureRegion[18][2];
		tempTexture = loadTexture("terrainBeach");
		for(int i = 0; i<18; i++) {
			a[i][0] = new TextureRegion(tempTexture,0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(tempTexture,0+(i*16),16,16,16);
		}
		tilesGraphics[5] = a;
		tileGraphicsBooleans[5] = false;
		//Sea
		tempTexture = loadTexture("terrainSea");
		a = new TextureRegion[tempTexture.getWidth()/16][tempTexture.getHeight()/16];
		for(int i = 0; i<tempTexture.getWidth()/16; i++) {
			a[i][0] = new TextureRegion(tempTexture,0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(tempTexture,0+(i*16),16,16,16);
			a[i][2] = new TextureRegion(tempTexture,0+(i*16),32,16,16);
		}
		tilesGraphics[6] = a;
		tileGraphicsBooleans[6] = false;
		//Property
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(loadTexture("terrainProperty"),0,0,16,16);
		tilesGraphics[8] = a;
		tileGraphicsBooleans[8] = false;
		//Port
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(loadTexture("terrainPort"),0,0,16,16);
		tilesGraphics[9] = a;
		tileGraphicsBooleans[9] = false;
		
		//Buildings - Needs to be improved...
		//The HQ should have a special "format"
		TextureRegion[] b = new TextureRegion[6];
		buildingsGraphics = new Object[6];
		
		//Town
		for(int i = 0;i < 6; i++) {
			tempTexture = loadTexture("building"+EnumBuildingType.Town.getFileName()+i);
			b[i] = new TextureRegion(tempTexture,0,0,16,32);
		}
		buildingsGraphics[0] = b;
		
		b = new TextureRegion[6];
		for(int i = 0;i < 6; i++) {
			tempTexture = loadTexture("building"+EnumBuildingType.Factory.getFileName()+i);
			b[i] = new TextureRegion(tempTexture,0,0,16,32);
		}
		buildingsGraphics[1] = b;
		
		b = new TextureRegion[6];
		for(int i = 0;i < 6; i++) {
			tempTexture = loadTexture("building"+EnumBuildingType.Airport.getFileName()+i);
			b[i] = new TextureRegion(tempTexture,0,0,16,32);
		}
		buildingsGraphics[2] = b;
		
		b = new TextureRegion[6];
		for(int i = 0;i < 6; i++) {
			tempTexture = loadTexture("building"+EnumBuildingType.Antena.getFileName()+i);
			b[i] = new TextureRegion(tempTexture,0,0,16,32);
		}
		buildingsGraphics[3] = b;
		
		//b = new TextureRegion[6];
		//buildingsGraphicsBooleans = new boolean[2];

		/*for(int i = 0;i < 6; i++) {
			temp1 = loadTexture("gfx/buildings/"+EnumBuildingType.Town.getFileName()+"_"+i+".png");
			b[i] = new TextureRegion(temp1,0,0,16,32);
		}
		buildingsGraphics[0] = b;
		//buildingsGraphicsBooleans[0] = false;
		
		b = new TextureRegion[6];
		for(int i = 0;i < 6; i++) {
			temp1 = loadTexture("gfx/buildings/"+EnumBuildingType.Factory.getFileName()+"_"+i+".png");
			b[i] = new TextureRegion(temp1,0,0,16,32);
		}
		buildingsGraphics[1] = b;
		//buildingsGraphicsBooleans[1] = false;*/
	}
}
