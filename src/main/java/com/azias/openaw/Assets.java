package com.azias.openaw;

import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badlogic.gdx.assets.AssetManager;

public class Assets {
	private final static Logger logger = LoggerFactory.getLogger(Assets.class);
	
	public static AssetManager assetsManager;
	public static Hashtable<String, String> assetsIndex;
}
