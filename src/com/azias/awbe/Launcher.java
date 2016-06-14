package com.azias.awbe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Launcher {
	private static final Logger logger = Logger.getLogger(Launcher.class.getName());
	
	private static Options launchOptions = new Options();
	private static CommandLine launchArgs;
	
	public static void main(String[] args) throws ParseException {
		loadLaunchArguments(args);
		
		if(launchArgs.hasOption("h")) {
			HelpFormatter formater = new HelpFormatter();
			formater.printHelp("Here is some text...", launchOptions);
			System.exit(0);
		}
		
		logger.log(Level.INFO, Utils.getFormatedTime()+": Java version: "+System.getProperty("java.version"));
		logger.log(Level.INFO, Utils.getFormatedTime()+": Operating System: "+System.getProperty("os.name")+" - "+System.getProperty("os.arch"));
		
		if(Integer.parseInt(System.getProperty("java.version").split("\\.")[1])<7) {
			JOptionPane.showMessageDialog(null, "Java "+Integer.parseInt(System.getProperty("java.version").split("\\.")[1])+" is not supported.\nPlease use Java 7 at least.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
		//System.setProperty("awbe.onlyeditor", "0");
		//System.setProperty("awbe.side", "client");
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		
		if(launchArgs.hasOption("w")) {
			config.width = Integer.parseInt(launchArgs.getOptionValue("w"));
		}
		
		logger.log(Level.INFO, Utils.getFormatedTime()+": Window Size: "+config.width+" x "+config.height);
		logger.log(Level.INFO, Utils.getFormatedTime()+": Launching Advance Wars Bootleg Edition...");
		logger.log(Level.INFO, Utils.getFormatedTime()+": - - - - - - - - - - - - - - - - - - - - - -");
		new LwjglApplication(new AdvanceWarsBootleg(), config);
	}
	
	private static void loadLaunchArguments(String[] args) {
		launchOptions.addOption("h", "help", false, "Show help.");

		launchOptions.addOption("w", "width", true, "Sets the window's width. (default: 1280)");
		launchOptions.addOption("z", "height", true, "Sets the window's height. (default: 720)");
		launchOptions.addOption("f", "fullscreen", false, "Enable fullscreen mode.");
		launchOptions.addOption("b", "borderless", false, "Enable borderless mode.");
		launchOptions.addOption("x", true, "Sets the window's X position. (-1 for centered)");
		launchOptions.addOption("y", true, "Sets the window's Y position. (-1 for centered)");
		
		try {
			launchArgs = new DefaultParser().parse(launchOptions, args);
		} catch (ParseException e) {
			logger.log(Level.SEVERE, "Failed to parse command line properties", e);
			System.exit(1);
		}
	}
}
