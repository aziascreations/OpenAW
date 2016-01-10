package com.azias.advancewarsbootleg.tests;

import com.azias.advancewarsbootleg.gui.GuiStyle;
import com.google.gson.Gson;

public class GsonStyleTester {
	private static GuiStyle style;
	
	public static void main(String[] args) {
		style = new GuiStyle("styleKeyString","textureKeyString");
		
		Gson gson = new Gson();
		String json = gson.toJson(style);
		
		/*try {
			FileWriter writer = new FileWriter("c:\\temp\\test1.json");
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		System.out.println(json);
		System.out.println("- - - -");
		
		String a = "{\"textureKey\":\"myTexture\",\"styleKey\":\"styleDefault\",\"isStretched\":false}";
		
		gson = new Gson();
		style = gson.fromJson(a, GuiStyle.class);
		System.out.println(style);
		
		gson = new Gson();
		json = gson.toJson(style);
		System.out.println(json);
	}
}
