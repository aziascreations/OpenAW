package com.azias.awbe.mod;

import java.util.HashMap;

public class ModInfo {
	/* Non-static Stuff */
	protected String id;
	protected String name, description;
	protected String[] authors, credits;
	protected boolean isCoreMod = false;
	protected String version;
	protected String versionUrl, projectUrl, updateUrl;
	protected HashMap<String, String> dependencies;
	protected EnumModType[] modTypes;
	
	public enum EnumModType {
		CODE, TEXTURE, AUDIO,
		UNIT, TERRAIN, CO,
		MAP, ALL;
	}
	
	public ModInfo() {
		
	}
	
	public void setDevelopInfo() {
		this.id = "awbe";
		this.name = "Advance Wars Bootleg Edition - Base Components";
		this.description = "...";
		this.authors = new String[]{"Azias"};
		this.credits = new String[]{"none"};
		this.isCoreMod = true;
		this.version = "0.0.1";
		this.versionUrl = "http://localhost/php/awbe/version.txt";
		this.projectUrl = "http://localhost/php/awbe/";
		this.updateUrl = "http://localhost/php/awbe/mod.zip";
		this.dependencies = new HashMap<String, String>();
		this.modTypes = new EnumModType[]{EnumModType.TERRAIN, EnumModType.CODE};
	}
	
	public String getId() {
		return this.id;
	}
	
	public EnumModType[] getModTypes() {
		return this.modTypes;
	}
	
	
	/* Static Stuff */
	/*public static ArrayList<Mod> loadModsList() {
		ArrayList<Mod> mods = new ArrayList<Mod>();
		ArrayList<File> folders = Utils.listFolders("./assets");
		
		for(File folder: folders) {
			File modInfoFile = new File(folder.getAbsolutePath()+"/modinfo.json");
			if(modInfoFile.isFile()) {
				try {
					String json = Utils.fileToString(modInfoFile.getAbsolutePath());
					Mod mod = new Gson().fromJson(json, Mod.class);
					mods.add(mod);
				} catch (IOException e) {
					System.err.println("Unable to load the \"modInfo.json\" file in \""+folder.getName()+"\"");
					e.printStackTrace();
				}
			} else {
				System.out.println("The folder named \""+folder.getName()+"\" does not countain a modinfo.json file.");
			}
		}
		
		return mods;
	}
	
	public static boolean checkModDependencies() {
		return false;
	}
	
	public static boolean loadMods() {
		
		return false;
	}*/
	
}
