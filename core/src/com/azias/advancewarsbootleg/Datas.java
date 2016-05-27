package com.azias.advancewarsbootleg;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.azias.advancewarsbootleg.animation.AnimationController;
import com.azias.advancewarsbootleg.gui.GuiController;
import com.azias.advancewarsbootleg.map.MapController;
import com.azias.advancewarsbootleg.net.ClientController;
import com.badlogic.gdx.Gdx;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

public class Datas {
	public static Map<String, String> lang; 
	
	public final static String sessionKey = Utils.nextSessionId();
	public static String side = "server";
	
	public static MapController coMap = new MapController();
	public static GuiController coGui = new GuiController();
	public static MusicController coMusic = new MusicController();
	public static AnimationController coAnim = new AnimationController();
	
	public static ClientController coClient = null;

	//Bug: They play sounds at 1.0F when set at 0.0F.
	public static float volumeEffects = 1.0F;
	public static float volumeMusic = 0.2F;
	
	//JavaScript Engine Test.
	public static ScriptEngine javaScriptEngine;

	public static String name = "none";
	
	public static void startJSEngine() {
		if(Integer.parseInt(System.getProperty("java.version").split("\\.")[1])>=8) {
			javaScriptEngine = new NashornScriptEngineFactory().getScriptEngine(new String[] {"--no-java"});
		} else {
			Gdx.app.error(Utils.getFormatedTime(), "Error: Unable to start JavaScript engine, please use java 8 at least.");
		}
	}

	public static void loadJSScripts() {
		loadJSScript("initUtils");
	}
	
	public static void loadJSScript(String scriptName) {
		try {
			javaScriptEngine.eval(new FileReader("./scripts/"+scriptName+".js"));
		} catch (FileNotFoundException | ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getJSResult(String function, Object... args) {
		try {
			Invocable invocable = (Invocable) javaScriptEngine;
			Object result = invocable.invokeFunction(function, args);
			return result;
		} catch (NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void executeJSCommand(String command) {
		try {
			javaScriptEngine.eval(command);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean loadConfigFile() {
		if(Gdx.files.internal("./config.json").file().exists()) {
			
		} else {
			
		}
		return false;
	}
	
	public static boolean loadLangFile(String language) {
		try {
			lang = new Hashtable<String, String>();
			JsonParser parser = new JsonParser();
			JsonObject jObj = (JsonObject)parser.parse(Utils.fileToString("./datas/lang/"+language+".json", StandardCharsets.UTF_8));
			for (Entry<String, JsonElement> e : jObj.entrySet()) {
				lang.put(e.getKey(), e.getValue().getAsString());
			}
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
