package com.azias.awbe;

import com.azias.openaw.mod.Mod;
import com.azias.openaw.mod.Mod.EventHandler;
import com.azias.awbe.units.Units;

@Mod(id="awbe")
public class ModBootlegEdition {
	
	@EventHandler
	public static void preLoad() {
		//System.out.println("Hello world! - preLoad");
		
		Units.registerUnits();
	}
	
	@EventHandler
	public static void postLoad() {
		//System.out.println("Hello world! - postLoad");
	}
}
