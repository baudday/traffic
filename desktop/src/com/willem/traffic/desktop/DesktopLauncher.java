package com.willem.traffic.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.willem.traffic.Traffic;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Traffic";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new Traffic(), config);
	}
}
