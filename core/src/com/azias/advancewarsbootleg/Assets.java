package com.azias.advancewarsbootleg;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.azias.advancewarsbootleg.enums.EnumBuildingType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Assets {
	//Fonts
	public static BitmapFont font24, font36, font48, font72;
	
	//Gui
	public static Texture guiDefaultBack, guiEditorSelectTileTabs;
	public static TextureRegion[] guiEditorSelectTileImages;
	public static TextureRegion[][] guiEditorSelectBuildingImages;
	
	public static TextureRegion[][] guiIcons;
	
	//Buildings
	public static Object[] buildingsGraphics;
	//public static boolean[] buildingsGraphicsBooleans;
	
	//Others Textures
	public static Texture background;
	public static Object[] tilesGraphics;
	public static boolean[] tileGraphicsBooleans;
	
	//Sounds
	public static Sound soundButtonClick, soundMenuIn, soundMenuOut;
	
	//Misc
	public static int tileRenderSizeIndex = 1;
	public static int[] tileRenderSize = new int[] {16,32,48,64,80,96};
	public static int[] renderOffset = new int[] {0,0};
	public static Texture pointer;
	
	public static Texture loadTexture(String filePath) {
		return new Texture(Gdx.files.internal(filePath));
	}
	
	public static Sound loadSound(String filePath) {
		return Gdx.audio.newSound(Gdx.files.internal(filePath));
	}
	
	public static String readFile(String path, Charset encoding) throws IOException  {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	//TODO: Add dispose() when needed to avoid memory leaks
	//I thinks it's done, but I'm not sure...
	public static void load() {
		//Fonts - Find a better way to handle them
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("gfx/fonts/adw2GBA.ttf"));
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
		
		//Sounds
		soundButtonClick = loadSound("sfx/sounds/menu/soundMenuMoving.ogg");
		soundMenuIn = loadSound("sfx/sounds/menu/soundMenuIn.ogg");
		soundMenuOut = loadSound("sfx/sounds/menu/soundMenuOut.ogg");
		
		//Textures
		background = loadTexture("gfx/gui/background/background_06.png");
		guiDefaultBack = loadTexture("gfx/gui/editor/tabsBack.png");
		guiEditorSelectTileTabs = loadTexture("gfx/gui/editor/tabsPics.png");
		
		pointer = loadTexture("gfx/gui/editor/mapCursor.png");
		
		//Datas.txtRenderer = new TextRenderer();
		Assets.loadTexturePackBasedAssets("plain");
	}
	
	public static void loadTexturePackBasedAssets(String par1) {
		//Gui
		guiEditorSelectTileImages = new TextureRegion[11];
		Texture temp1 = loadTexture("gfx/gui/editor/tileImages_"+par1+".png");
		for(int i=0; i<guiEditorSelectTileImages.length; i++) {
			guiEditorSelectTileImages[i] = new TextureRegion(temp1, i*64, 0, 64, 64);
		}
		guiEditorSelectBuildingImages = new TextureRegion[6][6];
		temp1 = loadTexture("gfx/gui/editor/buildingImages_"+par1+".png");
		for(int i=0; i<6; i++) {
			for(int j=0; j<6; j++) {
				guiEditorSelectBuildingImages[j][i] = new TextureRegion(temp1, j*64, i*64, 64, 64);
			}
			
		}
		//Don't use that, the icons are just black squares when using it.
		//temp1.dispose();
		
		//Tiles
		TextureRegion[][] a = null;
		tilesGraphics = new Object[10];
		tileGraphicsBooleans = new boolean[10];
		
		//Plains
		a = new TextureRegion[2][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_base.png"),0,0,16,16);
		a[1][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_base.png"),0,16,16,16);
		tilesGraphics[0] = a;
		tileGraphicsBooleans[0] = false;
		
		//Forest
		a = new TextureRegion[2][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_forest.png"),0,0,16,16);
		a[1][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_forest.png"),16,0,16,16);
		tilesGraphics[1] = a;
		tileGraphicsBooleans[1] = false;
		
		//Roads
		a = new TextureRegion[11][2];
		for(int i = 0; i<11; i++) {
			a[i][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_road.png"),0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_road.png"),0+(i*16),16,16,16);
		}
		tilesGraphics[2] = a;
		tileGraphicsBooleans[2] = false;
		
		//Mountains
		a = new TextureRegion[4][1];
		for(int i = 0; i<a.length; i++) {
			a[i][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_mountain.png"),0+(i*16),0,16,16);
		}
		tilesGraphics[3] = a;
		tileGraphicsBooleans[3] = false;
		
		//River
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_river.png"),0,0,16,16);
		tilesGraphics[4] = a;
		tileGraphicsBooleans[4] = false;
		
		//Beach - 18
		a = new TextureRegion[18][2];
		for(int i = 0; i<18; i++) {
			a[i][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_beach.png"),0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_beach.png"),0+(i*16),16,16,16);
		}
		tilesGraphics[5] = a;
		tileGraphicsBooleans[5] = false;
		
		//Sea
		temp1 = loadTexture("gfx/terrain/"+par1+"_sea.png");
		a = new TextureRegion[temp1.getWidth()/16][temp1.getHeight()/16];
		for(int i = 0; i<temp1.getWidth()/16; i++) {
			a[i][0] = new TextureRegion(temp1,0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(temp1,0+(i*16),16,16,16);
			a[i][2] = new TextureRegion(temp1,0+(i*16),32,16,16);
		}
		tilesGraphics[6] = a;
		tileGraphicsBooleans[6] = false;
		//a = new TextureRegion[1][1];
		//a[0][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_sea.png"),0,0,16,16);
		//tilesGraphics[6] = a;
		//tileGraphicsBooleans[6] = false;
		
		//Property
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_building.png"),0,0,16,16);
		tilesGraphics[8] = a;
		tileGraphicsBooleans[8] = false;
		
		//Port
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/"+par1+"_building.png"),0,0,16,16);
		tilesGraphics[9] = a;
		tileGraphicsBooleans[9] = false;
		
		//Icons - Will be moved somewhere else
		temp1 = loadTexture("gfx/gui/icons16.png");
		guiIcons = new TextureRegion[temp1.getWidth()/16][temp1.getHeight()/16];
		for(int i=0; i<temp1.getHeight()/16; i++) {
			for(int j=0; j<temp1.getWidth()/16; j++) {
				guiIcons[j][i] = new TextureRegion(temp1, j*16, i*16, 16, 16);
			}
		}
		
		//Buildings - Needs to be improved...
		//The HQ should have a special "format"
		TextureRegion[] b = new TextureRegion[6];
		buildingsGraphics = new Object[6];
		
		//Town
		for(int i = 0;i < 6; i++) {
			temp1 = loadTexture("gfx/buildings/"+EnumBuildingType.Town.getFileName()+"_"+i+".png");
			b[i] = new TextureRegion(temp1,0,0,16,32);
		}
		buildingsGraphics[0] = b;
		
		b = new TextureRegion[6];
		for(int i = 0;i < 6; i++) {
			temp1 = loadTexture("gfx/buildings/"+EnumBuildingType.Factory.getFileName()+"_"+i+".png");
			b[i] = new TextureRegion(temp1,0,0,16,32);
		}
		buildingsGraphics[1] = b;
		
		b = new TextureRegion[6];
		for(int i = 0;i < 6; i++) {
			temp1 = loadTexture("gfx/buildings/"+EnumBuildingType.Airport.getFileName()+"_"+i+".png");
			b[i] = new TextureRegion(temp1,0,0,16,32);
		}
		buildingsGraphics[2] = b;
		
		b = new TextureRegion[6];
		for(int i = 0;i < 6; i++) {
			temp1 = loadTexture("gfx/buildings/"+EnumBuildingType.Antena.getFileName()+"_"+i+".png");
			b[i] = new TextureRegion(temp1,0,0,16,32);
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
