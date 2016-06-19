package com.azias.awbe;

import java.util.Hashtable;

import com.badlogic.gdx.assets.AssetManager;

public class Assets {
	public static AssetManager assetsManager;
	private static Hashtable<String, String> assetsIndex;
	
	public static void init() {
		assetsManager = new AssetManager();
		assetsIndex = new Hashtable<String, String>();
	}
}
