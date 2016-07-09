package com.azias.openaw;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.google.common.base.Strings;

public class Assets {
	//private final static Logger logger = LoggerFactory.getLogger(Assets.class);
	
	public static AssetManager assetsManager;
	public static Hashtable<String, String> assetsIndex;
	public static Hashtable<String, String> lang;
	
	public static String localizeString(String unlocalizedString) {
		if(unlocalizedString.startsWith("!")) {
			return unlocalizedString.substring(1);
		} else {
			String localizedString = lang.get(unlocalizedString);
			return (localizedString!=null) ? localizedString : unlocalizedString;
		}
	}
	
	public static Texture getTexture(String id) {
		//TODO: Clean this shit
		//NOTE: If the id doesn't exist, a NullPointerException is thrown !
		
		/*System.out.println("Getting: "+id);
		//System.out.println("assetsManager's Size: "+assetsManager.getAssetNames().size);
		Array<String> a = assetsManager.getAssetNames();
		for(String b : a) {
			System.out.println("Asset: "+assetsIndex.containsValue(b)+" - "+b+" - "+assetsManager.get(b));
		}/**/
		//Why am i getting NPEs
		
		//Forgot the .gfx like a dumbass
		String path = assetsIndex.get("gfx."+id);
		
		//System.out.println("assetsIndex Size: "+assetsIndex.size());
		
		/*Iterator it = assetsIndex.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " => " + pair.getValue());
	        //it.remove();
	    }*/
		
		//System.out.println("Got: "+path+" - from: "+"gfx."+id);/**/
		//Texture texture = assetsManager.get(path);
		//return (texture != null) ? texture : (Texture)assetsManager.get(assetsIndex.get("default"));
		
		//Should work flawlessly
		//TODO: Check if the default texture isn't null in the loading phase.
		if(Strings.isNullOrEmpty(path)) {
			return assetsManager.get(assetsIndex.get("gfx.default"));
		} else {
			return assetsManager.get(path);
		}
	}
}
