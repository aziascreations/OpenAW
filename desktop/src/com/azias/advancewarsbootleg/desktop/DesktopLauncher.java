package com.azias.advancewarsbootleg.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Utils;
import com.azias.advancewarsbootleg.net.Server;

public class DesktopLauncher {
	private static boolean disableIcons = false;
	private static boolean disableMotd = false;
	private static String serverPassword = Utils.nextSessionId();
	private static int serverPort = 27069;
	private static int maxPlayers = 4;
	
	public static void main(String[] args) {
		System.out.println(Utils.getFormatedTime()+": Java version: "+System.getProperty("java.version"));
		System.out.println(Utils.getFormatedTime()+": Operating System: "+System.getProperty("os.name")+" - "+System.getProperty("os.arch"));
		
		boolean isClient = true;
		try {
			if(args[0].equals("-server")) {
				isClient = false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			System.err.println(Utils.getFormatedTime()+": Error: You haven't defined any arguments, client-side will be launched.");
			System.err.println(Utils.getFormatedTime()+": You should consider adding \"-client\" or \"-server\" as the first argument.");
		}
		
		if(isClient) {
			//Client
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.width = 1280;
			config.height = 720;
			config.resizable = false;
			
			try {
				for(int i=0; i<args.length; i++) {
					if(args[i].equals("-w")) {
						config.width = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("-h")) {
						config.height = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("-nomotd")) {
						disableMotd = true;
					} else if(args[i].equals("-noicon")) {
						disableIcons = true;
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.err.println(Utils.getFormatedTime()+": Error: The arguments are not set correctly.");
				e.printStackTrace();
				System.exit(1);
			}
			
			if(!disableIcons) {
				String[] a = new String[] {"Black","Blue","Green","Red","Yellow"};
				int b = new Random().nextInt(a.length);
				config.addIcon("datas/icons/icon"+a[b]+".png", FileType.Internal);
			}
			if(!disableMotd) {
				try {
					String messages[] = Utils.readFile("datas/titleMessages.txt", StandardCharsets.UTF_8).replace("\n", "").replace("\r", "").split(";");
					config.title = "Advance Wars Bootleg Edition - "+messages[new Random().nextInt(messages.length)];
				} catch (IOException e) {
					e.printStackTrace();
					config.title = "Advance Wars Bootleg Edition";
				}
			} else {
				config.title = "Advance Wars Bootleg Edition";
			}
	
			System.out.println(Utils.getFormatedTime()+": Window Size: "+config.width+" x "+config.height);
			System.out.println(Utils.getFormatedTime()+": Launching Advance Wars Bootleg Edition...");
			System.out.println(Utils.getFormatedTime()+": - - - - - - - - - - - - - - - - - - - - - -");
			new LwjglApplication(new AdvanceWarsBootleg(), config);
		} else {
			//Server
			try {
				for(int i=0; i<args.length; i++) {
					if(args[i].equals("-port")) {
						serverPort = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("-maxplayers")) {
						maxPlayers = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("-pass")) {
						serverPassword = args[i+1];
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.err.println(Utils.getFormatedTime()+": Error: The arguments are not set correctly.");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println(Utils.getFormatedTime()+": Launching Advance Wars Bootleg Edition Server...");
			System.out.println(Utils.getFormatedTime()+": - - - - - - - - - - - - - - - - - - - - - -");
			Server.startServer(serverPort, maxPlayers, serverPassword);
		}
	}
}
