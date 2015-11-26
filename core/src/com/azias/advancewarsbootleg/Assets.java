package com.azias.advancewarsbootleg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static Texture[] mapSelectParts;
	public static Texture[] guiTitles;
	public static Texture font;
	public static TextureRegion[][] fontTextures;
	
	public static Texture arrowOpen, arrowClose, arrowFiller, tileTabs;
	public static Texture pointer;
	public static TextureRegion[] editorSelectTileImages;
	
	public static Object[] tilesGraphics;
	public static boolean[] tileGraphicsBooleans;
	public static int tileRenderSizeIndex = 1;
	public static int[] tileRenderSize = new int[] {16,32,48,64,80,96};
	public static int[] renderOffset = new int[] {0,0};
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load() {
		background = loadTexture("gfx/gui/background/background_06.png");
		
		mapSelectParts = new Texture[3];
		mapSelectParts[0] = loadTexture("gfx/gui/mapselect/mapselectPart_01.png");
		mapSelectParts[1] = loadTexture("gfx/gui/mapselect/mapselectPart_02.png");
		mapSelectParts[2] = loadTexture("gfx/gui/mapselect/mapselectPart_03.png");
		
		guiTitles = new Texture[1];
		guiTitles[0] = loadTexture("gfx/gui/titles/chooseMap.png");
		
		font = loadTexture("gfx/fonts/fontPalette.png");
		fontTextures = new TextureRegion[26][4];

		arrowOpen = loadTexture("gfx/gui/editor/arrow_1.png");
		arrowClose = loadTexture("gfx/gui/editor/arrow_2.png");
		arrowFiller = loadTexture("gfx/gui/editor/tabsBack.png");
		tileTabs = loadTexture("gfx/gui/editor/tabsPics.png");
		
		editorSelectTileImages = new TextureRegion[11];
		Texture temp1 = loadTexture("gfx/gui/editor/tileImagesAlt.png");
		for(int i=0; i<editorSelectTileImages.length; i++) {
			editorSelectTileImages[i] = new TextureRegion(temp1, i*64, 0, 64, 64);
		}
		
		pointer = loadTexture("gfx/gui/editor/mapCursor.png");
		
		/* Tiles */
		TextureRegion[][] a = null;
		tilesGraphics = new Object[10];
		tileGraphicsBooleans = new boolean[10];
		
		//Plains
		a = new TextureRegion[2][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/plain_base.png"),0,0,16,16);
		a[1][0] = new TextureRegion(new Texture("gfx/terrain/plain_base.png"),0,16,16,16);
		tilesGraphics[0] = a;
		tileGraphicsBooleans[0] = false;
		
		//Forest
		a = new TextureRegion[2][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/plain_forest.png"),0,0,16,16);
		a[1][0] = new TextureRegion(new Texture("gfx/terrain/plain_forest.png"),16,0,16,16);
		tilesGraphics[1] = a;
		tileGraphicsBooleans[1] = false;
		
		//Roads
		a = new TextureRegion[11][2];
		for(int i = 0; i<10; i++) {
			a[i][0] = new TextureRegion(new Texture("gfx/terrain/plain_road.png"),0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(new Texture("gfx/terrain/plain_road.png"),0+(i*16),16,16,16);
		}
		tilesGraphics[2] = a;
		tileGraphicsBooleans[2] = false;
		
		//Mountains
		a = new TextureRegion[4][1];
		for(int i = 0; i<a.length; i++) {
			a[i][0] = new TextureRegion(new Texture("gfx/terrain/plain_mountain.png"),0+(i*16),0,16,16);
		}
		tilesGraphics[3] = a;
		tileGraphicsBooleans[3] = false;
		
		//River
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/plain_river.png"),0,0,16,16);
		tilesGraphics[4] = a;
		tileGraphicsBooleans[4] = false;
		
		//Beach18
		a = new TextureRegion[18][2];
		for(int i = 0; i<18; i++) {
			a[i][0] = new TextureRegion(new Texture("gfx/terrain/plain_beach.png"),0+(i*16),0,16,16);
			a[i][1] = new TextureRegion(new Texture("gfx/terrain/plain_beach.png"),0+(i*16),16,16,16);
		}
		tilesGraphics[5] = a;
		tileGraphicsBooleans[5] = false;
		
		//Sea
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/plain_sea.png"),0,0,16,16);
		tilesGraphics[6] = a;
		tileGraphicsBooleans[6] = false;
		
		//Property
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/plain_building.png"),0,0,16,16);
		tilesGraphics[8] = a;
		tileGraphicsBooleans[8] = false;
		
		//Port
		a = new TextureRegion[1][1];
		a[0][0] = new TextureRegion(new Texture("gfx/terrain/plain_building.png"),0,0,16,16);
		tilesGraphics[9] = a;
		tileGraphicsBooleans[9] = false;
	}
}
