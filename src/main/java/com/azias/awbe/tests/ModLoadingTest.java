package com.azias.awbe.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.azias.awbe.Utils;
import com.azias.awbe.mod.ModInfo;
import com.azias.awbe.mod.ModLoader;
import com.google.gson.Gson;

public class ModLoadingTest {
	
	/*The arguments must be mod ids (mod1;mod2;...)*/
	public static void main(String[] args) {
		//This part is shit!
		//TODO:Find a better way to do it.
		//Do this when launching the game.
		ArrayList<ModInfo> availableMods = loadModsList();
		
		String[] modIds = args[0].split(";");
		ModInfo[] modsToLoad = new ModInfo[modIds.length];
		
		for(int i=0; i<modIds.length; i++) {
			for(ModInfo mod : availableMods) {
				//TODO: check for nulls !
				if(mod.getId().equals(modIds[i])) {
					modsToLoad[i] = mod;
				}
			}
		}
		
		ModLoader modLoader = new ModLoader(modsToLoad);
		
		modLoader.loadModCode();
	}
	
	public static ArrayList<ModInfo> loadModsList() {
		ArrayList<ModInfo> mods = new ArrayList<ModInfo>();
		ArrayList<File> folders = Utils.listFolders("./assets");
		
		for(File folder: folders) {
			File modInfoFile = new File(folder.getAbsolutePath()+"/modinfo.json");
			if(modInfoFile.isFile()) {
				try {
					String json = Utils.fileToString(modInfoFile.getAbsolutePath());
					ModInfo mod = new Gson().fromJson(json, ModInfo.class);
					mods.add(mod);
					System.out.println("Loaded \""+folder.getName()+"\".");
				} catch (IOException e) {
					System.err.println("Unable to load the \"modInfo.json\" file in \""+folder.getName()+"\"");
					e.printStackTrace();
				}
			} else {
				System.err.println("The folder named \""+folder.getName()+"\" does not countain a modinfo.json file.");
			}
		}
		
		return mods;
	}
}
