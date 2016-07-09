package com.azias.openaw;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.google.common.primitives.Ints;

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
	private static int[] windowSize = {800,600};
	
	public static void main(String[] args) throws ParseException {
		loadLaunchArguments(args);
		
		if(launchArgs.hasOption("help") && !launchArgs.hasOption("force")) {
			HelpFormatter formater = new HelpFormatter();
			formater.printHelp("game.jar [options]", launchOptions);
			System.exit(0);
		}
		
		if(!launchArgs.hasOption("mods") && !launchArgs.hasOption("force")) {
			logger.error("The mod argument isn't set.");
			System.exit(102);
		}
		
		logger.info("Java: {} - {}", System.getProperty("java.vendor"), System.getProperty("java.version"));
		logger.info("Operating System: {} - {}", System.getProperty("os.name"), System.getProperty("os.arch"));
		
		if(Integer.parseInt(System.getProperty("java.version").split("\\.")[1])<7 && !launchArgs.hasOption("force")) {
			logger.error("Java {} is not supported.", Integer.parseInt(System.getProperty("java.version").split("\\.")[1]));
			logger.error("Please use Java 7 at least.");
			System.exit(101);
		}
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("OpenAW");
		config.setWindowedMode(windowSize[0], windowSize[1]);
		config.useOpenGL3(true, 3, 2);
		
		if(launchArgs.hasOption("vsync")) {
			config.useVsync(true);
		} else {
			config.useVsync(false);
		}
		if(launchArgs.hasOption("width")) {
			Integer width = Ints.tryParse(launchArgs.getOptionValue("width"));
			if (width != null) {
				windowSize[0] = width;
			} else {
				System.exit(103);
			}
		}
		if(launchArgs.hasOption("height")) {
			Integer height = Ints.tryParse(launchArgs.getOptionValue("height"));
			if (height != null) {
				windowSize[1] = height;
			} else {
				System.exit(104);
			}
		}
		if(launchArgs.hasOption("borderless") || launchArgs.hasOption("undecorated")) {
			config.setDecorated(false);
		}
		if(launchArgs.hasOption("fullscreen")) {
			//Works fine, TODO: Find a way to select the screen for multi-screen setups
			config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		}
		
		logger.info("Window Size: {} x {}", Lwjgl3ApplicationConfiguration.getDisplayMode().width, Lwjgl3ApplicationConfiguration.getDisplayMode().height);
		logger.info("Launching OpenAW...");
		logger.info("- - - - - - - - - - - - - - - - - - - - - -");
		new Lwjgl3Application(new OpenAW(launchArgs), config);
	}
	
	private static void loadLaunchArguments(String[] args) {
		launchOptions.addOption("b", "borderless", false, "Enable borderless mode.");
		launchOptions.addOption("f", "fullscreen", false, "Enable fullscreen mode.");
		launchOptions.addOption(null, "force", false, "Bypass some launch securities.");
		launchOptions.addOption(null, "help", false, "Prints the help.");
		launchOptions.addOption("h", "height", true, "Sets the window's height. (default: "+windowSize[1]+")");
		launchOptions.addOption("l", "lang", true, "Sets the language to be used. (default: english)");
		launchOptions.addOption("m", "mods", true, "[MODS] ex: mod1;mod2;...");
		//launchOptions.addOption("s", "screen", true, "[TEST] Choose which screen to use (LWJGL3) (! before setting -u/-b)");
		launchOptions.addOption("u", "undecorated", false, "Enable borderless mode.");
		launchOptions.addOption("v", "vsync", false, "Enable vsync.");
		launchOptions.addOption("w", "width", true, "Sets the window's width. (default: "+windowSize[0]+")");
		//launchOptions.addOption("x", true, "Sets the window's X position. (default: -1)");
		//launchOptions.addOption("y", true, "Sets the window's Y position. (default: -1)");
		
		try {
			launchArgs = new DefaultParser().parse(launchOptions, args);
		} catch (ParseException e) {
			logger.error("Failed to parse command line properties");
			e.printStackTrace();
			System.exit(100);
		}
	}
}