package com.azias.advancewarsbootleg.net;

import java.util.ArrayList;

import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;

public class CommandHandler {

	public static void handleCommandsList(ArrayList<String> par1) {
		for(int i=0; i<par1.size(); i++) {
			Gdx.app.log(Utils.getFormatedTime(), "Executing: "+par1.get(i));
			String[] command = par1.get(i).split("#_#");
			try {
				switch(command[0]) {
				case "game":
					
					break;
				case "lobby":
					if(command[1].equals("names")) {
						Datas.executeJSCommand("guiLobbyUsernames = [\""+command[2]+"\",\""+command[3]+"\",\""+command[4]+"\",\""+command[5]+"\"];");
					} else if(command[1].equals("ready")) {
						Datas.executeJSCommand("guiLobbyReadyList = \""+command[2]+"\";");
					} else if(command[1].equals("map")) {
						
					} else if(command[1].equals("cos")) {
						Datas.executeJSCommand("guiLobbySelectedCos = [\""+command[2]+"\",\""+command[3]+"\",\""+command[4]+"\",\""+command[5]+"\"];");
					}
					break;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}
	
}
