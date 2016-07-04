package com.azias.awbe;

import com.azias.openaw.mod.Mod;
import com.azias.openaw.mod.Mod.EventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azias.awbe.terrain.Terrain;
import com.azias.awbe.units.Units;

@Mod(id="awbe")
public class ModOpenAW {
	private final static Logger logger = LoggerFactory.getLogger(ModOpenAW.class);
	@EventHandler
	public static void preLoad() {
		logger.info("Registering Units...");
		Units.registerUnits();
		logger.info("Registering Terrains...");
		Terrain.registerUnits();
	}
	
	@EventHandler
	public static void postLoad() {
		//System.out.println("Hello world! - postLoad");
	}
}
