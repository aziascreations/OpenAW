package com.azias.openaw;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {
	private final static Logger logger = LoggerFactory.getLogger(Launcher.class);
	private static Options launchOptions = new Options();
	private static CommandLine launchArgs;
	
	public static void main(String[] args) throws ParseException {
		loadLaunchArguments(args);
		
		if(launchArgs.hasOption("h") && !launchArgs.hasOption("f")) {
			HelpFormatter formater = new HelpFormatter();
			formater.printHelp("Arguments List", launchOptions);
			System.exit(0);
		}
		
		if(!launchArgs.hasOption("m") && !launchArgs.hasOption("f")) {
			logger.error("The mod argument isn't set.");
			System.exit(102);
		}
		
		logger.info("Java: {} - {}", System.getProperty("java.vendor"), System.getProperty("java.version"));
		logger.info("Operating System: {} - {}", System.getProperty("os.name"), System.getProperty("os.arch"));
		
		if(Integer.parseInt(System.getProperty("java.version").split("\\.")[1])<7 && !launchArgs.hasOption("f")) {
			logger.error("Java {} is not supported.", Integer.parseInt(System.getProperty("java.version").split("\\.")[1]));
			logger.error("Please use Java 7 at least.");
			System.exit(101);
		}
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		
		if(launchArgs.hasOption("w")) {
			config.width = Integer.parseInt(launchArgs.getOptionValue("w"));
		}
		if(launchArgs.hasOption("b")) {
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		}
		
		logger.info("Window Size: {} x {}", config.width, config.height);
		logger.info("Launching Advance Wars Bootleg Edition...");
		logger.info("- - - - - - - - - - - - - - - - - - - - - -");
		new LwjglApplication(new AdvanceWarsBootleg(launchArgs), config);
	}
	
	private static void loadLaunchArguments(String[] args) {
		launchOptions.addOption("h", "help", false, "Show help.");
		
		launchOptions.addOption("w", "width", true, "Sets the window's width. (default: 1280)");
		launchOptions.addOption("z", "height", true, "Sets the window's height. (default: 720)");
		launchOptions.addOption("f", "fullscreen", false, "Enable fullscreen mode.");
		launchOptions.addOption("b", "borderless", false, "Enable borderless mode.");
		launchOptions.addOption("x", true, "Sets the window's X position. (-1 for centered)");
		launchOptions.addOption("y", true, "Sets the window's Y position. (-1 for centered)");
		launchOptions.addOption("m", "mods", true, "[MODS] ex: mod1;mod2;...");
		launchOptions.addOption("f", "force", false, "Bypass some launch securities.");
		
		try {
			launchArgs = new DefaultParser().parse(launchOptions, args);
		} catch (ParseException e) {
			logger.error("Failed to parse command line properties " + e);
			System.exit(100);
		}
	}
}