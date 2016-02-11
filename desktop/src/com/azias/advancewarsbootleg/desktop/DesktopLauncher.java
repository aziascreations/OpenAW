package com.azias.advancewarsbootleg.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Random;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;
import com.azias.advancewarsbootleg.net.Server;

public class DesktopLauncher {
	private static boolean disableIcons = false;
	private static boolean disableMotd = false;
	private static String serverPassword = Utils.nextSessionId();
	private static int serverPort = 27030;
	private static int maxPlayers = 4;
	
	public static void main(String[] args) {
		System.out.println(Utils.getFormatedTime()+": Java version: "+System.getProperty("java.version"));
		System.out.println(Utils.getFormatedTime()+": Operating System: "+System.getProperty("os.name")+" - "+System.getProperty("os.arch"));
		
		int side = 0;
		
		if(Integer.parseInt(System.getProperty("java.version").split("\\.")[1])<8) {
			side = -1;
		}
		
		if(side>=0) {
			try {
				if(args[0].equals("-s") || args[0].equals("--server")) {
					side = 1;
				} else if(args[0].equals("/?") || args[0].equals("-h") || args[0].equals("--help")) {
					side = -2;
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.err.println(Utils.getFormatedTime()+": Error: You haven't defined any arguments, client-side will be launched.");
				System.err.println(Utils.getFormatedTime()+": You should consider adding \"--client\" or \"--server\" as the first argument.");
			}
		}
		
		if(side==0) {
			//Client
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
			System.setProperty("awbe.onlyeditor", "0");
			config.width = 1280;
			config.height = 720;
			config.resizable = false;
			
			try {
				for(int i=0; i<args.length; i++) {
					if(args[i].equals("-w") || args[i].equals("--width")) {
						config.width = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("-h") || args[i].equals("--height")) {
						config.height = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("-x")) {
						if(!config.fullscreen) {
							config.x = Integer.parseInt(args[i+1]);
						}
					} else if(args[i].equals("-y")) {
						if(!config.fullscreen) {
							config.y = Integer.parseInt(args[i+1]);
						}
					} else if(args[i].equals("-f") || args[i].equals("--fullscreen")) {
						config.fullscreen = true;
						System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
					} else if(args[i].equals("-b") || args[i].equals("--borderless")) {
						config.fullscreen = false;
						System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
					} else if(args[i].equals("--nomotd")) {
						disableMotd = true;
					} else if(args[i].equals("--noicon")) {
						disableIcons = true;
					} else if(args[i].equals("-n") || args[i].equals("--name")) {
						Datas.name = args[i+1];
					} else if(args[i].equals("-e") || args[i].equals("--editor")) {
						System.setProperty("awbe.onlyeditor", "1");
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.err.println(Utils.getFormatedTime()+": Error: The arguments are not set correctly.");
				e.printStackTrace();
				System.exit(1);
			}
			
			if(Datas.name.equals("none")) {
				System.err.println(Utils.getFormatedTime()+": Error: You haven't set your name.");
				System.exit(1);
			}
			
			if(!disableIcons) {
				String[] a = new String[] {"Black","Blue","Green","Red","Yellow"};
				int b = new Random().nextInt(a.length);
				config.addIcon("datas/icons/icon"+a[b]+".png", FileType.Internal);
			}
			if(!disableMotd) {
				try {
					String messages[] = Utils.fileToString("datas/titleMessages.txt", StandardCharsets.UTF_8).replace("\n", "").replace("\r", "").split(";");
					config.title = "Advance Wars Bootleg Edition - "+messages[new Random().nextInt(messages.length)];
				} catch (IOException e) {
					e.printStackTrace();
					config.title = "Advance Wars Bootleg Edition";
				}
			} else {
				config.title = "Advance Wars Bootleg Edition";
			}
			
			String windowType = "Windowed";
			if(config.fullscreen) {
				windowType = "Fullscreen";
			} else if(System.getProperty("org.lwjgl.opengl.Window.undecorated").equals("true")) {
				windowType = "Borderless";
			}
			
			System.out.println(Utils.getFormatedTime()+": Window Size: "+config.width+" x "+config.height+" - "+windowType);
			System.out.println(Utils.getFormatedTime()+": Launching Advance Wars Bootleg Edition...");
			System.out.println(Utils.getFormatedTime()+": - - - - - - - - - - - - - - - - - - - - - -");
			new LwjglApplication(new AdvanceWarsBootleg(), config);
		} else if(side==1) {
			//Server
			try {
				for(int i=0; i<args.length; i++) {
					if(args[i].equals("-p") || args[i].equals("--port")) {
						serverPort = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("--maxplayers")) {
						maxPlayers = Integer.parseInt(args[i+1]);
					} else if(args[i].equals("--pass")) {
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
		} else if(side==-1) {
			//Wrong Java version
			Utils.showErrorMessageBox("Java "+Integer.parseInt(System.getProperty("java.version").split("\\.")[1])+" is not supported.\nPlease use Java 8.");
		} else if(side==-2) {
			//Help
			System.out.println(Utils.getFormatedTime()+": - - - - - - - - - - - - - - - - - - - - - -");
			System.out.println(Utils.getFormatedTime()+": Advance Wars Bootleg Edition - Help File:");
			System.out.println(Utils.getFormatedTime()+": - - - - - - - - - - - - - - - - - - - - - -");
			try {
				BufferedReader br = new BufferedReader(new FileReader("./datas/helpFile.txt"));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
