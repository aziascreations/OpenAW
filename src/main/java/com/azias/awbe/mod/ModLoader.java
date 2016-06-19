package com.azias.awbe.mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azias.awbe.mod.ModInfo.EnumModType;
import com.google.common.base.Strings;

public class ModLoader extends Object {
	private final static Logger logger = LoggerFactory.getLogger(ModLoader.class);
	private Set<Class<?>> modClasses; 
	private ModInfo[] mods;
	
	private int mainProgess = 0;
	private float subProgress = -1;
	
	public ModLoader(ModInfo[] mods) {
		this.mods = mods;
		logger.debug("Initialized ModLoader with {} mods.", mods.length);
	}
	
	public boolean update() {
		switch(this.mainProgess) {
		case 0:
			
			
		}
		return false;
	}
	
	public void loadModCode() {
		System.out.println("Loading mod classes..");
		this.loadModClasses();
		System.out.println("Done");
		
		System.out.println("Pre-loading mods... - "+this.mods.length);
		for(int i=0; i<this.mods.length; i++) {
			System.out.println("Pre-loading "+this.mods[i]+"...");
			System.out.println("Pre-loading "+this.mods[i].id+"...");
			
			List<EnumModType> modTypes = Arrays.asList(this.mods[i].getModTypes());
			
			if(modTypes.contains(ModInfo.EnumModType.ALL) || modTypes.contains(ModInfo.EnumModType.CODE)) {
				this.executeModClassMethod(this.mods[i].getId(), "preLoad");
			}
		}
		System.out.println("Done");
	}
	
	private void loadModClasses() {
		this.modClasses = new Reflections("").getTypesAnnotatedWith(Mod.class);
		System.out.println("Found "+this.modClasses.size()+" mod classes.");
	}
	
	private void executeModClassMethod(String modId, String methodName) {
		System.out.println("Attempting to preload "+modId);
		mainloop:
		for(Class<?> c : this.modClasses) {
			if(Strings.isNullOrEmpty(c.getAnnotation(Mod.class).id())) {
				System.out.println("Mod class "+c.getName()+" doesn't have an id !");
				continue;
			}
			if(!c.getAnnotation(Mod.class).id().equals(modId)) {
				System.out.println(modId+" != "+c.getAnnotation(Mod.class).id());
				continue;
			}
			
			for(Method m : c.getMethods()) {
				if(m.getName().equals(methodName)) {
					try {
						m.invoke(c.newInstance());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
						e.printStackTrace();
					}
					//I fell so dirty for doing this.
					break mainloop;
				}
			}
		}
	}
}
