package com.azias.advancewarsbootleg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import com.azias.advancewarsbootleg.gui.GuiController;
import com.azias.advancewarsbootleg.map.MapController;
import com.azias.advancewarsbootleg.net.ClientController;
import com.badlogic.gdx.Gdx;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Datas {
	public static Map<String, String> lang; 
	
	public final static String sessionKey = Utils.nextSessionId();
	
	public static MapController coMap = new MapController();
	public static GuiController coGui = new GuiController();
	
	//TODO: Find to deal with the fact that java can't access a volatile thing from a static context.
	public static ClientController coClient = null;

	//Bug: They play sounds at 1.0F when set at 0.0F.
	public static float volumeEffects = 1.0F;
	public static float volumeMusic = 1.0F;
	
	public static boolean loadConfigFile() {
		if(Gdx.files.internal("config.json").file().exists()) {
			
		} else {
			
		}
		return false;
	}
	
	public static boolean loadLangFile(String language) {
		try {
			lang = new Hashtable<String, String>();
			JsonParser parser = new JsonParser();
			JsonObject jObj = (JsonObject)parser.parse(Utils.readFile("datas/lang/"+language+".json", StandardCharsets.UTF_8));
			for (Entry<String, JsonElement> e : jObj.entrySet()) {
				lang.put(e.getKey(), e.getValue().getAsString());
			}
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
