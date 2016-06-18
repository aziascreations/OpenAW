package com.azias.awbe.mod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.azias.awbe.mod.Mod.EventHandler;
import com.azias.awbe.mod.ModInfo.EnumModType;

public class ModLoader extends Object {
	private Set<Class<?>> modClasses; 
	private ModInfo[] mods;
	
	public ModLoader(ModInfo[] mods) {
		this.mods = mods;
	}
	
	public void loadModCode() {
		System.out.println("Loading mod classes..");
		this.loadModClasses();

		System.out.println("Pre-loading mods...");
		for(int i=0; i<this.mods.length; i++) {
			System.out.println("Pre-loading "+this.mods[i].id+"...");
			
			List<EnumModType> modTypes = Arrays.asList(this.mods[i].getModTypes());
			
			if(modTypes.contains(ModInfo.EnumModType.ALL) || modTypes.contains(ModInfo.EnumModType.CODE)) {
				this.preLoadModClasses(this.mods[i].getId());
			}
		}
		System.out.println("Done");
	}
	
	//Doesn't support dependencies and find a better name.
	private void loadModClasses() {
		Reflections reflections = new Reflections("test.awbe.mod");
		
		this.modClasses = reflections.getTypesAnnotatedWith(Mod.class);
		
		/*try {
			//this.modClasses = new Reflections("org.confusion").getTypesAnnotatedWith(com.azias.awbe.mod.Mod.class);
			this.modClasses = new Reflections("test.awbe.mod").getTypesAnnotatedWith(com.azias.awbe.mod.Mod.class, false);
		} catch(NoClassDefFoundError e) {
			e.printStackTrace();
		}*/
		
	    System.out.println("found classes: " + this.modClasses.size());
	    System.exit(0);
	}
	
	private void preLoadModClasses(String modId) {
		for (Class<?> c : this.modClasses) {
	        for (Method m : c.getMethods()) {
	            try {
	                if(m.isAnnotationPresent(EventHandler.class)) {
	                	System.out.println("name: "+m.getName());
	                    //MessageId mid = m.getAnnotation(MessageId.class);
	                    //    Object o = c.newInstance();
	                    //if (mid.id() == id)
	                    //    m.invoke(o);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
