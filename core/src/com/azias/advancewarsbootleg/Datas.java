package com.azias.advancewarsbootleg;

import com.azias.advancewarsbootleg.gui.GuiController;
import com.azias.advancewarsbootleg.map.MapController;
import com.badlogic.gdx.Gdx;

public class Datas {
	public final static String sessionKey = Utils.nextSessionId();
	
	public static MapController coMap = new MapController();
	public static GuiController coGui = new GuiController();

	//Bug: They play sounds at 1.0F when set at 0.0F.
	public static float volumeEffects = 1.0F;
	public static float volumeMusic = 1.0F;
	
	//public static TextRenderer txtRenderer;
	
	public static boolean loadConfigFile() {
		if(Gdx.files.internal("config.json").file().exists()) {
			
		} else {
			
		}
		return false;
	}
}
