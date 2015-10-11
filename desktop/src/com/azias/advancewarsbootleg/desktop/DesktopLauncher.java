package com.azias.advancewarsbootleg.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.util.Random;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		String[] a = new String[] {"Black","Blue","Green","Red","Yellow"};
		Random rand = new Random();
		int b = rand.nextInt(a.length);
        config.addIcon("assets-desktop/icons/icon"+a[b]+".png", FileType.Internal);
		config.title = "Advance Wars Bootleg Edition";
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		new LwjglApplication(new AdvanceWarsBootleg(), config);
	}
}
