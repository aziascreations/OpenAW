package com.azias.awbe;

import com.azias.awbe.mod.Mod;
import com.azias.awbe.mod.Mod.EventHandler;
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
